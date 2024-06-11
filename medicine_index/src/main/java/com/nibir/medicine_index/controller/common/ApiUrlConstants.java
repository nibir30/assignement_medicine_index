package com.nibir.medicine_index.controller.common;

public class ApiUrlConstants {

    public final static String API_URI_PREFIX = "/api/v1";
    public final static String PATH_VAR_ID = "/{id}";

    public final static String AUTH_API = API_URI_PREFIX + "/auth";

    public final static String BLOOD_BANK = API_URI_PREFIX + "/blood-bank";

    public final static String ADD_BLOOD_BANK = BLOOD_BANK + "/add";
    public final static String GET_USER_BY_EMAIL = API_URI_PREFIX + "/user/email" + PATH_VAR_ID;
    public final static String UPDATE_PROFILE = API_URI_PREFIX + "/profile/update";
}
