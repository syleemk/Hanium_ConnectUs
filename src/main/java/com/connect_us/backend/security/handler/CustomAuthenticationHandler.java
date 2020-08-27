package com.connect_us.backend.security.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationHandler extends SimpleUrlAuthenticationSuccessHandler {
    // 이전 사용자의 요청을 담고있는 객체
//    private RequestCache requestCache = new HttpSessionRequestCache();
//
//    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
//
//    // 인증 성공 이후에 request, response 이용해 다양한 기능추가 가능
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
//            throws IOException, ServletException {
//        //사용자가 인증에 성공하기 전의 정보 꺼냄
//        SavedRequest savedRequest = requestCache.getRequest(request, response);
//        setDefaultTargetUrl("/");
//
//        //로그인 인증 시도시 정보 없는 경우에는 null
//        if(savedRequest!=null){
//            String targetUrl = savedRequest.getRedirectUrl();
//            redirectStrategy.sendRedirect(request,response,targetUrl);
//        }else{//default page
//            redirectStrategy.sendRedirect(request, response, getDefaultTargetUrl());
//        }
//    }
    public AuthenticationSuccessHandler successHandler() {
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                httpServletResponse.getWriter().append("OK");
                httpServletResponse.setStatus(200);
            }
        };
    }
}

