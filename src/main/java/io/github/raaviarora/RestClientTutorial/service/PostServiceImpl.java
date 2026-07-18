package io.github.raaviarora.RestClientTutorial.service;

import io.github.raaviarora.RestClientTutorial.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final RestClient restClient;

    @Override
    public List<Post> getAllPosts() {
        return restClient.get()
                .uri("/posts")
                .retrieve()
                .body(new ParameterizedTypeReference<List<Post>>() {});
    }

    @Override
    public Post getPostById(Integer id) {
        return restClient.get()
                .uri("/posts/{id}", id)
                .retrieve()
                .body(Post.class);
    }

    @Override
    public Post createPost(Post post) {
        return restClient.post()
                .uri("/posts")
                .body(post)
                .retrieve()
                .body(Post.class);
    }

    @Override
    public Post updatePost(Integer id, Post post) {
        return restClient.put()
                .uri("/posts/{id}", id)
                .body(post)
                .retrieve()
                .body(Post.class);
    }

    @Override
    public Post patchPost(Integer id, Map<String, Object> updates) {
        return restClient.patch()
                .uri("/posts/{id}", id)
                .body(updates)
                .retrieve()
                .body(Post.class);
    }

    @Override
    public void deletePost(Integer id) {
        restClient.delete()
                .uri("/posts/{id}", id)
                .retrieve()
                .toBodilessEntity();
    }


}
