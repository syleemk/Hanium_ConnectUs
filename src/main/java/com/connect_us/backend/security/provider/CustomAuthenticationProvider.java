package com.connect_us.backend.security.provider;

import com.connect_us.backend.security.service.AccountContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

//UserContext 인증관련
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private  UserDetailsService userDetailsService;
    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Override //검증을 위한 구현 authentication: 인증객체, 사용자의 정보가 담겨져있음
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password =(String)authentication.getCredentials();

       AccountContext accountContext = (AccountContext) userDetailsService.loadUserByUsername(email);
       //비번 일치하는지 확인
       if(!passwordEncoder.matches(password, accountContext.getAccount().getPassword())){
           throw new BadCredentialsException("BadCredentialsException");
       }

       //인증 성공
       UsernamePasswordAuthenticationToken authenticationToken =
               new UsernamePasswordAuthenticationToken(accountContext.getAccount(), null, accountContext.getAuthorities());

       return authenticationToken;
    }

    @Override //authentication과 token 타입이 일치하면 true
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
