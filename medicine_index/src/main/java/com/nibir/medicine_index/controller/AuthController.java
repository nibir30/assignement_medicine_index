package com.nibir.medicine_index.controller;

import com.nibir.medicine_index.controller.common.ApiUrlConstants;
import com.nibir.medicine_index.controller.common.PublicApiUrlConstants;
import com.nibir.medicine_index.data.ReqData.LoginReqData;
import com.nibir.medicine_index.data.ReqData.UserRegisterReqData;
import com.nibir.medicine_index.data.ResData.RegistrationResData;
import com.nibir.medicine_index.service.UserService;
import com.nibir.medicine_index.util.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class AuthController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = PublicApiUrlConstants.REGISTER_USER, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> registerUser(@RequestBody UserRegisterReqData user) {
        try {
            return userService.saveUser(user);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseUtils.exceptionError("Error registering", e.getMessage());
        }
    }

    @RequestMapping(value = ApiUrlConstants.GET_USER_BY_EMAIL, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getUserByEmail(@PathVariable("id") String email) {
        try {
            return userService.findUserByEmail(email);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseUtils.exceptionError("Error getting users", e.getMessage());
        }
    }

    @RequestMapping(value = ApiUrlConstants.UPDATE_PROFILE, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> getUserByEmail(@RequestBody RegistrationResData registrationResData) {
        try {
            return userService.updateProfile(registrationResData);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseUtils.exceptionError("Error getting users", e.getMessage());
        }
    }

    @RequestMapping(value = PublicApiUrlConstants.GET_USERS_BY_BLOOD_GROUP, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getUsersByBloodGroup(@PathVariable("id") String bloodGroup) {
        try {
            return userService.getAllUserByBloodGroup(bloodGroup);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseUtils.exceptionError("Error getting users", e.getMessage());
        }
    }

    @RequestMapping(value = PublicApiUrlConstants.GET_USERS_FROM_ALL_BLOOD_GROUP, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getAllDonors() {
        try {
            return userService.getAllUserByBloodGroup(null);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseUtils.exceptionError("Error getting donors", e.getMessage());
        }
    }

    @RequestMapping(value = PublicApiUrlConstants.GET_ALL_USERS, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getAllUsers() {
        try {
            return userService.getAllUser();
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseUtils.exceptionError("Error getting users", e.getMessage());
        }
    }

    //    @RequestMapping(value = PublicApiUrlConstants.LOGIN_USER, method = RequestMethod.POST)
//    @ResponseBody
//    public ResponseEntity<?> login(@RequestBody LoginReqData loginReqData) {
//        try {
//            return userService.login(loginReqData);
//        } catch (Exception e) {
//            log.error(e.getMessage());
//            return ResponseUtils.exceptionError("Error Logging", e.getMessage());
//        }
//    }
    @RequestMapping(value = PublicApiUrlConstants.LOGIN_USER, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> login(@RequestBody LoginReqData loginReqData) {
        try {
            return userService.login(loginReqData);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseUtils.exceptionError("Error Logging", e.getMessage());
//        return ResponseUtils.exceptionError("Error Logging", e.getMessage());
        }
    }

}
