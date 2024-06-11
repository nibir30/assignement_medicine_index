package com.nibir.medicine_index.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorDetails {

    //    @JsonProperty("status")
    private boolean success;

    @JsonProperty("code")
    private Integer code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("details")
    private String details;

    @JsonProperty("timestamp")
    private String timestamp;

//     @JsonProperty("data")
//     private T data;

    public ErrorDetails(String message) {
        super();
        this.success = false;
        this.code = 400;
        this.message = message;
        this.timestamp = LocalDateTime.now().toString();
    }

    public ErrorDetails(String message, String details) {
        super();
        this.success = false;
        this.code = 400;
        this.message = message;
        this.details = details;
        this.timestamp = LocalDateTime.now().toString();
    }

    public ErrorDetails(String message, Integer code) {
        super();
        this.success = false;
        this.code = code;
        this.message = message;
        this.timestamp = LocalDateTime.now().toString();
    }

    public ErrorDetails(String message, Integer code, String details) {
        super();
        this.success = false;
        this.code = code;
        this.message = message;
        this.details = details;
        this.timestamp = LocalDateTime.now().toString();
    }

    public ErrorDetails(String message, Integer code, boolean success) {
        super();
        this.success = success;
        this.code = code;
        this.message = message;
        this.timestamp = LocalDateTime.now().toString();
    }
}
