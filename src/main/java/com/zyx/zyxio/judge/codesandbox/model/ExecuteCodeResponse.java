package com.zyx.zyxio.judge.codesandbox.model;

import com.zyx.zyxio.model.dto.question.QuestionJudgeConfig;
import com.zyx.zyxio.model.dto.questionsubmit.JudgeInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zyx
 * @version 1.0
 * @date 2024/1/7 007 19:39
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExecuteCodeResponse {

    private List<String> outputList;

    /**
     * 接口信息
     */
    private String message;

    /**
     * 执行状态
     */
    private Integer status;

    /**
     * 判题信息
     */
    private JudgeInfo judgeInfo;


}
