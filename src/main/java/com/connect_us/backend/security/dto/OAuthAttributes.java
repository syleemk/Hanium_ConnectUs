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
    private String uid;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey,
                           String name, String email, String uid){
        this.attributes=attributes;
        this.nameAttributeKey=nameAttributeKey;
        this.name=name;
        this.email=email;
        this.uid=uid;
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

    //OAuth2User에서 Map 반환함, 그래서 하나하나 변환해야 함
    public static OAuthAttributes of(String registrationId, String userNameAttributeName,
                                     Map<String, Object> attributes){
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

}
