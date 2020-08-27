package com.connect_us.backend.security.filter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.entity.ContentType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private boolean postOnly =true;
    private HashMap<String, String> jsonRequest;

    @Override
    protected String obtainPassword(HttpServletRequest request){
        String password = super.getPasswordParameter();
        if(request.getHeader("Content-Type").equals(ContentType.APPLICATION_JSON.getMimeType())){
            return jsonRequest.get(password); //json
        }
        return request.getParameter(password);//formLogin
    }

    @Override
    protected String obtainUsername(HttpServletRequest request){
        String username = super.getUsernameParameter();
        if(request.getHeader("Content-Type").equals(ContentType.APPLICATION_JSON.getMimeType())){
            return jsonRequest.get(username); //json
        }
        return request.getParameter(username);//formLogin
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if(postOnly && !request.getMethod().equals("POST")){
            throw new AuthenticationServiceException(("authentication method not supported: "+request.getMethod()));
        }
        if(request.getHeader("Content-Type").equals(ContentType.APPLICATION_JSON.getMimeType())){
            ObjectMapper mapper = new ObjectMapper();
            try{
                this.jsonRequest = mapper.readValue(request.getReader().lines().collect(Collectors.joining()),
                        new TypeReference<Map<String,String>>() {});
            }catch(IOException e){
                e.printStackTrace();
                throw new AuthenticationServiceException("request content-type(application/json) parsing eror");
            }
        }
        String username = obtainUsername(request);
        String password = obtainPassword(request);

        logger.info("LOGIN REQUEST [email: {}]"+ username);
        if(username==null){
            username="";
        }
        if(password==null){
            password="";
        }
        username=username.trim();
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
        setDetails(request,authRequest);
        System.out.println(authRequest);
        System.out.println(this.getAuthenticationManager().authenticate(authRequest));
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    @Override
    public void setPostOnly(boolean postOnly){
        this.postOnly=postOnly;
    }
}
