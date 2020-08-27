package com.connect_us.backend.security.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    //    @Override
//    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
//        //예외 client에 뿌리기
//        String errorMessage ="이메일이나 비밀번호가 잘못되었습니다.";
//
//        if(exception instanceof BadCredentialsException){
//            errorMessage="이메일이나 비밀번호가 잘못되었습니다.";
//        }
//        //예외 추가되면 더 넣을 수 있음
//        setDefaultFailureUrl("/v1/auth/login?error=true&exception"+exception.getMessage());
//        super.onAuthenticationFailure(request,response,exception);
//
//    }
    public AuthenticationFailureHandler failureHandler() {
        return new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                httpServletResponse.getWriter().append("Authentication failure");
                httpServletResponse.setStatus(401);
            }
        };
    }
}
