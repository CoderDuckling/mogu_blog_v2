//package com.moxi.mogublog.search.service;
//
//import com.moxi.mogublog.search.global.SysConf;
//import com.moxi.mogublog.search.pojo.SolrIndex;
//import com.moxi.mogublog.utils.StringUtils;
//import com.moxi.mogublog.xo.entity.Blog;
//import com.moxi.mogublog.xo.entity.Tag;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.solr.core.SolrTemplate;
//import org.springframework.data.solr.core.query.*;
//import org.springframework.data.solr.core.query.result.HighlightEntry;
//import org.springframework.data.solr.core.query.result.HighlightPage;
//import org.springframework.stereotype.Service;
//
//import java.util.*;
//
///**
// * solr索引维护实现
// *
// * @author limboy
// * @create 2018-09-29 16:19
// */
//@Service
//public class SolrSearchService {
//
//    @Autowired
//    private SolrTemplate solrTemplate;
//
//    //搜索(带高亮)
//
//    public Map<String, Object> search(String colleciton, String keywords, Integer currentPage, Integer pageSize) {
//
//        Map<String, Object> map = new HashMap<>();
//        map.putAll(searchList(colleciton, keywords, currentPage, pageSize));
//        return map;
//    }
//
//    //初始化索引
//    public void initIndex(String collection, List<Blog> blogList) {
//
//        List<SolrIndex> solrIndexs = new ArrayList<>();
//
//        for (Blog blog : blogList) {
//            SolrIndex solrIndex = new SolrIndex();
//            solrIndex.setFileUid(blog.getFileUid());
//            //将图片存放索引中
//            if (blog.getPhotoList() != null && blog.getPhotoList().size() > 0) {
//                solrIndex.setPhotoUrl(blog.getPhotoList().get(0));
//            } else {
//                solrIndex.setPhotoUrl("");
//            }
//            solrIndex.setId(blog.getUid());
//            solrIndex.setTitle(blog.getTitle());
//            solrIndex.setSummary(blog.getSummary());
//
//            List<Tag> tagList = blog.getTagList();
//            List<String> tagContentList = new ArrayList<>();
//
//            if (tagList != null && tagList.size() > 0) {
//                tagList.forEach(item -> {
//                    if (item != null) {
//                        tagContentList.add(item.getContent());
//                    }
//                });
//            }
//
//            if (tagContentList.size() > 0) {
//                solrIndex.setBlogTagName(StringUtils.listTranformString(tagContentList, ","));
//            }
//
//            if (blog.getBlogSort() != null) {
//                solrIndex.setBlogSortName(blog.getBlogSort().getSortName());
//            }
//
//            solrIndex.setBlogTagUid(blog.getTagUid());
//            solrIndex.setBlogSortUid(blog.getBlogSortUid());
//            solrIndex.setAuthor(blog.getAuthor());
//            solrIndex.setCreateTime(blog.getCreateTime());
//            solrIndexs.add(solrIndex);
//        }
//        solrTemplate.saveBeans(collection, solrIndexs);
//        solrTemplate.commit(collection);
//    }
//
//    //添加索引
//    public void addIndex(String collection, Blog blog) {
//
//        SolrIndex solrIndex = new SolrIndex();
//        //将图片存放索引中
//        if (blog.getPhotoList() != null && blog.getPhotoList().size() > 0) {
//            solrIndex.setPhotoUrl(blog.getPhotoList().get(0));
//        } else {
//            solrIndex.setPhotoUrl("");
//        }
//        solrIndex.setId(blog.getUid());
//        solrIndex.setTitle(blog.getTitle());
//        solrIndex.setSummary(blog.getSummary());
//
//        List<Tag> tagList = blog.getTagList();
//        List<String> tagContentList = new ArrayList<>();
//        if (tagList != null) {
//            tagList.forEach(item -> {
//                tagContentList.add(item.getContent());
//            });
//        }
//        solrIndex.setBlogTagName(StringUtils.listTranformString(tagContentList, ","));
//
//        if (blog.getBlogSort() != null) {
//            solrIndex.setBlogSortName(blog.getBlogSort().getSortName());
//        }
//
//        solrIndex.setAuthor(blog.getAuthor());
//        solrIndex.setCreateTime(blog.getCreateTime());
//        solrTemplate.saveBean(collection, solrIndex);
//        solrTemplate.commit(collection);
//
//    }
//
//    //更新索引
//    public void updateIndex(String collection, Blog blog) {
//
//        Optional<SolrIndex> solrIndex = solrTemplate.getById(collection, blog.getUid(), SolrIndex.class);
//
//        //为空表示原来修改发布状态位的时候，删除掉了索引，需要重新添加
//        if (solrIndex.isPresent()) {
//
//            addIndex(collection, blog);
//
//        } else {
//            //将图片存放索引中
//            if (blog.getPhotoList() != null && blog.getPhotoList().size() > 0) {
//                solrIndex.get().setPhotoUrl(blog.getPhotoList().get(0));
//            } else {
//                solrIndex.get().setPhotoUrl("");
//            }
//            solrIndex.get().setId(blog.getUid());
//            solrIndex.get().setTitle(blog.getTitle());
//            solrIndex.get().setSummary(blog.getSummary());
//
//            List<Tag> tagList = blog.getTagList();
//            List<String> tagContentList = new ArrayList<>();
//            if (tagList != null) {
//                tagList.forEach(item -> {
//                    tagContentList.add(item.getContent());
//                });
//            }
//            solrIndex.get().setBlogTagName(StringUtils.listTranformString(tagContentList, ","));
//
//            if (blog.getBlogSort() != null) {
//                solrIndex.get().setBlogSortName(blog.getBlogSort().getSortName());
//            }
//
//            solrIndex.get().setAuthor(blog.getAuthor());
//            solrIndex.get().setCreateTime(blog.getCreateTime());
//            solrTemplate.saveBean(collection, solrIndex);
//            solrTemplate.commit(collection);
//        }
//
//    }
//
//
//    public void deleteIndex(String collection, String uid) {
//        solrTemplate.deleteByIds(collection, uid);
//        solrTemplate.commit(collection);
//    }
//
//    public void deleteBatchIndex(String collection, List<String> ids) {
//        solrTemplate.deleteByIds(collection, ids);
//        solrTemplate.commit(collection);
//    }
//
//    public void deleteAllIndex(String collection) {
//        SimpleQuery query = new SimpleQuery("*:*");
//        solrTemplate.delete(collection, query);
//        solrTemplate.commit(collection);
//    }
//
//
//    private Map<String, Object> searchList(String collection, String keywords, Integer currentPage, Integer pageSize) {
//
//        Map<String, Object> map = new HashMap<>();
//
//        HighlightQuery query = new SimpleHighlightQuery();
//        HighlightOptions highlightOptions = new HighlightOptions().addField("blog_title");
//        //高亮前缀
//        highlightOptions.setSimplePrefix("<span style = 'color:red'>");
//        //高亮后缀
//        highlightOptions.setSimplePostfix("</span>");
//        query.setHighlightOptions(highlightOptions);
//
//        //添加查询条件
//        Criteria criteria = new Criteria("blog_keywords").is(keywords);
//        query.addCriteria(criteria);
//
//        //从第几条记录查询
//        query.setOffset((long) (currentPage - 1) * pageSize);
//        query.setRows(pageSize);
//
//        HighlightPage<SolrIndex> page = solrTemplate.queryForHighlightPage(collection, query, SolrIndex.class);
//
//        //循环高亮入口集合
//        for (HighlightEntry<SolrIndex> h : page.getHighlighted()) {
//            //获取原实体类
//            SolrIndex solrIndex = h.getEntity();
//            if (h.getHighlights().size() > 0 && h.getHighlights().get(0).getSnipplets().size() > 0) {
//                //设置高亮结果
//                solrIndex.setTitle(h.getHighlights().get(0).getSnipplets().get(0));
//            }
//        }
//
//        // 返回总记录数
//        map.put(SysConf.TOTAL, page.getTotalElements());
//
//        // 返回总页数
//        map.put(SysConf.TOTAL_PAGE, page.getTotalPages());
//
//        // 返回当前页大小
//        map.put(SysConf.PAGE_SIZE, pageSize);
//
//        // 返回当前页
//        map.put(SysConf.CURRENT_PAGE, currentPage);
//
//        map.put(SysConf.BLOG_LIST, page.getContent());
//        return map;
//    }
//
//}
