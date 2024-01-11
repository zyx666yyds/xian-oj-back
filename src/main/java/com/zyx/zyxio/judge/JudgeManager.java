package com.zyx.zyxio.judge;

import com.zyx.zyxio.judge.strategy.DefaultJudgeStrategy;
import com.zyx.zyxio.judge.strategy.JavaJudgeStrategy;
import com.zyx.zyxio.judge.strategy.JudgeContext;
import com.zyx.zyxio.judge.strategy.JudgeStrategy;
import com.zyx.zyxio.judge.codesandbox.model.JudgeInfo;
import com.zyx.zyxio.model.entity.QuestionSubmit;
import org.springframework.stereotype.Service;

import static com.zyx.zyxio.model.enums.QuestionSubmitLanguageEnum.*;

/**
 * 尽量简化对判题功能的调用
 *
 * @author zyx
 * @version 1.0
 * @date 2024/1/8 008 11:24
 */
@Service
public class JudgeManager {

    public JudgeInfo doJudge(JudgeContext judgeContext) {
        // 判题策略
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if (JAVA.getValue().equals(language)) {
            judgeStrategy = new JavaJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }
}
