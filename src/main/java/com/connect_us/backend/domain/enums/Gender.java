package com.connect_us.backend.domain.users;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Gender {
    MALE("MALE","남자"),
    FEMALE("FEMALE","여자");

    private final String key;
    private final String value;
    public String getKey(){
        return key;
    }

}
