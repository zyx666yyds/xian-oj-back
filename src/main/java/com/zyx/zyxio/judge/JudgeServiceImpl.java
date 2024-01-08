package com.zyx.zyxio.judge;

import cn.hutool.json.JSONUtil;
import com.zyx.zyxio.common.ErrorCode;
import com.zyx.zyxio.exception.BusinessException;
import com.zyx.zyxio.judge.codesandbox.CodeSandbox;
import com.zyx.zyxio.judge.codesandbox.CodeSandboxFactory;
import com.zyx.zyxio.judge.codesandbox.CodeSandboxProxy;
import com.zyx.zyxio.judge.codesandbox.model.ExecuteCodeRequest;
import com.zyx.zyxio.judge.codesandbox.model.ExecuteCodeResponse;
import com.zyx.zyxio.judge.strategy.DefaultJudgeStrategy;
import com.zyx.zyxio.judge.strategy.JavaJudgeStrategy;
import com.zyx.zyxio.judge.strategy.JudgeContext;
import com.zyx.zyxio.judge.strategy.JudgeStrategy;
import com.zyx.zyxio.model.dto.question.QuestionJudgeCase;
import com.zyx.zyxio.model.dto.question.QuestionJudgeConfig;
import com.zyx.zyxio.model.dto.questionsubmit.JudgeInfo;
import com.zyx.zyxio.model.entity.Question;
import com.zyx.zyxio.model.entity.QuestionSubmit;
import com.zyx.zyxio.model.enums.JudgeInfoMessageEnum;
import com.zyx.zyxio.model.enums.QuestionSubmitLanguageEnum;
import com.zyx.zyxio.model.enums.QuestionSubmitStatusEnum;
import com.zyx.zyxio.model.vo.QuestionVO;
import com.zyx.zyxio.service.QuestionService;
import com.zyx.zyxio.service.QuestionSubmitService;
import org.elasticsearch.Assertions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zyx
 * @version 1.0
 * @date 2024/1/8 008 9:59
 */
@Service
public class JudgeServiceImpl implements JudgeService {

    @Value("${codesandbox.type:example}")
    private String type;

    @Resource
    private QuestionService questionService;

    @Resource
    private JudgeManager judgeManager;

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Override
    public QuestionSubmit doJudge(long questionSubmitId) {
        // 1.传入题目提交的id，查询题目提交记录
        QuestionSubmit questionSubmit = questionSubmitService.getById(questionSubmitId);
        if (questionSubmit == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "题目提交不存在");
        }

        Long questionId = questionSubmit.getQuestionId();
        Question question = questionService.getById(questionId);
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "题目不存在");
        }
        // 2.如果不为等待状态
        if (!questionSubmit.getStatus().equals(QuestionSubmitStatusEnum.WAITING.getValue())) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "题目正在判题");
        }
        // 3.更新题目提交状态
        QuestionSubmit questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.RUNNING.getValue());
        boolean updateById = questionSubmitService.updateById(questionSubmitUpdate);
        if (!updateById) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "更新题目提交状态失败");
        }

        // 4.调用沙箱，获取到执行结果
        CodeSandbox codeSandbox = CodeSandboxFactory.newInstance(type);
        codeSandbox = new CodeSandboxProxy(codeSandbox);
        String language = questionSubmit.getLanguage();
        String code = questionSubmit.getCode();
        //获取输入用例
        String judgeCase = question.getJudgeCase();
        List<QuestionJudgeCase> judgeCaseList = JSONUtil.toList(judgeCase, QuestionJudgeCase.class);
        List<String> inputList = judgeCaseList.stream().map(QuestionJudgeCase::getInput).collect(Collectors.toList());

        ExecuteCodeRequest builder = ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputList(inputList)
                .build();
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(builder);
        List<String> outputList = executeCodeResponse.getOutputList();

        // 5.根据沙箱执行结果，设置题目的判题状态和信息
        JudgeContext judgeContext = new JudgeContext();
        judgeContext.setJudgeInfo(executeCodeResponse.getJudgeInfo());
        judgeContext.setInputList(inputList);
        judgeContext.setOutputList(outputList);
        judgeContext.setJudgeCaseList(judgeCaseList);
        judgeContext.setQuestion(question);
        judgeContext.setQuestionSubmit(questionSubmit);


        JudgeInfo judgeInfo = judgeManager.doJudge(judgeContext);

        // 6.修改数据库中的判题结果
        questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());
        questionSubmitUpdate.setJudgeInfo(JSONUtil.toJsonStr(judgeInfo));
        updateById = questionSubmitService.updateById(questionSubmitUpdate);
        if (!updateById) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "更新题目提交状态失败");
        }

        QuestionSubmit questionSubmitResult = questionSubmitService.getById(questionId);


        return questionSubmitResult;
    }
}
