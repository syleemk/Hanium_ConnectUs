package com.connect_us.backend.config.auth;

import com.connect_us.backend.domain.enums.Role;
import com.connect_us.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.CharacterEncodingFilter;


@Configuration
@RequiredArgsConstructor
@EnableWebSecurity //Spring Security 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;

    @Bean //Security에서 제공하는 비밀번호 암호화 객체
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //static file 무시
    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
    }

    //http 관련 인증
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");

        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/","/oauth2/**","/h2-console/**", "/login", "/join").permitAll()
                    .antMatchers("/api/v1/admin/**").hasRole(Role.ADMIN.name()) //관리자페이지 권한
                    .antMatchers("/api/v1/seller/**").hasRole(Role.SELLER.name())//판매자페이지 권한
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name()) //나머지는 일반회원도 가능..?
                    .anyRequest().authenticated()
                .and()
                    .headers().frameOptions().disable()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/")
                .and()
                    .logout()
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true);
//                .and()
//                    .oauth2Login()
//                    .userInfoEndpoint()//로그인 성공 후 사용자 정보 가져올때 설정 담당
//                    .userService(customOAuth2UserService);//로그인 성공시 후속 조치 진행

    }

    //로그인할때 필요한 정보 가져옴
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }




}
