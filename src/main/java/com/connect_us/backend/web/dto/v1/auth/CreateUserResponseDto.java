package com.connect_us.backend.web.dto.v1.auth;

import com.connect_us.backend.web.dto.v1.ResponseDto;
import javafx.beans.binding.BooleanExpression;
import lombok.AllArgsConstructor;
import lombok.Builder;

public class CreateUserResponseDto extends ResponseDto {
    private Long id;

    @Builder
    public CreateUserResponseDto(Long id, Boolean success, String message){
        super(success,message);
        this.id=id;
    }
}
