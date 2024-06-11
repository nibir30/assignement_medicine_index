package com.nibir.medicine_index.exception.handler;
//package com.cnsbd.cpa_inventory_api.exception.handler;

import com.nibir.medicine_index.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.format.DateTimeParseException;

@ControllerAdvice
public class GlobalExceptionHandler {

    // handling resource not found exception
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundHandling(ResourceNotFoundException exception, WebRequest request) {
        exception.printStackTrace();
        ErrorDetails errorDetails = getErrorDetails(exception.getMessage(), request, null);
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    // handling no data found exception
    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<?> noDataFoundExceptionHandler(NoDataFoundException exception, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(exception.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    // handling argumentNotValid exception
    @ExceptionHandler(ArgumentNotValidException.class)
    public ResponseEntity<?> argumentNotValidHandling(ArgumentNotValidException exception, WebRequest request) {
        exception.printStackTrace();
        ErrorDetails errorDetails = new ErrorDetails(exception.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    // handling field exception
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        ex.printStackTrace();
        ObjectError objectError = ex.getBindingResult().getAllErrors().stream().findFirst().orElse(null);
        String message = objectError != null ? objectError.getDefaultMessage() : "Invalid request";
        ErrorDetails errorDetails = new ErrorDetails(message);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }


    // handling global exception
    @ExceptionHandler(CommonServerException.class)
    public ResponseEntity<?> serverExceptionHandling(CommonServerException exception, WebRequest request) {
        exception.printStackTrace();
        ErrorDetails errorDetails = getErrorDetails(exception.getMessage(), request, exception.toString());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UnauthorizedUserException.class)
    public ResponseEntity<?> unauthorizedUserExceptionHandling(UnauthorizedUserException exception) {
        exception.printStackTrace();
        ErrorDetails errorDetails = exception.getCode() != null
                ? new ErrorDetails(exception.getMessage(), exception.getCode())
                : new ErrorDetails(exception.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(CustomUnauthorizedException.class)
    public ResponseEntity<?> customUnauthorizedExceptionHandling(CustomUnauthorizedException exception) {
        exception.printStackTrace();
        ErrorDetails errorDetails = exception.getCode() != null
                ? new ErrorDetails(exception.getMessage(), exception.getCode())
                : new ErrorDetails(exception.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
    // --implemented!!

    private ErrorDetails getErrorDetails(String description, WebRequest request, String details) {
        String additionalDesc = details + " [" + request.getDescription(false) + "]";
        return new ErrorDetails(description, additionalDesc);
    }

    // ----------------------------------------------------

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<?> dateTimeParseExceptionHandling(Exception ex,
                                                            WebRequest request) {
        ex.printStackTrace();
        ErrorDetails errorDetails = getErrorDetails(ex.getMessage(), request,
                ex.toString());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CrudException.class)
    public ResponseEntity<?> crudExceptionHandler(CrudException ex, WebRequest request) {
        ex.printStackTrace();
        ErrorDetails errorDetails = getErrorDetails(ex.getMessage(), request,
                ex.toString());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> numberFormatExceptionHandler(MethodArgumentTypeMismatchException ex,
                                                          WebRequest request) {
        ex.printStackTrace();
        String details = "[Field: " + ex.getName() + "], [Error: " + ex.getMessage()
                + "]";
        ErrorDetails errorDetails = getErrorDetails("INVALID REQUEST!", request,
                details);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    // handling file upload exception
    @ExceptionHandler(FileUploadException.class)
    public ResponseEntity<?> fileUploadExceptionHandler(FileUploadException ex,
                                                        WebRequest request) {
        ex.printStackTrace();
        ErrorDetails errorDetails = getErrorDetails(ex.getMessage(), request,
                ex.toString());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    // handling procedure exception
    @ExceptionHandler(ProcedureException.class)
    public ResponseEntity<?> produreExceptionHandler(ProcedureException ex,
                                                     WebRequest request) {
        ex.printStackTrace();
        /*ErrorDetails errorDetails = getErrorDetails(ex.getMessage(), request,
                ex.toString());*/
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), ex.getStatusCode(), ex.getField());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    // handling global exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandling(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = getErrorDetails("SOMETHING WENT WRONG! PLEASE TRY AGAIN LATER.", request,
                ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}