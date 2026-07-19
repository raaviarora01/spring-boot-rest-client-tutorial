package io.github.raaviarora.RestClientTutorial.controller;

import io.github.raaviarora.RestClientTutorial.model.Post;
import io.github.raaviarora.RestClientTutorial.service.PostService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Validated
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/posts/filter")
    public ResponseEntity<List<Post>> getPostsByUserId(@RequestParam Integer userId){
        return ResponseEntity.ok(postService.getPostsByUserId(userId));
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable @Positive(message = "Id must be positive.") Integer id){
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @PostMapping("/posts")
    public ResponseEntity<Post> createPost(@Valid @RequestBody Post post){
        Post createdPost = postService.createPost(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable @Positive(message = "Id must be positive.") Integer id, @Valid @RequestBody Post post){
        return ResponseEntity.ok(postService.updatePost(id, post));
    }

    @PatchMapping("/posts/{id}")
    public ResponseEntity<Post> patchPost(@PathVariable @Positive(message = "Id must be positive.") Integer id, @Valid @RequestBody Map<String, Object> updates){
        return ResponseEntity.ok(postService.patchPost(id, updates));
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable @Positive(message = "Id must be positive.") Integer id){
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}