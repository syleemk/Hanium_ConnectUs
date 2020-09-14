package com.connect_us.backend.web.dto.v1.account;

import com.connect_us.backend.domain.account.Account;
import com.connect_us.backend.domain.enums.Gender;
import com.connect_us.backend.domain.enums.Role;
import com.connect_us.backend.domain.enums.Social;
import com.connect_us.backend.web.dto.v1.ResponseDto;
import lombok.*;

@Getter
@NoArgsConstructor
public class ProfileResponseDto extends ResponseDto {
    private Data data;

    @Getter
    @NoArgsConstructor
    static class Data{
        private String email;
        private String name;
        private String phone;
        private String addr;
        private Gender gender;

        Data(Account account) {
            this.email = account.getEmail();
            this.name = account.getName();
            this.phone = account.getPhone();
            this.addr = account.getAddr();
            this.gender = account.getGender();
        }
    }

    @Builder
    public ProfileResponseDto(Boolean success, String message, Account account) {
        super(success, message);
        this.data = new Data(account);
    }
}
