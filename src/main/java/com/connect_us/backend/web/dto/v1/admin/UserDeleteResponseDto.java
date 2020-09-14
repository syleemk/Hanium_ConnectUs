package com.connect_us.backend.web.dto.v1.admin;

import com.connect_us.backend.web.dto.v1.ResponseDto;

public class UserDeleteResponseDto extends ResponseDto {
    private Long id;

    public UserDeleteResponseDto(Boolean success, String message, Long id) {
        super(success, message);
        this.id = id;
    }
}
