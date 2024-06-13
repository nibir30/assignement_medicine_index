package com.nibir.medicine_index.data.ResData;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class LoginInfo {
    private Long id;
    private Long imageId;
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNo;
    private List<String> roleNames;
}
