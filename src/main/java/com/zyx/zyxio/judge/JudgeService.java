package com.zyx.zyxio.judge;

import com.zyx.zyxio.judge.codesandbox.model.ExecuteCodeResponse;
import com.zyx.zyxio.model.entity.QuestionSubmit;
import com.zyx.zyxio.model.vo.QuestionVO;

/**
 * @author zyx
 * @version 1.0
 * @date 2024/1/8 008 9:50
 */
public interface JudgeService {

    /**
     * 判题
     * @param questionSubmitId
     * @return
     */
    QuestionSubmit doJudge(long questionSubmitId);
}
