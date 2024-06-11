package com.nibir.medicine_index.config.auth.dto;

import com.nibir.medicine_index.model.RoleModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class SecurityUser implements Serializable {
    private Long id;
    private String userId;
    private String password;
    private String userStatus;
    private LocalDateTime passwordExpiryDate;
    private String firstName;
    private String lastName;
    private List<RoleModel> userRoleModels;
    public SecurityUser() {
    }

    public SecurityUser(SecurityUser user) {

        this.id = user.getId();
        this.userId = user.getUserId();
        this.password = user.getPassword();
        this.userStatus =user.getUserStatus();
        this.passwordExpiryDate = user.getPasswordExpiryDate();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.userRoleModels = user.getUserRoleModels();
    }

}
