package com.zyx.zyxio.judge.codesandbox.impl;

import com.zyx.zyxio.judge.codesandbox.CodeSandbox;
import com.zyx.zyxio.judge.codesandbox.model.ExecuteCodeRequest;
import com.zyx.zyxio.judge.codesandbox.model.ExecuteCodeResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 实例代码沙箱（仅为了跑通业务流程）
 * @author zyx
 * @version 1.0
 * @date 2024/1/7 007 17:22
 */
@Slf4j
public class ExampleCodeSandbox implements CodeSandbox {

    /**
     * 执行代码
     *
     * @param executeCodeRequest
     * @return
     */
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        log.info("示例代码沙箱");
        return null;
    }
}
