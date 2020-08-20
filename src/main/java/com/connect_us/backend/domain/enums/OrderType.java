package com.connect_us.backend.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderType {
    PRODUCT("PRODUCT", "상품"),
    FUND("FUND", "펀드");

    private final String key;
    private final String value;
    private String getKey() {return key;};
}
