package com.nibir.medicine_index.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ArgumentNotValidException extends RuntimeException {

    private static final Long serialVersionUID = 1L;
    private final Integer code;

    public ArgumentNotValidException(String message) {
        super(message);
        this.code = 98;
    }

    public ArgumentNotValidException(String message, Integer code) {
        super(message);
        this.code = code;
    }
}
