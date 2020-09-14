package com.connect_us.backend.web.dto.v1.admin;

import com.connect_us.backend.domain.account.Account;
import com.connect_us.backend.web.dto.v1.ResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

public class UserListResponseDto extends ResponseDto {
    List<Account> accounts;

    @Builder
    public UserListResponseDto(Boolean success, String message, List<Account> accounts) {
        super(success, message);
        this.accounts = accounts;
    }
}
