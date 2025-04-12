package com.example.blog_system.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity //标记为JPA实体类，对应数据表
@Table(name = "posts")
@Getter
@Setter
public class Post {
    @Id //标记为主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) //主键自增策略
    private Long id;

    @Column(nullable = false, length = 200) //非空，限制长度
    @NotBlank(message = "标题不能为空")
    private String title;

    @Lob //大文本字段
    @Column(nullable = false, columnDefinition = "TEXT") //使用数据库的TEXT类型
    @NotBlank(message = "内容不能为空")
    private String content;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String htmlContent;

    @Column(name = "created_at") //指定数据库列名
    private LocalDateTime createdAt;

    //定义无参构造器，JPA要求
    public Post(){

    }

    //定义有参构造器
    public Post(String title, String content){
        this.title = title;
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }

}
