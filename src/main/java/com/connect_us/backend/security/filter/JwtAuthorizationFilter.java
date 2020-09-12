package com.connect_us.backend.security.filter;

import com.auth0.jwt.JWT;
import com.connect_us.backend.domain.account.Account;
import com.connect_us.backend.domain.account.AccountRepository;
import com.connect_us.backend.security.config.JwtProperties;
import com.connect_us.backend.security.dto.AccountPrincipal;
import com.connect_us.backend.service.account.impl.AccountServiceImp;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

/** authorization with JWT from login success user
 *  find the user's ROLE using username(email)
 * **/

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private AccountServiceImp accountServiceImp;
    private AccountRepository accountRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, AccountRepository accountRepository) {
        super(authenticationManager);
        this.accountRepository = accountRepository;
        this.accountServiceImp = accountServiceImp;
    }

    /** At the endpoint, Every Request has to hit with Authorization**/
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("authorization filter");
        // Read the Authorization header in JWT
        String header = request.getHeader(JwtProperties.HEADER_STRING);

        // Check if header contain BEARER or is null
        if(header == null || !header.startsWith(JwtProperties.TOKEN_PREFIX)){
            // rest of the spring pipeline
            chain.doFilter(request, response);
            return;
        }

        // If header is present, try grab user principal from database and perform authorization
        Authentication authentication = getUsernamePasswordAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Continue filter execution
        chain.doFilter(request, response);
    }

    /** Password Authentication **/
    private Authentication getUsernamePasswordAuthentication(HttpServletRequest request) {
        //Get JWT token
        String token = request.getHeader(JwtProperties.HEADER_STRING)
                .replace(JwtProperties.TOKEN_PREFIX,"");
        if(token != null){
            // parse the token and validate it (decode)
            String username = JWT.require(HMAC512(JwtProperties.SECRET.getBytes()))
                    .build()
                    .verify(token)
                    .getSubject();

            // Search in the DB if we find the user by token subject (username)
            // If so, then grab user details and create spring auth token using username, pass, authorities/roles
            if(username != null){
                Account account = accountRepository.findByEmail(username);
                AccountPrincipal accountPrincipal = new AccountPrincipal(account);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null, accountPrincipal.getAuthorities());
                return auth;
            }

            return null;

        }

        return null;
    }
}
