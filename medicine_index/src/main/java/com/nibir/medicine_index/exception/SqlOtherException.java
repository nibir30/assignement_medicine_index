package com.nibir.medicine_index.exception;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@NoArgsConstructor
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class SqlOtherException extends RuntimeException {

    @JsonProperty("message")
    private String message;

    @JsonProperty("statusCode")
    private Integer statusCode;

    public SqlOtherException(String message) {
        super(message);
        this.message = message;
        this.statusCode = 99;
    }

    public SqlOtherException(String message, Integer statusCode) {
        super(message);
        this.message = message;
        this.statusCode = statusCode;
    }
}