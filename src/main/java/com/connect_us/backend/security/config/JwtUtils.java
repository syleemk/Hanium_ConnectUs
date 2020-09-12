package com.connect_us.backend.security.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtUtils {
    //프론트에서 토큰 넘겨받았을때 유효한지 검사
    public boolean validateToken(String token){
        if(token==null){
            return false;
        }else{
            try{
                Algorithm algorithm= Algorithm.HMAC256("SECRET");
                JWTVerifier verifier = JWT.require(algorithm)
                        .withIssuer("auth0")
                        .build();
                DecodedJWT jwt = verifier.verify(token);
                return true;
            }catch (JWTVerificationException exception){
                    exception.printStackTrace();
                    return false;
            }
        }
    }
}
