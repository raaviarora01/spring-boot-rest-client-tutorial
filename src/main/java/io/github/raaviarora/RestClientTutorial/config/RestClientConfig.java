package io.github.raaviarora.RestClientTutorial.config;

import io.github.raaviarora.RestClientTutorial.interceptor.LoggingInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Value("${api.base-url}")
    private String baseUrl;

    @Bean
    public RestClient getRestClient(){
        return RestClient.builder()
                .baseUrl(baseUrl)
                .requestInterceptor(new LoggingInterceptor())
                .build();
    }
}
