package io.github.raaviarora.RestClientTutorial.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.lang.reflect.Method;

public class LoggingInterceptor implements ClientHttpRequestInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

        logger.info("Outgoing request");
        logger.info("Method : {}", request.getMethod());
        logger.info("URL : {}", request.getURI());
        logger.info("Headers : {}", request.getHeaders());

        if(body.length > 0){
            logger.info("Body : {}", new String(body));
        }

        ClientHttpResponse response = execution.execute(request, body);
        logger.info("Incoming Response");
        logger.info("Status : {}", response.getStatusCode());

        return response;
    }
}
