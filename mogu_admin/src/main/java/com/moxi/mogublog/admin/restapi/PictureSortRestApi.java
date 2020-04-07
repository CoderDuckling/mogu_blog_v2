package com.moxi.mogublog.admin.restapi;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moxi.mogublog.admin.global.MessageConf;
import com.moxi.mogublog.admin.global.SQLConf;
import com.moxi.mogublog.admin.global.SysConf;
import com.moxi.mogublog.admin.log.OperationLogger;
import com.moxi.mogublog.admin.security.AuthorityVerify;
import com.moxi.mogublog.admin.util.WebUtils;
import com.moxi.mogublog.commons.entity.Picture;
import com.moxi.mogublog.commons.entity.PictureSort;
import com.moxi.mogublog.commons.feign.PictureFeignClient;
import com.moxi.mogublog.utils.ResultUtil;
import com.moxi.mogublog.utils.StringUtils;
import com.moxi.mogublog.xo.service.PictureService;
import com.moxi.mogublog.xo.service.PictureSortService;
import com.moxi.mogublog.xo.vo.PictureSortVO;
import com.moxi.mogublog.xo.vo.PictureVO;
import com.moxi.mougblog.base.enums.EStatus;
import com.moxi.mougblog.base.exception.ThrowableUtils;
import com.moxi.mougblog.base.validator.group.Delete;
import com.moxi.mougblog.base.validator.group.GetList;
import com.moxi.mougblog.base.validator.group.Insert;
import com.moxi.mougblog.base.validator.group.Update;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * <p>
 * 图片分类表 RestApi
 * </p>
 *
 * @author xzx19950624@qq.com
 * @since 2018年9月17日16:37:13
 */
@Api(value = "图片分类RestApi", tags = {"PictureSortRestApi"})
@RestController
@RequestMapping("/pictureSort")
@Slf4j
public class PictureSortRestApi {

    @Autowired
    PictureSortService pictureSortService;

    @AuthorityVerify
    @ApiOperation(value = "获取图片分类列表", notes = "获取图片分类列表", response = String.class)
    @PostMapping(value = "/getList")
    public String getList(@Validated({GetList.class}) @RequestBody PictureSortVO pictureSortVO, BindingResult result) {
        // 参数校验
        ThrowableUtils.checkParamArgument(result);
        return ResultUtil.result(SysConf.SUCCESS, pictureSortService.getPageList(pictureSortVO));
    }

    @AuthorityVerify
    @OperationLogger(value = "增加图片分类")
    @ApiOperation(value = "增加图片分类", notes = "增加图片分类", response = String.class)
    @PostMapping("/add")
    public String add(@Validated({Insert.class}) @RequestBody PictureSortVO pictureSortVO, BindingResult result) {
        // 参数校验
        ThrowableUtils.checkParamArgument(result);
        return pictureSortService.addPictureSort(pictureSortVO);
    }

    @AuthorityVerify
    @OperationLogger(value = "编辑图片分类")
    @ApiOperation(value = "编辑图片分类", notes = "编辑图片分类", response = String.class)
    @PostMapping("/edit")
    public String edit(@Validated({Update.class}) @RequestBody PictureSortVO pictureSortVO, BindingResult result) {

        // 参数校验
        ThrowableUtils.checkParamArgument(result);
        return pictureSortService.editPictureSort(pictureSortVO);
    }

    @AuthorityVerify
    @OperationLogger(value = "删除图片分类")
    @ApiOperation(value = "删除图片分类", notes = "删除图片分类", response = String.class)
    @PostMapping("/delete")
    public String delete(@Validated({Delete.class}) @RequestBody PictureSortVO pictureSortVO, BindingResult result) {
        // 参数校验
        ThrowableUtils.checkParamArgument(result);
        return pictureSortService.deletePictureSort(pictureSortVO);
    }

    @AuthorityVerify
    @OperationLogger(value = "置顶分类")
    @ApiOperation(value = "置顶分类", notes = "置顶分类", response = String.class)
    @PostMapping("/stick")
    public String stick(@Validated({Delete.class}) @RequestBody PictureSortVO pictureSortVO, BindingResult result) {
        // 参数校验
        ThrowableUtils.checkParamArgument(result);
        return pictureSortService.stickPictureSort(pictureSortVO);
    }

    @OperationLogger(value = "通过Uid获取分类")
    @ApiOperation(value = "通过Uid获取分类", notes = "通过Uid获取分类", response = String.class)
    @PostMapping("/getPictureSortByUid")
    public String getPictureSortByUid(@Validated({Delete.class}) @RequestBody PictureSortVO pictureSortVO, BindingResult result) {
        // 参数校验
        ThrowableUtils.checkParamArgument(result);
        PictureSort pictureSort = pictureSortService.getById(pictureSortVO.getUid());
        return ResultUtil.result(SysConf.SUCCESS, pictureSort);
    }
}

