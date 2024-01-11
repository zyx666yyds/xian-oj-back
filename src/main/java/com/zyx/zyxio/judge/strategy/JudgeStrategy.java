package com.zyx.zyxio.judge.strategy;

import com.zyx.zyxio.judge.codesandbox.model.JudgeInfo;

/**
 * 判题策略
 *
 * @author zyx
 * @version 1.0
 * @date 2024/1/8 008 10:51
 */
public interface JudgeStrategy {

    /**
     * 执行判题
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext);
}
