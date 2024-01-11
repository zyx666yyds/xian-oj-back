package com.zyx.zyxio.judge.codesandbox.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.zyx.zyxio.common.ErrorCode;
import com.zyx.zyxio.exception.BusinessException;
import com.zyx.zyxio.judge.codesandbox.CodeSandbox;
import com.zyx.zyxio.judge.codesandbox.model.ExecuteCodeRequest;
import com.zyx.zyxio.judge.codesandbox.model.ExecuteCodeResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * 远程代码沙箱（实际调用接口的沙箱）
 * @author zyx
 * @version 1.0
 * @date 2024/1/7 007 17:22
 */
@Slf4j
public class RemoteCodeSandbox implements CodeSandbox {

    /**
     * 请求头
     */
    private static final String AUTH_REQUEST_HEADER = "auth";
    /**
     * 密钥
     */
    private static final String AUTH_REQUEST_SECRET = "secretKey";
    /**
     * 执行代码
     *
     * @param executeCodeRequest
     * @return
     */
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        String url = "http://localhost:8081/executeCode";
        String str = JSONUtil.toJsonStr(executeCodeRequest);
        String responseStr = HttpUtil.createPost(url)
                .header(AUTH_REQUEST_HEADER,AUTH_REQUEST_SECRET)
                .body(str)
                .execute()
                .body();
        if (StringUtils.isBlank(responseStr)){
            throw new BusinessException(ErrorCode.API_REQUEST_ERROR,"远程代码沙箱返回结果为空 ");
        }
        return JSONUtil.toBean(responseStr, ExecuteCodeResponse.class);
    }
}
