package com.connect_us.backend.web.dto.v1.account;

import com.connect_us.backend.web.dto.v1.ResponseDto;

public class EditResponseDto extends ResponseDto {
    public EditResponseDto(Boolean success, String message) {
        super(success, message);
    }
}
