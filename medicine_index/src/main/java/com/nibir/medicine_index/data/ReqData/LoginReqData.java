package com.nibir.medicine_index.data.ReqData;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LoginReqData {
    private String userId;
    private String password;
}