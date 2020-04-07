package com.moxi.mogublog.xo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moxi.mogublog.commons.entity.Blog;
import com.moxi.mogublog.commons.entity.BlogSort;
import com.moxi.mogublog.xo.vo.BlogSortVO;
import com.moxi.mogublog.xo.vo.BlogVO;
import com.moxi.mougblog.base.service.SuperService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 博客表 服务类
 * </p>
 *
 * @author xuzhixiang
 * @since 2018-09-08
 */
public interface BlogService extends SuperService<Blog> {

    /**
     * 给博客列表设置标签
     *
     * @return
     */
    public List<Blog> setTagByBlogList(List<Blog> list);

    /**
     * 给博客列表设置分类和标签
     *
     * @param list
     * @return
     */
    public List<Blog> setTagAndSortByBlogList(List<Blog> list);

    /**
     * 给博客设置标签
     *
     * @param blog
     * @return
     */
    public Blog setTagByBlog(Blog blog);

    /**
     * 给博客设置分类
     *
     * @param blog
     * @return
     */
    public Blog setSortByBlog(Blog blog);

    /**
     * 通过推荐等级获取博客列表
     *
     * @param level
     * @return
     */
    public List<Blog> getBlogListByLevel(Integer level);

    /**
     * 通过推荐等级获取博客Page
     *
     * @param level
     * @return
     */
    public IPage<Blog> getBlogPageByLevel(Page<Blog> page, Integer level);

    /**
     * 通过推荐等级获取博客Page，是否排序
     *
     * @param level
     * @return
     */
    public IPage<Blog> getBlogPageByLevel(Page<Blog> page, Integer level, Integer useSort);

    /**
     * 通过状态获取博客数量
     *
     * @author xzx19950624@qq.com
     * @date 2018年10月22日下午3:30:28
     */
    public Integer getBlogCount(Integer status);

    /**
     * 通过标签获取博客数目
     *
     * @author Administrator
     * @date 2019年6月19日16:28:16
     */
    public List<Map<String, Object>> getBlogCountByTag();

    /**
     * 通过标签获取博客数目
     *
     * @author Administrator
     * @date 2019年11月27日13:14:34
     */
    public List<Map<String, Object>> getBlogCountByBlogSort();

    /**
     * 获取一年内的文章贡献数
     *
     * @return
     */
    public Map<String, Object> getBlogContributeCount();

    /**
     * 通过uid获取博客内容
     *
     * @param uid
     * @return
     */
    public Blog getBlogByUid(String uid);

    /**
     * 根据BlogUid获取相关的博客
     *
     * @param blogUid
     * @return
     */
    public List<Blog> getSameBlogByBlogUid(String blogUid);

    /**
     * 获取博客列表
     * @param blogVO
     * @return
     */
    public IPage<Blog> getPageList(BlogVO blogVO);

    /**
     * 新增博客
     * @param blogVO
     */
    public String addBlog(BlogVO blogVO);

    /**
     * 编辑博客
     * @param blogVO
     */
    public String editBlog(BlogVO blogVO);

    /**
     * 推荐博客排序调整
     * @param blogVOList
     * @return
     */
    public String editBatch(List<BlogVO> blogVOList);

    /**
     * 批量删除博客
     * @param blogVO
     */
    public String deleteBlog(BlogVO blogVO);

    /**
     * 批量删除博客
     * @param blogVoList
     * @return
     */
    public String deleteBatchBlog(List<BlogVO> blogVoList);
}
