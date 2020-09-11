package com.connect_us.backend.security.dto;

import com.connect_us.backend.domain.enums.Gender;
import com.connect_us.backend.domain.enums.Role;
import com.connect_us.backend.domain.enums.Social;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AccountDto {//회원가입정보 꼬이지 않기 위해 따로 관리
    private String email;
    private String password;
    private String name;
    private Social social;
    private Gender gender;
    private Role role;

}
