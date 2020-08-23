//package com.connect_us.backend.config.auth.dto;
//
//import com.connect_us.backend.domain.enums.Role;
//import com.connect_us.backend.domain.user.User;
//import lombok.Builder;
//import lombok.Getter;
//
//import java.util.Map;
//
////User: entity, 다른 entity들이랑 Mapping시 꼬일수 있어서 소셜 로그인 관련 dto 따로 만듬
//@Getter
//public class OAuthAttributes {
//    private Map<String, Object> attributes;
//    private String nameAttributeKey;
//    private String name;
//    private String email;
//
//    @Builder
//    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey,
//                           String name, String email){
//        this.attributes=attributes;
//        this.nameAttributeKey=nameAttributeKey;
//        this.name=name;
//        this.email=email;
//    }
//
//    //OAuth2User에서 Map 반환함, 그래서 하나하나 변환해야 함
//    public static OAuthAttributes of(String registrationId, String userNameAttributeName,
//                                     Map<String, Object> attributes){
//        if("naver".equals(registrationId)){
//            return ofNaver("id",attributes);
//        }
//        return ofGoogle(userNameAttributeName, attributes);
//    }
//
//    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String,Object> attributes){
//        return OAuthAttributes.builder()
//                .name((String) attributes.get("name"))
//                .email((String) attributes.get("email"))
//                .attributes(attributes)
//                .nameAttributeKey(userNameAttributeName)
//                .build();
//    }
//
//    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes){
//        Map<String, Object> response = (Map<String,Object>) attributes.get("response");
//
//        return OAuthAttributes.builder()
//                .name((String) attributes.get("name"))
//                .email((String) attributes.get("email"))
//                .attributes(attributes)
//                .nameAttributeKey(userNameAttributeName)
//                .build();
//    }
//
//    //처음 가입하면 일반 권한 주고 entity 생성
//    public User toEntity(){
//        return User.builder()
//                .name(name)
//                .email(email)
//                .role(Role.USER)
//                .build();
//    }
//}
