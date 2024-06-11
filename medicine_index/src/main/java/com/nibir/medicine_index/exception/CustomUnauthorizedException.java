package com.nibir.medicine_index.exception;

public class CustomUnauthorizedException extends RuntimeException{
//    private static final Long serialVersionUID = 1L;
    private final Integer code;

    public CustomUnauthorizedException(String message) {
        super(message);
        this.code=400;
    }
    public CustomUnauthorizedException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
