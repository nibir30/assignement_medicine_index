package com.nibir.medicine_index.data.ResData;

import com.nibir.medicine_index.data.ResData.core.JwtResponseData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginResData {
    LoginInfo userInfo;
    private JwtResponseData tokenInfo;
}
