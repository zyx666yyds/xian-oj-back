package com.zyx.zyxio.judge.codesandbox.impl;

import com.zyx.zyxio.judge.codesandbox.CodeSandbox;
import com.zyx.zyxio.judge.codesandbox.model.ExecuteCodeRequest;
import com.zyx.zyxio.judge.codesandbox.model.ExecuteCodeResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 远程代码沙箱（实际调用接口的沙箱）
 * @author zyx
 * @version 1.0
 * @date 2024/1/7 007 17:22
 */
@Slf4j
public class RemoteCodeSandbox implements CodeSandbox {

    /**
     * 执行代码
     *
     * @param executeCodeRequest
     * @return
     */
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        log.info("远程代码沙箱");
        return null;
    }
}
