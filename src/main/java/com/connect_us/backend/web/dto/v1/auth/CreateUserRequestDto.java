package com.connect_us.backend.web.dto.v1.auth;

import com.connect_us.backend.domain.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateUserRequestDto {
    private String email;
    private String password;
    private String name;
    private Gender gender;

}
