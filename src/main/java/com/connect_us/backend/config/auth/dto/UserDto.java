package com.connect_us.backend.config.auth.dto;

import com.connect_us.backend.domain.enums.Gender;
import com.connect_us.backend.domain.enums.Role;
import com.connect_us.backend.domain.enums.Social;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@NoArgsConstructor
public class UserDto {//기존 User와 꼬이지 않기 위해 따로 관리

    private Long id;
    private String email;
    private String password;
    private String name;
    private Social social;
    private String phone;
    private String addr;
    private Gender gender;
    private Role role;

//    @Builder
//    public UserDto(Long id, String email, String password, String name){
//        this.id=id;
//        this.email=email;
//        this.password=password;
//    }
//
//    public User toEntity(){
//        return User.builder()
//                .email(email)
//                .password(password)
//                .build();
//    }


}
