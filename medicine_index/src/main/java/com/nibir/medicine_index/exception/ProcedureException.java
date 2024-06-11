package com.nibir.medicine_index.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ProcedureException extends RuntimeException {
    private static final Long serialVersionUID = 1L;

    @JsonProperty("o_status_message")
    private String message;

    @JsonProperty("o_status_code")
    private Integer statusCode;

    @JsonProperty("field")
    private String field;

    public ProcedureException(String message) {
        super(message);
        this.message = message;
        this.statusCode = 99;
        this.field = null;
    }

    public ProcedureException(String message, String field) {
        super(message);
        this.message = message;
        this.statusCode = 99;
        this.field = field;
    }

    public ProcedureException(String message, Integer statusCode, String field) {
        super(message);
        this.message = message;
        this.field = field;
        this.statusCode = statusCode;
    }

    public ProcedureException(String message, Integer statusCode) {
        super(message);
        this.message = message;
        this.field = null;
        this.statusCode = statusCode;
    }
}
