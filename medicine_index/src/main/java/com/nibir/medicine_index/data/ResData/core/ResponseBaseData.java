package com.nibir.medicine_index.data.ResData.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseBaseData<T> {
    @JsonProperty("isSuccess")
    private Boolean success;

    @JsonProperty("messageCode")
    private Integer code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("developerMessage")
    private String details;

    @JsonProperty("timestamp")
    private LocalDateTime timestamp;

    @JsonProperty("data")
    private T data;

    public ResponseBaseData(String message, T data) {
        this.success = true;
        this.code = 200;
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.data = data;
    }

    public ResponseBaseData(String message, T data, Integer code) {
        this.success = true;
        this.code = code;
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.data = data;
    }

    public ResponseBaseData(Boolean success, String message, T data, Integer code) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.data = data;
    }
}
