package com.nibir.medicine_index.util;

import com.nibir.medicine_index.data.ResData.core.ResponseBaseData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public class ResponseUtils {
    public static ResponseEntity<?> validationError(String errorMessage) {
        return new ResponseEntity<ResponseBaseData<?>>(ResponseBaseData.builder()
                .code(400)
                .success(false)
                .message(errorMessage)
                .timestamp(LocalDateTime.now())
                .build(), HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<?> success(String message) {
        return new ResponseEntity<ResponseBaseData<?>>(ResponseBaseData.builder()
                .code(200)
                .success(true)
                .message(message)
                .timestamp(LocalDateTime.now())
                .build(), HttpStatus.OK);
    }

    public static ResponseEntity<?> exceptionError(String errorMessage, String exceptionDetails) {
        return new ResponseEntity<ResponseBaseData<?>>(ResponseBaseData.builder()
                .code(400)
                .success(false)
                .message(errorMessage)
                .details(exceptionDetails)
                .timestamp(LocalDateTime.now())
                .build(), HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<?> dataSuccess(String message, Object data) {
        return new ResponseEntity<ResponseBaseData<?>>(ResponseBaseData.builder()
                .code(200)
                .success(true)
                .message(message)
                .data(data)
                .timestamp(LocalDateTime.now())
                .build(), HttpStatus.OK);
    }
}
