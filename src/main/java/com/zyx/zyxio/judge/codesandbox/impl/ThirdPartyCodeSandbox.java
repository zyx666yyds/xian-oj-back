package com.zyx.zyxio.judge.codesandbox.impl;

import com.zyx.zyxio.judge.codesandbox.CodeSandbox;
import com.zyx.zyxio.judge.codesandbox.model.ExecuteCodeRequest;
import com.zyx.zyxio.judge.codesandbox.model.ExecuteCodeResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 第三方代码沙箱（调用别人现成的代码沙箱）
 * @author zyx
 * @version 1.0
 * @date 2024/1/7 007 17:22
 */
@Slf4j
public class ThirdPartyCodeSandbox implements CodeSandbox {

    /**
     * 执行代码
     *
     * @param executeCodeRequest
     * @return
     */
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        log.info("调用第三方代码沙箱");
        return null;
    }
}
