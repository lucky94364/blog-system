package com.example.blog_system.service.impl;

import com.example.blog_system.model.Post;
import com.example.blog_system.repository.PostRepository;
import com.example.blog_system.service.PostService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // 标记为Spring管理的Service组件
@Transactional // 标记为支持事务,类级别的事务管理（所有方法默认开启事务）(方法执行失败时自动会滚数据库操作)
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Autowired // 构造器注入（推荐方式）
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    @Override
    public Post savePost(Post post) {
        //业务逻辑示例：保存前检查标题是否为空
        if(post.getTitle() == null || post.getTitle().trim().isEmpty()){
            throw new IllegalArgumentException("文章标题不能为空");
        }
        return postRepository.save(post);
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("未找到文章ID：" + id));
    }

    @Override
    public List<Post> getPostByTitle(String title) {
        return postRepository.findByTitle(title);
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    // 添加其他业务逻辑方法
    @Override
    public Page<Post> getAllPostsByPage(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Override
    public List<Post> searchPostsByKeyword(String keyword) {
        return postRepository.findByTitleContainingOrContentContaining(keyword, keyword);
    }
}
