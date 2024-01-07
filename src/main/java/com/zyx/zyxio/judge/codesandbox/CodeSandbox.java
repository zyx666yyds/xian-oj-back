package com.zyx.zyxio.judge.codesandbox;

import com.zyx.zyxio.judge.codesandbox.model.ExecuteCodeRequest;
import com.zyx.zyxio.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * @author zyx
 * @version 1.0
 * @date 2024/1/7 007 17:22
 */
public interface CodeSandbox {

    /**
     * 执行代码
     *
     * @param executeCodeRequest
     * @return
     */
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}
