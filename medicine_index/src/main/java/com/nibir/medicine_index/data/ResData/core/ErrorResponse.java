package com.nibir.medicine_index.data.ResData.core;

import lombok.Builder;
import lombok.Data;

/**
 * @author razu ahmmed
 */

@Data
@Builder
public class ErrorResponse {
    private boolean status;
    private int statusCode;
    private String message;
    private String errorMessage;
    private String dateTime;
    private Object data;
}