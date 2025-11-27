package kr.java.mybatis.controller;

import jakarta.servlet.http.HttpSession;
import kr.java.mybatis.domain.UserInfo;
import kr.java.mybatis.service.PostService;
import kr.java.mybatis.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("posts", postService.getAllPosts());
        return "posts";
    }

    @GetMapping("/create")
    public String create(
            HttpSession session
    ) {
        UserInfo userInfo = (UserInfo) session.getAttribute("loginUser");
        if (userInfo == null) return "redirect:/login";
        return "posts_create";
    }

    @PostMapping("/create")
    public String create(
            @RequestParam String title,
            @RequestParam String content,
            HttpSession session) {
        UserInfo userInfo = (UserInfo) session.getAttribute("loginUser");
        if (userInfo == null) return "redirect:/login";
        postService.createPost(userInfo.getId(), title, content);
        return "redirect:/posts";
    }
}
