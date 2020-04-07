package com.moxi.mogublog.xo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.moxi.mogublog.commons.entity.BlogSort;
import com.moxi.mogublog.commons.entity.Feedback;
import com.moxi.mogublog.xo.vo.BlogSortVO;
import com.moxi.mogublog.xo.vo.FeedbackVO;
import com.moxi.mougblog.base.service.SuperService;

import java.util.List;

/**
 * <p>
 * 反馈表 服务类
 * </p>
 *
 * @author xuzhixiang
 * @since 2018-09-08
 */
public interface FeedbackService extends SuperService<Feedback> {

    /**
     * 获取反馈列表
     * @param feedbackVO
     * @return
     */
    public IPage<Feedback> getPageList(FeedbackVO feedbackVO);

    /**
     * 新增反馈
     * @param feedbackVO
     */
    public String addFeedback(FeedbackVO feedbackVO);

    /**
     * 编辑反馈
     * @param feedbackVO
     */
    public String editFeedback(FeedbackVO feedbackVO);

    /**
     * 批量删除反馈
     * @param feedbackVOList
     */
    public String deleteBatchFeedback(List<FeedbackVO> feedbackVOList);
}
