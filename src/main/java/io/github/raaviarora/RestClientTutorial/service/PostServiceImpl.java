package io.github.raaviarora.RestClientTutorial.service;

import io.github.raaviarora.RestClientTutorial.exception.ExternalServiceException;
import io.github.raaviarora.RestClientTutorial.exception.ResourceNotFoundException;
import io.github.raaviarora.RestClientTutorial.handler.RestClientErrorHandler;
import io.github.raaviarora.RestClientTutorial.model.Post;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final Validator validator;
    private final RestClient restClient;

    @Override
    public List<Post> getAllPosts() {
        return restClient.get()
                .uri("/posts")
                .retrieve()
                .onStatus(
                        HttpStatusCode::isError,
                        RestClientErrorHandler::handle
                )
                .body(new ParameterizedTypeReference<List<Post>>() {});
    }

    @Override
    public List<Post> getPostsByUserId(Integer id) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/posts")
                        .queryParam("userId", id)
                        .build())
                .retrieve()
                .onStatus(
                        HttpStatusCode::isError,
                        RestClientErrorHandler::handle
                )
                .body(new ParameterizedTypeReference<List<Post>>() {});
    }

    @Override
    public Post getPostById(Integer id) {
        Post post = restClient.get()
                .uri("/posts/{id}", id)
                .retrieve()
                .onStatus(
                        HttpStatusCode::isError,
                        RestClientErrorHandler::handle
                )
                .body(Post.class);

        validateResponse(post);
        return post;
    }

    @Override
    public Post createPost(Post post) {
        Post createdPost = restClient.post()
                .uri("/posts")
                .body(post)
                .retrieve()
                .onStatus(
                        HttpStatusCode::isError,
                        RestClientErrorHandler::handle
                )
                .body(Post.class);

        validateResponse(createdPost);
        return createdPost;
    }

    @Override
    public Post updatePost(Integer id, Post post) {
        Post updatedPost = restClient.put()
                .uri("/posts/{id}", id)
                .body(post)
                .retrieve()
                .onStatus(
                        HttpStatusCode::isError,
                        RestClientErrorHandler::handle
                )
                .body(Post.class);

        validateResponse(updatedPost);
        return updatedPost;
    }

    @Override
    public Post patchPost(Integer id, Map<String, Object> updates) {
        Post updatedPost = restClient.patch()
                .uri("/posts/{id}", id)
                .body(updates)
                .retrieve()
                .onStatus(
                        HttpStatusCode::isError,
                        RestClientErrorHandler::handle
                )
                .body(Post.class);

        validateResponse(updatedPost);
        return updatedPost;
    }

    @Override
    public void deletePost(Integer id) {
        restClient.delete()
                .uri("/posts/{id}", id)
                .retrieve()
                .onStatus(
                        HttpStatusCode::isError,
                        RestClientErrorHandler::handle
                )
                .toBodilessEntity();
    }

    public void validateResponse(Post post){
        Set<ConstraintViolation<Post>> violations = validator.validate(post);

        if(!violations.isEmpty()){
            String meaasge = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(", "));

            throw new ExternalServiceException(meaasge);
        }
    }

}
