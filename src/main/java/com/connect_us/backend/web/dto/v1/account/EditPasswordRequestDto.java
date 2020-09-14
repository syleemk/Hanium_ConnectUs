package com.connect_us.backend.web.dto.v1.account;

import lombok.Getter;

@Getter
public class EditPasswordRequestDto {
    private String password;

    public EditPasswordRequestDto(String password) {
        this.password = password;
    }
}
