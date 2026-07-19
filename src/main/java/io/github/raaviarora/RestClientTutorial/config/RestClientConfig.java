package io.github.raaviarora.RestClientTutorial.config;

import io.github.raaviarora.RestClientTutorial.interceptor.LoggingInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import java.net.http.HttpClient;
import java.time.Duration;

@Configuration
public class RestClientConfig {

    @Bean
    public RestClient getRestClient(@Value("${api.base-url}") String baseUrl,
                                    @Value("${api.connect-timeout}") int connectTimeout,
                                    @Value("${api.read-timeout}") int readTimeout,
                                    @Value("${api.bearer-token}") String bearerToken){

        HttpClient httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(connectTimeout))
                .build();

        JdkClientHttpRequestFactory requestFactory = new JdkClientHttpRequestFactory(httpClient);
        requestFactory.setReadTimeout(Duration.ofSeconds(readTimeout));

        return RestClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeaders(headers -> {
                    headers.setBearerAuth(bearerToken);
                    headers.set("Client-Id", "restclient-tutorial");
                })
                .requestFactory(requestFactory)
                .requestInterceptor(new LoggingInterceptor())
                .build();
    }
}
