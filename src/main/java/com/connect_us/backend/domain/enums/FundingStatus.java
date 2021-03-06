package com.connect_us.backend.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FundingStatus {
    NORMAL("NORMAL","진행"),
    STOPPED("STOPPED","중단"),
    CLOSED("DELETE","마감");

    private final String key;
    private final String value;
}
