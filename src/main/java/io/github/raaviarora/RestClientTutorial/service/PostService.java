package io.github.raaviarora.RestClientTutorial.service;

import io.github.raaviarora.RestClientTutorial.model.Post;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface PostService {
    List<Post> getAllPosts();

    Post getPostById(Integer id);

    Post createPost(Post post);

    Post updatePost(Integer id, Post post);

    Post patchPost(Integer id, Map<String, Object> updates);

    void deletePost(Integer id);
}
