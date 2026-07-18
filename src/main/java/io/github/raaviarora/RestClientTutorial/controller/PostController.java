package io.github.raaviarora.RestClientTutorial.controller;

import io.github.raaviarora.RestClientTutorial.model.Post;
import io.github.raaviarora.RestClientTutorial.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Post>> getPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Integer id){
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @PostMapping("/posts")
    public ResponseEntity<Post> createPost(@RequestBody Post post){
        Post createdPost = postService.createPost(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Integer id, @RequestBody Post post){
        return ResponseEntity.ok(postService.updatePost(id, post));
    }

    @PatchMapping("/posts/{id}")
    public ResponseEntity<Post> patchPost(@PathVariable Integer id, @RequestBody Map<String, Object> updates){
        return ResponseEntity.ok(postService.patchPost(id, updates));
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Integer id){
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}