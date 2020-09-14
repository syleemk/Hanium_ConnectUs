package com.connect_us.backend.web.dto.v1.admin;

import com.connect_us.backend.domain.enums.Role;
import com.connect_us.backend.web.dto.v1.ResponseDto;

public class RoleChangeResponseDto extends ResponseDto {
    private Role before;
    private Role after;
    public RoleChangeResponseDto(Boolean success, String message, Role before, Role after) {
        super(success, message);
        this.before = before;
        this.after = after;
    }
}
