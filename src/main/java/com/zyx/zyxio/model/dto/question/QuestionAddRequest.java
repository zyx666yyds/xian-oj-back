package com.zyx.zyxio.model.dto.question;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 创建请求
 *
 * @author <a href="https://github.com/lizyx">程序员鱼皮</a>
 * @from <a href="https://zyx.icu">编程导航知识星球</a>
 */
@Data
public class QuestionAddRequest implements Serializable {


    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 标签列表（json 数组）
     */
    private List<String> tags;

    /**
     * 题目答案
     */
    private String answer;


    /**
     * 判题用例(json数组)
     */
    private List<QuestionJudgeCase> judgeCase;

    /**
     * 判题配置(json)
     */
    private QuestionJudgeConfig judgeConfig;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}