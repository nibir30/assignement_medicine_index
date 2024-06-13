package com.nibir.medicine_index.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "User")
public class UserModel {
    @Id
    private Long id;
    private Long imageId;
    private String userId;
    private String firstName;
    private String lastName;
    private String fullName;
    @JsonIgnore
    private String password;
    private String userStatus;
    private LocalDateTime passwordExpiryDate;

    private String email;
    private String phoneNo;
    private String address;
    @ManyToMany(targetEntity = RoleModel.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
    private List<RoleModel> roleModels;

    @JsonIgnore
    private LocalDateTime insertTime;
    @JsonIgnore
    private LocalDateTime updateTime;
}
