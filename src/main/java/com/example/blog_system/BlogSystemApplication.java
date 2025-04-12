package com.example.blog_system;

import com.example.blog_system.model.Post;
import com.example.blog_system.repository.PostRepository;
import com.example.blog_system.service.PostService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class BlogSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogSystemApplication.class, args);
	}

	@Bean //临时测试：应用启动后执行
	public CommandLineRunner demo(PostService postService) {
		return (args) -> {

			List<Post> hw_posts = postService.getPostByTitle("Hello World");
			hw_posts.forEach(post -> postService.deletePost(
					post.getId()
			));

			// 查询所有文章
			List<Post> posts = postService.getAllPosts();
			posts.forEach(post -> System.out.println("标题：" + post.getTitle()));
		};
	}

}
