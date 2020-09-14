package com.connect_us.backend.web.dto.v1.admin;

import com.connect_us.backend.domain.enums.Role;
import com.connect_us.backend.web.dto.v1.ResponseDto;

public class RoleChangeResponseDto extends ResponseDto {
    private Data data;

    class Data{
        private Role before;
        private Role after;

        public Data(Role before, Role after) {
            this.before = before;
            this.after = after;
        }
    }
    public RoleChangeResponseDto(Boolean success, String message, Role before, Role after) {
        super(success, message);
        this.data = new Data(before,after);
    }
}
