package com.example.blog_system.repository;

import com.example.blog_system.model.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository //标记为Repository层
public interface PostRepository extends JpaRepository<Post, Long> {
    // 无需编写方法，继承的JpaRepository已提供基础CRUD操作

    //自定义方法
    // 方法名自动解析为SQL：SELECT * FROM posts WHERE title = ?
    List<Post> findByTitle(String title);

    // 模糊查询：SELECT * FROM posts WHERE title,content LIKE '%keyword%'
    List<Post> findByTitleContainingOrContentContaining(String title, String content);

    // 使用JPQL自定义复杂查询
    @Query("SELECT p FROM Post p WHERE p.createdAt > :startDate")
    List<Post> findPostsAfterDate(@Param("startDate")LocalDateTime startDate);

    //分页查询
    List<Post> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
