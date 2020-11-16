package com.connect_us.backend.security.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
@Getter
public class GoogleRequest {
    private String redirectUri;
    private String clientId;
    private String clientSecret;
    private String code;
    private String responseType;
    private String scope;
    private String accessType;
    private String grantType;
    private String state;
    private String includeGrantedScopes;
    private String loginHint;
    private String prompt;
}

