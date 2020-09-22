package com.connect_us.backend.security.filter;

import com.connect_us.backend.security.config.JwtProperties;
import com.connect_us.backend.security.config.JwtUtils;
import com.connect_us.backend.security.dto.AccountPrincipal;
import com.connect_us.backend.security.dto.LoginModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** change username, password to JSON
 *  do Login
 *  make JWT token
 * **/

@Slf4j
@NoArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils=jwtUtils;
    }

    /** v1/auth/login/normal
     *  request: userEmail, password
     * **/
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("this is request: "+request);
        System.out.println("this is response: "+response);
        LoginModel credentials =null;
        try{
            credentials= new ObjectMapper().readValue(request.getInputStream(), LoginModel.class);
        } catch(IOException e){
            log.error("Failed json parser: {}", request, e);
        }
        System.out.println(credentials);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                credentials.getUsername(),
                credentials.getPassword()
        );
        Authentication auth = authenticationManager.authenticate(authenticationToken);
        return auth;
    }

    public Authentication attemptAuthentication(LoginModel loginModel) throws AuthenticationException {
        System.out.println("for oauth2 login");
        LoginModel credentials =null;
        try{
            credentials= loginModel;
        } catch(NullPointerException e){
            e.printStackTrace();
        }
        System.out.println(credentials);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                credentials.getUsername(),
                credentials.getPassword()
        );
        System.out.println(authenticationManager);
        Authentication auth = authenticationManager.authenticate(authenticationToken);
        return auth;
    }


    /** 로그인 성공시 수행 **/
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        System.out.println("successfulAuthentication");
        AccountPrincipal principal = (AccountPrincipal)authResult.getPrincipal();

        /**create jwt token**/
        String jwtToken = jwtUtils.createToken(principal.getUsername());
        response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + jwtToken);
    }
}
