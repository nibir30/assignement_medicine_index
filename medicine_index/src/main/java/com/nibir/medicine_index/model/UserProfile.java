package com.nibir.medicine_index.model;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfile implements Serializable {

    private static final Long serialVersionUID = 1L;

    private String userId;
    private LocalDate password_expiry_date;
    private String profile_image;
    private String userName;
    private Long id;
    private List<Integer> user_role;
}