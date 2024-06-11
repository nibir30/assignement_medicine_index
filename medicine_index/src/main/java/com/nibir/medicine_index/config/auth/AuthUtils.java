package com.nibir.medicine_index.config.auth;

import com.nibir.medicine_index.config.auth.dto.SecurityUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthUtils {

    public SecurityUserDetails getSecurityUserDetails() {
        return (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        SecurityUser securityUser = new SecurityUser();
//        securityUser.setUserId(60101110026L);
//        securityUser.setPassword("cns123");
//        securityUser.setActiveYn("Y");
//        securityUser.setName("DummyUser");
//        return new SecurityUserDetails(securityUser);
    }
}