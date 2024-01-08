package com.zyx.zyxio.judge.codesandbox;

import com.zyx.zyxio.judge.codesandbox.impl.ExampleCodeSandbox;
import com.zyx.zyxio.judge.codesandbox.impl.RemoteCodeSandbox;
import com.zyx.zyxio.judge.codesandbox.impl.ThirdPartyCodeSandbox;
import com.zyx.zyxio.judge.codesandbox.model.ExecuteCodeRequest;
import com.zyx.zyxio.judge.codesandbox.model.ExecuteCodeResponse;
import com.zyx.zyxio.model.enums.QuestionSubmitLanguageEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author zyx
 * @version 1.0
 * @date 2024/1/7 007 20:14
 */
@SpringBootTest
class CodeSandboxTest {

    @Value("${codesandbox.type:example}")
    private String type;

    @Test
    void executeCode() {
        CodeSandbox exampleCodeSandbox = new ExampleCodeSandbox();
        CodeSandbox remoteCodeSandbox = new RemoteCodeSandbox();
        CodeSandbox thirdPartyCodeSandbox = new ThirdPartyCodeSandbox();

        String code = "int main(){}";
        String language = QuestionSubmitLanguageEnum.JAVA.getValue();
        List<String> inputList = Arrays.asList("1 2", "3 4");

        ExecuteCodeRequest builder = ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputList(inputList)
                .build();
        ExecuteCodeResponse executeCodeResponse = exampleCodeSandbox.executeCode(builder);
        Assertions.assertNotNull(executeCodeResponse);
    }

    @Test
    void executeCodeByProxy() {
        CodeSandbox codeSandbox = CodeSandboxFactory.newInstance(type);

        String code = "int main(){}";
        String language = QuestionSubmitLanguageEnum.JAVA.getValue();
        List<String> inputList = Arrays.asList("1 2", "3 4");

        ExecuteCodeRequest builder = ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputList(inputList)
                .build();
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(builder);
        Assertions.assertNotNull(executeCodeResponse);
    }
}