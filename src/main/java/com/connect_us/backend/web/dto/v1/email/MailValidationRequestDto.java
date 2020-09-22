package com.connect_us.backend.web.dto.v1.email;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class MailValidationRequestDto {
    @NotNull(message = "Must not be null") //@Valid
    private String email;
}
