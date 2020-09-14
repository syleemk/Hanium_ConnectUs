package com.connect_us.backend.web.dto.v1.email;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MailValidationRequestDto {
    private String email;
}
