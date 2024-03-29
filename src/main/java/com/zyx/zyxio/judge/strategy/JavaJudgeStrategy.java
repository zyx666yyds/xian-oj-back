package com.zyx.zyxio.judge.strategy;

import cn.hutool.json.JSONUtil;
import com.zyx.zyxio.model.dto.question.QuestionJudgeCase;
import com.zyx.zyxio.model.dto.question.QuestionJudgeConfig;
import com.zyx.zyxio.judge.codesandbox.model.JudgeInfo;
import com.zyx.zyxio.model.entity.Question;
import com.zyx.zyxio.model.enums.JudgeInfoMessageEnum;

import java.util.List;
import java.util.Optional;

/**
 * @author zyx
 * @version 1.0
 * @date 2024/1/8 008 10:56
 */
public class JavaJudgeStrategy implements JudgeStrategy {
    @Override
    public JudgeInfo doJudge(JudgeContext judgeContext) {


        JudgeInfo judgeInfo = judgeContext.getJudgeInfo();
        Long memory = Optional.ofNullable(judgeInfo.getMemory()).orElse(0L);
        Long time = Optional.ofNullable(judgeInfo.getTime()).orElse(0L);
        List<String> inputList = judgeContext.getInputList();
        List<String> outputList = judgeContext.getOutputList();
        Question question = judgeContext.getQuestion();
        List<QuestionJudgeCase> judgeCaseList = judgeContext.getJudgeCaseList();
        JudgeInfo judgeInfoResponse = new JudgeInfo();
        JudgeInfoMessageEnum judgeInfoMessageEnum = JudgeInfoMessageEnum.ACCEPTED;

        judgeInfoResponse.setTime(time);
        judgeInfoResponse.setMemory(memory);


        if (outputList.size() != inputList.size()) {
            judgeInfoMessageEnum = JudgeInfoMessageEnum.WRONG_ANSWER;
            judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
            return judgeInfoResponse;
        }
        for (int i = 0; i < judgeCaseList.size(); i++) {
            QuestionJudgeCase questionJudgeCase = judgeCaseList.get(i);
            if (!questionJudgeCase.getOutput().equals(outputList.get(i))) {
                judgeInfoMessageEnum = JudgeInfoMessageEnum.WRONG_ANSWER;
                judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
                return judgeInfoResponse;
            }
        }

        // 判断题目限制
        String judgeConfigStr = question.getJudgeConfig();
        QuestionJudgeConfig questionJudgeConfig = JSONUtil.toBean(judgeConfigStr, QuestionJudgeConfig.class);

        Long memoryLimit = questionJudgeConfig.getMemoryLimit();
        Long timeLimit = questionJudgeConfig.getTimeLimit();
        if (memory > memoryLimit) {
            judgeInfoMessageEnum = JudgeInfoMessageEnum.MEMORY_LIMIT_EXCEEDED;
            judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
            return judgeInfoResponse;
        }

        //java本身需要额外执行10s
        long JAVA_TIME_COST = 1000;

        if ((time - JAVA_TIME_COST) > timeLimit) {
            judgeInfoMessageEnum = JudgeInfoMessageEnum.TIME_LIMIT_EXCEEDED;
            judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
            return judgeInfoResponse;
        }
        judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
        return judgeInfoResponse;
    }
}
