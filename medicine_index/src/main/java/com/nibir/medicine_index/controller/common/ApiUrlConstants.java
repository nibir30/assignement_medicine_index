package com.nibir.medicine_index.controller.common;

public class ApiUrlConstants {

    public final static String API_URI_PREFIX = "/api/v1";
    public final static String PATH_VAR_ID = "/{id}";
    public final static String GET_USER_BY_EMAIL = API_URI_PREFIX + "/user/email" + PATH_VAR_ID;
    public final static String UPDATE_PROFILE = API_URI_PREFIX + "/profile/update";

    public final static String MEDICINE = API_URI_PREFIX + "/medicine";
    public final static String SAVE_MEDICINE = MEDICINE + "/save";
    public final static String DELETE_MEDICINE = MEDICINE + "/delete" + PATH_VAR_ID;

    public final static String MANUFACTURER = API_URI_PREFIX + "/manufacturer";
    public final static String SAVE_MANUFACTURER = MANUFACTURER + "/save";
    public final static String DELETE_MANUFACTURER = MANUFACTURER + "/delete" + PATH_VAR_ID;
}
