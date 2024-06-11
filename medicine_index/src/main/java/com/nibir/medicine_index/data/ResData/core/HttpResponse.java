package com.nibir.medicine_index.data.ResData.core;

import org.springframework.stereotype.Component;

/**
 * @author razu ahmmed
 */

@Component
public class HttpResponse {

//    private final HttpStatus httpStatusOk = HttpStatus.OK;
//    private final LanguageService languageService;
//    private final UserMapper userMapper;
//
//    public HttpResponse(LanguageService languageService, UserMapper userMapper) {
//        this.languageService = languageService;
//        this.userMapper = userMapper;
//    }
//
//    public NotFoundException notFoundException(@NonNull String messageCode) {
//        String message = getMsgByCode(messageCode);
//        throw new NotFoundException(message, message);
//    }
//
//    public MismatchException mismatchException(@NonNull String messageCode) {
//        String message = getMsgByCode(messageCode);
//        throw new MismatchException(message, message);
//    }
//
//    public DataTransactionException dataTransactionError(@NonNull String messageCode, @NonNull Exception exception) {
//        exception.printStackTrace();
//        String errorMsg = exception.getMessage();
//        String message = getMsgByCode(messageCode);
//        throw new DataTransactionException(message, errorMsg);
//    }
//
//    public SuccessResponse successResponse(
//            @NonNull String messageCode,
//            Object data
//    ) {
//        return SuccessResponse.builder()
//                .status(true)
//                .statusCode(httpStatusOk.value())
//                .message(getMsgByCode(messageCode))
//                .dateTime(DateTimeFormat.currentEDmyHmsAzDateTime.get())
//                .data(data)
//                .build();
//    }
//
//    public ResponseEntity<SuccessResponse> successResponse(
//            SuccessResponse successResponse
//    ) {
//        return ResponseEntity.status(httpStatusOk).contentType(APPLICATION_JSON).headers(buildHttpHeaders()).body(successResponse);
//    }
//
//    public ResponseEntity<ErrorResponse> errorResponse(
//            @NonNull HttpStatus httpStatus,
//            String... message
//    ) {
//        ErrorResponse errorResponse = ErrorResponse.builder()
//                .status(false)
//                .statusCode(httpStatus.value())
//                .message(message[0])
//                .errorMessage(message[1])
//                .dateTime(DateTimeFormat.currentEDmyHmsAzDateTime.get())
//                .build();
//        return errorResponse(errorResponse);
//    }
//
//    public ResponseEntity<ErrorResponse> errorResponse(
//            ErrorResponse errorResponse
//    ) {
//        return ResponseEntity.status(httpStatusOk).contentType(APPLICATION_JSON).headers(buildHttpHeaders()).body(errorResponse);
//    }
//
//    public HttpHeaders buildHttpHeaders() {
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add(DATE, DateTimeFormat.currentEDmyHmsAzDateTime.get());
//        httpHeaders.setCacheControl(CacheControl.noCache().getHeaderValue());
//        httpHeaders.setContentType(APPLICATION_JSON);
//        return httpHeaders;
//    }
//
//    public String getMsgByCode(@NonNull String messageCode) {
//        return languageService.getMsgByCode(messageCode);
//    }
//
//    public Long getUserId(Principal principal) {
//        if (NullHandlerUtils.isNull.apply(principal)) {
//            throw notFoundException("user.not.found");
//        }
//        User user = userMapper.findByUserId(principal.getName());
//        if (NullHandlerUtils.isNull.apply(user)) {
//            throw notFoundException("user.not.found");
//        }
//        return user.getId();
//    }
}