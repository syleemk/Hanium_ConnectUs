package com.connect_us.backend.security.config;

import com.connect_us.backend.domain.enums.Role;
import com.connect_us.backend.security.handler.CustomAuthenticationFailureHandler;
import com.connect_us.backend.security.handler.CustomAuthenticationHandler;
import com.connect_us.backend.security.provider.CustomAuthenticationProvider;
import com.connect_us.backend.security.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity //Spring Security 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean //Security에서 제공하는 비밀번호 암호화 객체
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //static file 무시
    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    //http 관련 인증
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");

        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/","/oauth2/**","/login/**", "/h2-console/**", "/v1/auth/**", "/v1/auth/login*").permitAll()
                    .antMatchers("/v1/admin/**").hasRole(Role.ADMIN.name()) //관리자페이지 권한
                    .antMatchers("/v1/seller/**").hasRole(Role.SELLER.name())//판매자페이지 권한
                    .antMatchers("/v1/user/**").hasRole(Role.USER.name())
                    .anyRequest().authenticated()
                .and()
                    .headers().frameOptions().disable()
                .and()
                    .formLogin()
                    .loginPage("/v1/auth/login")
                    .loginProcessingUrl("/v1/auth/login") //로그인 form 의 action 과 일치시켜주어야 한다.
                    .usernameParameter("email")
                    .defaultSuccessUrl("/v1/home")
                    .successHandler(new CustomAuthenticationHandler())
                    .failureHandler(new CustomAuthenticationFailureHandler())
                    .permitAll()
                .and()
                    .logout()
                        .logoutSuccessUrl("/") // 로그아웃 성공시 home으로
                        .invalidateHttpSession(true)
                .and()
                    .oauth2Login()
                    .defaultSuccessUrl("/")
                    .userInfoEndpoint()//로그인 성공 후 사용자 정보 가져올때 설정 담당
                    .userService(customOAuth2UserService);//로그인 성공시 후속 조치 진행

    }

    //로그인 인증
    //관리자 계정, 판매자 계정 inmemory에 생성
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        String password = passwordEncoder().encode("1234");
        auth.inMemoryAuthentication().withUser("admin").password(password).roles("ADMIN");
        auth.inMemoryAuthentication().withUser("seller").password(password).roles("SELLER");
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new CustomAuthenticationProvider();
    }


}
