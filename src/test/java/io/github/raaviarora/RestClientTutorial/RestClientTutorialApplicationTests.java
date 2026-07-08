package io.github.raaviarora.RestClientTutorial;

import io.github.raaviarora.RestClientTutorial.model.Post;
import io.github.raaviarora.RestClientTutorial.service.PostService;
import io.github.raaviarora.RestClientTutorial.service.PostServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class RestClientTutorialApplicationTests {

    @Autowired
    private PostService postService;

	@Test
	void contextLoads() {
	}

    @Test
    void getAllPosts(){
        List<Post> list = postService.getAllPosts();
        System.out.println(list);
    }

    @Test
    void getPostById(){
        Post p = postService.getPostById(1);
        System.out.println(p);
    }

}
