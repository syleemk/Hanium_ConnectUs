package com.connect_us.backend.security.dto;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class GoogleResponse {

    private String accessToken;
    private String expiresIn;
    private String refreshToken;
    private String scope;
    private String tokenType;
    private String idToken;


};