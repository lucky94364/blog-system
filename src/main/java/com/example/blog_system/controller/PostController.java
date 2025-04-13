package com.example.blog_system.controller;

import com.example.blog_system.model.Post;
import com.example.blog_system.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.io.FilenameUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/posts") // 所有方法路径以/posts开头
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 显示所有文章列表页面
    @GetMapping
    public String listPosts(
            @RequestParam(defaultValue = "0") int page, // 默认为第一页
            @RequestParam(defaultValue = "9") int size, // 每页显示10条数据
            Model model
    ) {
        Page<Post> postPage = postService.getAllPostsByPage(PageRequest.of(page, size));
        model.addAttribute("postsPage", postPage);
        return "posts/list"; // 对应 templates/posts/list.html
    }

    // 显示文章详情页面
    @GetMapping("/{id}")
    public String viewPost(@PathVariable Long id, Model model) {
        Post post = postService.getPostById(id);
        model.addAttribute("post", post);
        return "posts/view";
    }

    // 显示创建新文章的表单
    @GetMapping("/new")
    public String showCreateFrom(Model model){
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    // 处理表单提交，创建新文章
    @PostMapping
    public String createPost(@Valid @ModelAttribute("post") Post post,  //触发JSR-303数据验证（需在实体类中添加校验注解，如 @NotBlank）
                             BindingResult result,
                             Model model
    ) {
        if (result.hasErrors()){
            return "posts/create"; //返回表单页面，显示错误信息
        }
        postService.savePost(post);
        return "redirect:/posts"; // 创建成功后重定向到文章列表页
    }

    // 显示编辑文章的表单
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Post post = postService.getPostById(id);
        model.addAttribute("post", post);
        return "posts/edit";
    }

    // 处理表单提交，更新文章
    @PostMapping("/{id}")
    public String updatePost(@PathVariable Long id,
                             @Valid @ModelAttribute("post") Post post,
                             BindingResult result,
                             Model model) {
        if (result.hasErrors()){
            return "posts/edit"; //返回表单页面，显示错误信息
        }
        post.setId(id);
        postService.savePost(post);
        return "redirect:/posts"; // 更新成功后重定向到文章列表页
    }

    // 删除文章
    @PostMapping("/{id}/delete")
    @ResponseBody // 仅用于测试，正式环境可重定向
    public String deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return "redirect:/posts"; // 删除成功后重定向到文章列表页
    }

    // 增加图片
    @PostMapping("/upload-image")
    public Map<String, Object> uploadImage(
        @RequestParam("file") MultipartFile file,
        HttpServletRequest request
    ){
        Map<String, Object> result = new HashMap<>();
        try{
            //生成唯一文件名
            String filename = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
            //保存路径（/static/images）
            String savePath = request.getServletContext().getRealPath("/static/images");
            File destFile = new File(savePath, filename);

            file.transferTo(destFile);
            result.put("success", 1);
            result.put("message","上传成功");
            result.put("url", "/images/" + filename);
        }catch (Exception e){
            result.put("success", 0);
            result.put("message", "上传失败" + e.getMessage());
        }
        return result;
    }
}
