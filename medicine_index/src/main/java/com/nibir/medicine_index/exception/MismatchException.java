package com.nibir.medicine_index.exception;

/**
 * @author razu ahmmed
 */

public class MismatchException extends RuntimeException {

    private final String message;
    private final String errorMessage;

    public MismatchException(String message, String errorMessage) {
        super();
        this.message = message;
        this.errorMessage = errorMessage;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}