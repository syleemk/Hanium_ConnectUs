package com.connect_us.backend.web.dto.v1.account;

import com.connect_us.backend.domain.enums.Gender;
import lombok.Builder;
import lombok.Getter;

@Getter
public class EditUserRequestDto {
    private String name;
    private String addr;
    private String phone;
    private Gender gender;

    @Builder
    public EditUserRequestDto(String name, String addr, String phone, Gender gender) {
        this.name = name;
        this.addr = addr;
        this.phone = phone;
        this.gender = gender;
    }



}
