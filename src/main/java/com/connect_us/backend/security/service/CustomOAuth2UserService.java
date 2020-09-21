package com.connect_us.backend.security.service;

import com.connect_us.backend.domain.account.Account;
import com.connect_us.backend.domain.account.AccountRepository;
import com.connect_us.backend.security.config.JwtProperties;
import com.connect_us.backend.security.config.JwtUtils;
import com.connect_us.backend.security.config.ModifiableHttpServletRequest;
import com.connect_us.backend.security.dto.AccountPrincipal;
import com.connect_us.backend.security.dto.LoginModel;
import com.connect_us.backend.security.dto.OAuthAttributes;
import com.connect_us.backend.security.dto.SocialUser;
import com.connect_us.backend.security.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collections;
/*소셜 로그인으로 가져온 사용자 정보 기반으로 가입, 정보수정, 세션 저장 등의 기능 지원 */

@WebServlet(initParams={@WebInitParam(name="request", value=" ")})
@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final AccountRepository accountRepository;
    private final JwtUtils jwtUtils;
    private final HttpServletResponse httpServletResponse;
    private final HttpServletRequest httpServletRequest;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("i'm in loadUser");
        OAuth2UserService<OAuth2UserRequest, OAuth2User>
                delegate = new DefaultOAuth2UserService(); //대리자

        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        //현재 로그인 진행중인 서비스 구분하는 코드
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        //소셜 로그인 진행시 키가 되는 필드값, pk (구글은 sub로 기본지원, 나머지는 기본 지원 안함)
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();

        //OAuth2UserService를 통해 가져온 OAuth2User의 attribute 담음
        OAuthAttributes attributes =
                OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        Account account = saveOrUpdate(attributes);
        System.out.println("oauth account: "+account);

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(account.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    private Account saveOrUpdate(OAuthAttributes attributes) {
        System.out.println("i'm in a saveOrUpdate");
        System.out.println(attributes.getEmail());
        Account account = accountRepository.findByEmail(attributes.getEmail());

        //계정이 없다면 회원가입
        if(account == null || account.getEmail().isEmpty()){
            account = attributes.toEntity();
            accountRepository.save(account);
        }
        //로그인 해야 함
       LoginModel loginModel=new LoginModel(account.getEmail(), null);

        return account;
    }
}



