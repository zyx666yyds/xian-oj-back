package com.zyx.zyxio.service;

import co.elastic.clients.elasticsearch.sql.QueryRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyx.zyxio.model.dto.question.QuestionQueryRequest;
import com.zyx.zyxio.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.zyx.zyxio.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.zyx.zyxio.model.entity.Question;
import com.zyx.zyxio.model.entity.QuestionSubmit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zyx.zyxio.model.entity.User;
import com.zyx.zyxio.model.vo.QuestionSubmitVO;
import com.zyx.zyxio.model.vo.QuestionVO;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 24088
 * @description 针对表【question_submit(题目提交)】的数据库操作Service
 * @createDate 2024-01-03 16:03:55
 */
public interface QuestionSubmitService extends IService<QuestionSubmit> {
    /**
     * 题目提交
     *
     * @param questionSubmitAddRequest
     * @param loginUser
     * @return
     */
    long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);

    /**
     * 获取查询条件
     *
     * @param questionSubmitQueryRequest
     * @return
     */
    QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest);

    /**
     * 获取帖子封装
     *
     * @param questionSubmit
     * @param loginUser
     * @return
     */
    QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, User loginUser);

    /**
     * 分页获取帖子封装
     *
     * @param questionSubmitPage
     * @param loginUser
     * @return
     */
    Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmitPage, User loginUser);
}
