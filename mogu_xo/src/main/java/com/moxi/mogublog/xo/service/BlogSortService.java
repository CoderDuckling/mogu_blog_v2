package com.moxi.mogublog.xo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.moxi.mogublog.commons.entity.BlogSort;
import com.moxi.mogublog.commons.entity.ResourceSort;
import com.moxi.mogublog.xo.vo.BlogSortVO;
import com.moxi.mogublog.xo.vo.ResourceSortVO;
import com.moxi.mougblog.base.service.SuperService;

import java.util.List;

/**
 * <p>
 * 博客分类表 服务类
 * </p>
 *
 * @author xuzhixiang
 * @since 2018-09-08
 */
public interface BlogSortService extends SuperService<BlogSort> {
    /**
     * 获取博客分类列表
     * @param blogSortVO
     * @return
     */
    public IPage<BlogSort> getPageList(BlogSortVO blogSortVO);

    /**
     * 新增博客分类
     * @param blogSortVO
     */
    public String addBlogSort(BlogSortVO blogSortVO);

    /**
     * 编辑博客分类
     * @param blogSortVO
     */
    public String editBlogSort(BlogSortVO blogSortVO);

    /**
     * 批量删除博客分类
     * @param blogSortVoList
     */
    public String deleteBatchBlogSort(List<BlogSortVO> blogSortVoList);

    /**
     * 置顶博客分类
     * @param blogSortVO
     */
    public String stickBlogSort(BlogSortVO blogSortVO);

    /**
     * 通过点击量排序博客
     * @return
     */
    public String blogSortByClickCount();

    /**
     * 通过引用量排序博客
     * @return
     */
    public String blogSortByCite();
}
