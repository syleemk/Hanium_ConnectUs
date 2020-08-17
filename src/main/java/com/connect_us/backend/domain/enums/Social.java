package com.connect_us.backend.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Social{
    NONE("ROLE_NONE"),
    GOOGLE("ROLE_GOOGLE"),
    NAVER("ROLE_NAVER"),
    KAKAO("ROLE_KAKAO");

    private final String key;

    public String getKey(){
        return key;
    }
    public boolean isEquals(String authority){ //check the join root
        return this.getKey().equals(authority);
    }
}
