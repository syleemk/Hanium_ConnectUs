package com.connect_us.backend.web.dto.v1.admin;

import com.connect_us.backend.domain.account.Account;
import com.connect_us.backend.domain.enums.Gender;
import com.connect_us.backend.domain.enums.Role;
import com.connect_us.backend.domain.enums.Social;
import com.connect_us.backend.web.dto.v1.ResponseDto;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

public class UserListResponseDto extends ResponseDto {
    private List<Data> data;

    @Getter
    @NoArgsConstructor
    static class Data {
        private String email;
        private String name;
        private String phone;
        private String addr;
        private Role role;
        private Gender gender;
        private Social social;

        public Data(Account entity) {
            this.email = entity.getEmail();
            this.name = entity.getName();
            this.phone = entity.getPhone();
            this.addr = entity.getAddr();
            this.role = entity.getRole();
            this.gender = entity.getGender();
            this.social = entity.getSocial();
        }
    }

    public UserListResponseDto(Boolean success, String message, List<Account> data) {
        super(success, message);
        this.data = data.stream().map(Data::new).collect(Collectors.toList());
    }
}
