package com.nibir.medicine_index.exception;

/**
 * @author razu ahmmed
 */

public class ObjectValidationException extends RuntimeException {

    private final String message;
    private final String errorMessage;
    private final String field;

    public ObjectValidationException(String message, String errorMessage, String field) {
        super();
        this.message = message;
        this.errorMessage = errorMessage;
        this.field = field;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getField() {
        return field;
    }
}