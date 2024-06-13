package com.nibir.medicine_index.controller.common;

public class PublicApiUrlConstants {
    public final static String API_URI_PREFIX = "/api/v1/public";
    public final static String PATH_VAR_ID = "/{id}";
    public final static String AUTH_API = API_URI_PREFIX + "/auth";

    public final static String REGISTER_USER = AUTH_API + "/register";
    public final static String LOGIN_USER = AUTH_API + "/login";
    public final static String HELLO_WORLD = API_URI_PREFIX + "/hello";
    public final static String USERS = API_URI_PREFIX + "/users";
    public final static String GET_ALL_USERS = USERS;
    public final static String SAVE_MEDIA = API_URI_PREFIX + "/save-media";
    public final static String MEDIA = API_URI_PREFIX + "/media" + PATH_VAR_ID;

    public final static String MEDICINE = API_URI_PREFIX + "/medicine";
    public final static String GET_MEDICINE = MEDICINE + "/get-all";

    public final static String MANUFACTURER = API_URI_PREFIX + "/manufacturer";
    public final static String GET_MANUFACTURER = MANUFACTURER + "/get-all";


//    public static final String GET_NAVBAR_MENUS = API_URI_PREFIX + NAVBAR + "/getAllNavbarMenus" + PATH_VAR_ID;

}

