//package com.connect_us.backend.security.config;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.JWTVerifier;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.exceptions.JWTVerificationException;
//import com.auth0.jwt.interfaces.DecodedJWT;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
//import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
//@Component
////dependency cycle로 사용 못함
//public class JwtUtils {
//    //토큰 생성
//    public String createToken(String username){
//        String jwtToken = JWT.create()
//                .withIssuer(username)
//                .withSubject(username+"token")
//                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
//                .sign(Algorithm.HMAC512(JwtProperties.SECRET.getBytes()));
//
//        return jwtToken;
//    }
//
//    //토큰 해독(username)
//    public String decodeTokenUsername(String token){
//        String username = JWT.require(HMAC512(JwtProperties.SECRET.getBytes()))
//                .build()
//                .verify(token)
//                .getSubject();
//
//        return username;
//    }
//    //프론트에서 토큰 넘겨받았을때 유효한지 검사
//    public boolean validateToken(String token){
//        if(token==null){
//            return false;
//        }else{
//            try{
//                Algorithm algorithm= Algorithm.HMAC256(JwtProperties.SECRET.getBytes());
//                JWTVerifier verifier = JWT.require(algorithm).build();
//                DecodedJWT jwt = verifier.verify(token);
//                return true;
//            }catch (JWTVerificationException exception){
//                    exception.printStackTrace();
//                    return false;
//            }
//        }
//    }
//}
