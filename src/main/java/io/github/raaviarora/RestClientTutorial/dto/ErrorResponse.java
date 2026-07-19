package io.github.raaviarora.RestClientTutorial.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;

@AllArgsConstructor
@Getter
public class ErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String msg;
    private String path;
    private Map<String, String> fieldErrors;

    public ErrorResponse(LocalDateTime timestamp, int status, String error, String msg, String path){
        this.timestamp = timestamp;
        this.status = status;
        this.error =  error;
        this.msg = msg;
        this.path = path;
    }
}
