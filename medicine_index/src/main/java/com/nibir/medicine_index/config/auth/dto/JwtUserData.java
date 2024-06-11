package com.nibir.medicine_index.config.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtUserData implements Serializable {
    private static final Long serialVersionUID = 1L;

    private String userType;
    private String userId;
    private String userStatus;
    private String userRole;
}
