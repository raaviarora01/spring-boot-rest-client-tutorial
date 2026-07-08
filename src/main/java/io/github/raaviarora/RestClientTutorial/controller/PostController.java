package io.github.raaviarora.RestClientTutorial.controller;

import io.github.raaviarora.RestClientTutorial.model.Post;
import io.github.raaviarora.RestClientTutorial.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @GetMapping("/posts/{id}")
    public Post getPostById(@PathVariable Integer id){
        return postService.getPostById(id);
    }

    @PostMapping("/posts")
    public Post createPost(@RequestBody Post post){
        return postService.createPost(post);
    }

    @PutMapping("/posts/{id}")
    public Post updatePost(@PathVariable Integer id, @RequestBody Post post){
        return postService.updatePost(id, post);
    }

    @PatchMapping("/posts/{id}")
    public Post patchPost(@PathVariable Integer id, @RequestBody Map<String, Object> updates){
        return postService.patchPost(id, updates);
    }
}