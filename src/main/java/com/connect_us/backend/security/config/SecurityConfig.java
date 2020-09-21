package com.connect_us.backend.security.config;

import com.connect_us.backend.domain.account.AccountRepository;
import com.connect_us.backend.domain.enums.Role;
import com.connect_us.backend.security.filter.JwtAuthenticationFilter;
import com.connect_us.backend.security.filter.JwtAuthorizationFilter;
import com.connect_us.backend.security.handler.CustomAuthenticationFailureHandler;
import com.connect_us.backend.security.handler.CustomAuthorizationHandler;
import com.connect_us.backend.security.service.AccountPrincipalDetailService;
import com.connect_us.backend.security.service.CustomOAuth2UserService;
import com.connect_us.backend.service.account.impl.AccountServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

//import com.connect_us.backend.security.handler.CustomAuthenticationHandler;

@Configuration
@RequiredArgsConstructor
@EnableJpaAuditing
@EnableWebSecurity //Spring Security 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final AccountServiceImp accountServiceImp;
    private final AccountPrincipalDetailService accountPrincipalDetailService;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final JwtUtils jwtUtils;

    @Bean //Security에서 제공하는 비밀번호 암호화 객체
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //static file 무시
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    //http 관련 인증
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");

        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//rest: stateless, cookie에 세션 저장 x
                .and()
                    .authorizeRequests()
                    .antMatchers("/", "/oauth2/**", "/login/**", "/h2-console/**", "/api/v1/auth/users", "/api/v1/auth/login*", "/api/v1/email/userVerification/**","/api/v1/products/**").permitAll()
                    .antMatchers("/api/v1/admin/**").hasRole(Role.ADMIN.name()) //관리자페이지 권한
                    .antMatchers("/api/v1/seller/**").hasRole(Role.SELLER.name())//판매자페이지 권한
                    .antMatchers("/api/v1/users/**").hasRole(Role.USER.name())
                    .anyRequest().authenticated()
                .and()
                    .headers().frameOptions().disable()
                .and()
                    .logout()
                    .logoutSuccessUrl("/") // 로그아웃 성공시 home으로
                    .invalidateHttpSession(true)
                .and()
                    .oauth2Login()
                        .userInfoEndpoint()
                            .userService(customOAuth2UserService);
        http
                .addFilterAt(getAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), this.accountServiceImp, this.jwtUtils));

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        //db의 비밀번호 암호화/복호화
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.accountPrincipalDetailService);
        return daoAuthenticationProvider;
    }

    protected JwtAuthenticationFilter getAuthenticationFilter() throws Exception {
        JwtAuthenticationFilter authenticationFilter = new JwtAuthenticationFilter(authenticationManager(), this.jwtUtils);
        try {
            authenticationFilter.setFilterProcessesUrl("/api/v1/auth/login");
            authenticationFilter.setUsernameParameter("email");
            authenticationFilter.setAuthenticationSuccessHandler(new CustomAuthorizationHandler());
            authenticationFilter.setAuthenticationFailureHandler(new CustomAuthenticationFailureHandler());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authenticationFilter;
    }
}
