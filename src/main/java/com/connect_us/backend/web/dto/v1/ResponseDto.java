package com.connect_us.backend.web.dto.v1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {
    protected Boolean success;
    protected String message;
}
