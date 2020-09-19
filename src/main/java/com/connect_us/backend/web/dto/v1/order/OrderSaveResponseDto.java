package com.connect_us.backend.web.dto.v1.order;

import com.connect_us.backend.web.dto.v1.ResponseDto;
import lombok.Builder;
import lombok.NoArgsConstructor;

public class OrderSaveResponseDto extends ResponseDto {

    @Builder
    public OrderSaveResponseDto(Boolean success, String message){
        super(success, message);
    }
}
