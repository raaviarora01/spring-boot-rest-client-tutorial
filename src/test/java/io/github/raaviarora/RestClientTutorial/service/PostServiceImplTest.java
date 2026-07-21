package io.github.raaviarora.RestClientTutorial.service;

import io.github.raaviarora.RestClientTutorial.model.Post;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestClient;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PostServiceImplTest {

    @Mock
    private RestClient restClient;

    @Mock
    private Validator validator;

    @SuppressWarnings({"rawTypes", "unchecked"})
    @Mock
    private RestClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private RestClient.ResponseSpec responseSpec;

    @Mock
    private RestClient.RequestBodyUriSpec requestBodyUriSpec;

    @InjectMocks
    private PostServiceImpl postService;

    @Test
    void shouldReturnPostById(){
        Post post = new Post();
        post.setId(1);
//        post.setUserId(23);
        post.setTitle("Spring");
//        post.setBody("Learning Spring");

        doReturn(requestHeadersUriSpec)
                .when(restClient)
                .get();

        when(requestHeadersUriSpec.uri("/posts/{id}", 1))
                .thenReturn(requestHeadersUriSpec);

        when(requestHeadersUriSpec.retrieve())
                .thenReturn(responseSpec);

        when(responseSpec.onStatus(any(), any()))
                .thenReturn(responseSpec);

        when(responseSpec.body(Post.class))
                .thenReturn(post);

        when(validator.validate(post))
                .thenReturn(Collections.emptySet());

        Post result = postService.getPostById(1);
        assertEquals(1, result.getId());
        assertEquals("Spring", result.getTitle());

        verify(restClient).get();

    }
}
