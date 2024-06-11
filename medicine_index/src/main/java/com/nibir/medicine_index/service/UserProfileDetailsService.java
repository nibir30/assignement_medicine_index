package com.nibir.medicine_index.service;

import com.nibir.medicine_index.config.auth.dto.SecurityUser;
import com.nibir.medicine_index.config.auth.dto.SecurityUserDetails;
import com.nibir.medicine_index.constants.DbConstant;
import com.nibir.medicine_index.model.UserModel;
import com.nibir.medicine_index.model.UserProfile;
import com.nibir.medicine_index.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserProfileDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public SecurityUserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        UserProfile userProfile;
        /*if (userId != null) {
            userProfile = new UserProfile();
            userProfile.setUserStatus("Y");
            userProfile.setUserId(userId);
        } else {*/
        UserModel userModel = userRepository.findByUserId(userId);
        SecurityUser securityUser = new SecurityUser();
        if (userModel != null && userModel.getUserId() != null) {
            securityUser.setPassword(bcryptEncoder.encode(DbConstant.SECURITY_USER_PASSWORD));
            securityUser.setUserId(userModel.getUserId());
            securityUser.setUserStatus(userModel.getUserStatus());
            securityUser.setPasswordExpiryDate(userModel.getPasswordExpiryDate());
            securityUser.setId(userModel.getId());
            securityUser.setUserRoleModels(userModel.getRoleModels());
            securityUser.setFirstName(userModel.getFirstName());
            securityUser.setLastName(userModel.getLastName());
        }

        return new SecurityUserDetails(securityUser);
    }

}