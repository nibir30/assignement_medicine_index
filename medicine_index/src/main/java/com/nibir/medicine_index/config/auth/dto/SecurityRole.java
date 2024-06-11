package com.nibir.medicine_index.config.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class SecurityRole implements Serializable {
    private String role;
}
