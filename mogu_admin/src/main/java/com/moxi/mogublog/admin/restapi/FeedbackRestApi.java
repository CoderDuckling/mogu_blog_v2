package com.moxi.mogublog.admin.restapi;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moxi.mogublog.admin.global.MessageConf;
import com.moxi.mogublog.admin.global.SQLConf;
import com.moxi.mogublog.admin.global.SysConf;
import com.moxi.mogublog.admin.log.OperationLogger;
import com.moxi.mogublog.admin.security.AuthorityVerify;
import com.moxi.mogublog.commons.entity.Feedback;
import com.moxi.mogublog.commons.entity.User;
import com.moxi.mogublog.utils.ResultUtil;
import com.moxi.mogublog.utils.StringUtils;
import com.moxi.mogublog.xo.service.FeedbackService;
import com.moxi.mogublog.xo.service.UserService;
import com.moxi.mogublog.xo.vo.FeedbackVO;
import com.moxi.mougblog.base.enums.EStatus;
import com.moxi.mougblog.base.exception.ThrowableUtils;
import com.moxi.mougblog.base.validator.group.Delete;
import com.moxi.mougblog.base.validator.group.GetList;
import com.moxi.mougblog.base.validator.group.Update;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * <p>
 * 反馈表 RestApi
 * </p>
 *
 * @author 陌溪
 * @since 2020年3月16日08:38:07
 */
@RestController
@Api(value = "反馈RestApi", tags = {"FeedbackRestApi"})
@RequestMapping("/feedback")
@Slf4j
public class FeedbackRestApi {

    @Autowired
    FeedbackService feedbackService;

    @AuthorityVerify
    @ApiOperation(value = "获取反馈列表", notes = "获取反馈列表", response = String.class)
    @PostMapping("/getList")
    public String getList(@Validated({GetList.class}) @RequestBody FeedbackVO feedbackVO, BindingResult result) {

        // 参数校验
        ThrowableUtils.checkParamArgument(result);
        return ResultUtil.result(SysConf.SUCCESS, feedbackService.getPageList(feedbackVO));
    }

    @AuthorityVerify
    @OperationLogger(value = "编辑反馈")
    @ApiOperation(value = "编辑反馈", notes = "编辑反馈", response = String.class)
    @PostMapping("/edit")
    public String edit(@Validated({Update.class}) @RequestBody FeedbackVO feedbackVO, BindingResult result) {

        // 参数校验
        ThrowableUtils.checkParamArgument(result);
        return feedbackService.addFeedback(feedbackVO);
    }

    @AuthorityVerify
    @OperationLogger(value = "批量删除反馈")
    @ApiOperation(value = "批量删除反馈", notes = "批量删除反馈", response = String.class)
    @PostMapping("/deleteBatch")
    public String delete(@Validated({Delete.class}) @RequestBody List<FeedbackVO> feedbackVOList, BindingResult result) {

        // 参数校验
        ThrowableUtils.checkParamArgument(result);
        return feedbackService.deleteBatchFeedback(feedbackVOList);
    }

}

