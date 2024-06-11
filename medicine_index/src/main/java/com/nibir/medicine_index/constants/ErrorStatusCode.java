package com.nibir.medicine_index.constants;

import java.io.Serializable;

public enum ErrorStatusCode implements Serializable {

    COMMON_SERVER_ERROR("AN UNEXPECTED ERROR OCCURRED, PLEASE TRY AGAIN LATER!", 99),

    // Authentication error
    JWT_TOKEN_EXPIRED("YOUR TOKEN HAS EXPIRED, PLEASE LOGIN AGAIN", 99),
    USER_NOT_ACTIVE("YOUR ARE NOT ACTIVE YET", 97),
    PASSWORD_EXPIRY("YOUR PASSWORD IS EXPIRY", 96),
    USER_BLOCKED("USER IS BLOCK, PLEASE CONTACT ADMINISTRATOR", 99),
    INVALID_USER_PASSWORD("INVALID USER NAME OR PASSWORD", 99),
    INVALID_USER("INVALID USER", 99),
    INVALID_PASSWORD("INVALID PASSWORD", 99),
    NOT_AUTHORIZED("SORRY! YOU ARE NOT AUTHORIZED", 401),
    USERNAME_PASSWORD_NOT_BLANK("USERNAME OR PASSWORD CAN NOT BLANK", 901),
    USER_NOT_FOUND("USER NOT FOUND", 904),
    SSO_LOGIN_FAILED("SSO LOGIN FAILED", 905);


    /*USERNAME_PASSWORD_NOT_BLANK("USERNAME OR PASSWORD CAN NOT BLANK", 90);*/


    private final String value;
    private final Integer code;

    ErrorStatusCode(String value, Integer code) {
        this.value = value;
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public Integer getCode() {
        return code;
    }

}
