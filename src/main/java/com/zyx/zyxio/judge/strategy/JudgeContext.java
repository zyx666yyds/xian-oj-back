package com.zyx.zyxio.judge.strategy;

import com.zyx.zyxio.model.dto.question.QuestionJudgeCase;
import com.zyx.zyxio.judge.codesandbox.model.JudgeInfo;
import com.zyx.zyxio.model.entity.Question;
import com.zyx.zyxio.model.entity.QuestionSubmit;
import lombok.Data;

import java.util.List;

/**
 * 上下文（用于定义在策略中传递的参数）
 * @author zyx
 * @version 1.0
 * @date 2024/1/8 008 10:53
 */
@Data
public class JudgeContext {

    private JudgeInfo judgeInfo;

    private List<String> inputList;

    private List<String> outputList;

    private List<QuestionJudgeCase> judgeCaseList;

    private Question question;

    private QuestionSubmit questionSubmit;
}
