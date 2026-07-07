package io.github.raaviarora.RestClientTutorial.controller;

import io.github.raaviarora.RestClientTutorial.model.Post;
import io.github.raaviarora.RestClientTutorial.service.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public List<Post> getPosts() {
        return postService.getAllPosts();
    }
}