package com.connect_us.backend.security.config;

public class JwtProperties {
    public static final String SECRET = "connectUs"; //hash에 사용되는 key
    public static final int EXPIRATION_TIME = 864000000; //token validation 기간
    public static final String TOKEN_PREFIX = "Bearer "; //header prefix
    public static final String HEADER_STRING = "Authorization"; //authorization header로 전달
}
