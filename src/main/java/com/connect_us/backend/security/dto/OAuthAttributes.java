package com.connect_us.backend.security.dto;

import com.connect_us.backend.domain.account.Account;
import com.connect_us.backend.domain.enums.Role;
import com.connect_us.backend.domain.enums.Social;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

//social dto
@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey,
                           String name, String email){
        this.attributes=attributes;
        this.nameAttributeKey=nameAttributeKey;
        this.name=name;
        this.email=email;
    }

    //OAuth2User에서 Map 반환함, 그래서 하나하나 변환해야 함
    public static OAuthAttributes of(String registrationId, String userNameAttributeName,
                                     Map<String, Object> attributes){
        if("naver".equals(registrationId)){
            return ofNaver("id",attributes);
        }
        else if("kakao".equals(registrationId)){
            System.out.println("check if it is kakao");
            return ofKakao("id",attributes);
        }
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String,Object> attributes){
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes){
        Map<String,Object> response = (Map<String,Object>) attributes.get("response");

        System.out.println(response);
        System.out.println("ofNaver");

        return OAuthAttributes.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes){
        Map<String,Object> response = (Map<String,Object>) attributes.get("response");

        System.out.println(attributes);
        System.out.println("ofKakao");

        return OAuthAttributes.builder()
                .name((String) response.get("profile.name"))
                .email((String) response.get("account_email"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    //처음 가입하면 일반 권한 주고 entity 생성
    public Account toEntity(){
        return Account.builder()
                .name(name)
                .email(email)
                .role(Role.USER)
                .social(Social.TRUE)
                .build();
    }
}
