package com.nibir.medicine_index.data.ResData.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class JwtResponseData implements Serializable {

    private static final Long serialVersionUID = -8091879091924046844L;

    @JsonProperty("type")
    private String type;

    @JsonProperty("token")
    private String token;

    public JwtResponseData(String type, String token) {
        this.type = type;
        this.token = token;
    }
}