package com.nibir.medicine_index.data.ReqData;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserRegisterReqData {
    private String userId;
    private Long imageId;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String phoneNo;
    private String address;
    private List<Long> roleIds;
}
