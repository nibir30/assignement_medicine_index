package com.nibir.medicine_index.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoDataFoundException extends RuntimeException {
    private static final Long serialVersionUID = 1L;
    private final Integer code;

    public NoDataFoundException(String message) {
        super(message);
        code = 98;
    }

    public NoDataFoundException(String message, Integer code) {
        super(message);
        this.code = code;
    }
}
