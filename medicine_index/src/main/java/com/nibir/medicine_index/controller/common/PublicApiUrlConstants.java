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
    public final static String GET_USERS_BY_BLOOD_GROUP = USERS + "/blood-group" + PATH_VAR_ID;
    public final static String GET_USERS_FROM_ALL_BLOOD_GROUP = USERS + "/blood-group";
    public final static String BLOOD_BANK = API_URI_PREFIX + "/blood-bank";

    public final static String GET_ACTIVE_BLOOD_BANK = BLOOD_BANK + "/active";
    public final static String DELETE_BLOOD_BANK = BLOOD_BANK + "/delete" + PATH_VAR_ID;
    public final static String GET_ALL_BLOOD_BANK = BLOOD_BANK;
    public final static String SAVE_MEDIA = API_URI_PREFIX + "/save-media";
    public final static String MEDIA = API_URI_PREFIX + "/media" + PATH_VAR_ID;


//    public static final String GET_NAVBAR_MENUS = API_URI_PREFIX + NAVBAR + "/getAllNavbarMenus" + PATH_VAR_ID;

}

