package com.connect_us.backend.web.dto.v1.email;

import com.connect_us.backend.web.dto.v1.ResponseDto;

public class MailValidationResponseDto extends ResponseDto {
    public MailValidationResponseDto(Boolean success, String message) {
        super(success, message);
    }
}
