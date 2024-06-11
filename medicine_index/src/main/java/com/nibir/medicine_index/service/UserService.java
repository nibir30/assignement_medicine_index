package com.nibir.medicine_index.service;

import com.nibir.medicine_index.data.ReqData.LoginReqData;
import com.nibir.medicine_index.data.ReqData.UserRegisterReqData;
import com.nibir.medicine_index.data.ResData.RegistrationResData;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    ResponseEntity<?> getAllUser();

    ResponseEntity<?> getAllUserByBloodGroup(String bloodGroup);

    ResponseEntity<?> saveUser(UserRegisterReqData userRegisterReqData);

    ResponseEntity<?> findUserByEmail(String email);

    ResponseEntity<?> updateProfile(RegistrationResData registrationResData);

    ResponseEntity<?> login(LoginReqData loginReqData);

    ResponseEntity<?> adminLogin(LoginReqData loginReqData);
}
