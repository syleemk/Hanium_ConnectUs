//package com.connect_us.backend.config.auth;
//
//import com.connect_us.backend.config.auth.dto.OAuthAttributes;
//import com.connect_us.backend.config.auth.dto.SessionUser;
//import com.connect_us.backend.domain.users.Users;
//import com.connect_us.backend.domain.users.UsersRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
//import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
//import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.stereotype.Service;
//
//import javax.servlet.http.HttpSession;
//import java.util.Collections;
///*소셜 로그인으로 가져온 사용자 정보 기반으로 가입, 정보수정, 세션 저장 등의 기능 지원 */
//
//@RequiredArgsConstructor
//@Service
//public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
//    private final UsersRepository usersRepository;
//    private final HttpSession httpSession;
//
//    @Override
//    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//        OAuth2UserService<OAuth2UserRequest, OAuth2User>
//                delegate = new DefaultOAuth2UserService(); //대리자
//
//        OAuth2User oAuth2User = delegate.loadUser(userRequest);
//
//        //현재 로그인 진행중인 서비스 구분하는 코드
//        String registrationId = userRequest.getClientRegistration().getRegistrationId();
//
//        //소셜 로그인 진행시 키가 되는 필드값, pk (구글은 sub로 기본지원, 나머지는 기본 지원 안함)
//        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
//                .getUserInfoEndpoint().getUserNameAttributeName();
//
//        //OAuth2UserService를 통해 가져온 OAuth2User의 attribute 담음
//        OAuthAttributes attributes =
//                OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
//
//        Users user = saveOrUpdate(attributes);
//
//        //session에 사용자 정보 저장하기 위한 dto(새로 만들어서 써야함)
//        httpSession.setAttribute("user", new SessionUser(user));
//
//        return new DefaultOAuth2User(
//                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
//                attributes.getAttributes(),
//                attributes.getNameAttributeKey());
//    }
//
//    private Users saveOrUpdate(OAuthAttributes attributes) {
//        Users user = usersRepository.findByEmail(attributes.getEmail())
//                .map(entity -> entity.update(attributes.getName()))
//                .orElse(attributes.toEntity());
//        return usersRepository.save(user);
//    }
//}
//
//
//
