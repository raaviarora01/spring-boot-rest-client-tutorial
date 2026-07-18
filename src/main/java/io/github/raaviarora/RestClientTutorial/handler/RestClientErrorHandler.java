package io.github.raaviarora.RestClientTutorial.handler;

import io.github.raaviarora.RestClientTutorial.exception.ExternalServiceException;
import io.github.raaviarora.RestClientTutorial.exception.ResourceNotFoundException;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class RestClientErrorHandler {
    public static void handle(HttpRequest req, ClientHttpResponse res) throws IOException {
        HttpStatusCode status = res.getStatusCode();

        if(status.value() == 404){
            throw new ResourceNotFoundException("Requested Resource Not Found");
        }
        if(status.is5xxServerError()){
            throw new ExternalServiceException("External API unavailable");
        }
    }
}
