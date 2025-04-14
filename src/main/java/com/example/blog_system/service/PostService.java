package com.example.blog_system.service;

import com.example.blog_system.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {
    //保存文章
    Post savePost(Post post);

    //获取所有文章
    List<Post> getAllPosts();

    //根据文章id获取文章
    Post getPostById(Long id);

    // 根据文章标题获取文章
    List<Post> getPostByTitle(String title);

    // 删除文章
    void deletePost(Long id);

    // 添加业务逻辑扩展
    // 自定义分页查询
    // PostService.java
    Page<Post> getAllPostsByPage(Pageable pageable);
    // 使用排序后的顺序分页
    Page<Post> getPostsPagedBySort(int page, int size, String sortBy, String sortDir);

    // 根据关键词搜索文章
    List<Post> searchPostsByKeyword(String keyword);

    // 获取内容摘要
    String getContentSummary(String htmlContent, int maxLength);
}
