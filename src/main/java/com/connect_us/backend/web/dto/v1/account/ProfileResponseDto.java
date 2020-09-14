package com.connect_us.backend.web.dto.v1.account;

import com.connect_us.backend.domain.account.Account;
import com.connect_us.backend.web.dto.v1.ResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

public class ProfileResponseDto extends ResponseDto {
    private Account account;
    @Builder
    public ProfileResponseDto(Boolean success, String message, Account account) {
        super(success, message);
        this.account = account;
    }
}
