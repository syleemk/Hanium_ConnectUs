package com.connect_us.backend.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    USER("ROLE_USER", "일반"),
    SELLER("ROLE_SELLER", "판매자"),
    ADMIN("ROLE_ADMIN", "관리자");

    private final String key;
    private final String value;
}
