package com.connect_us.backend.web.dto.v1.cart;

import com.connect_us.backend.web.dto.v1.ResponseDto;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CartItemAddResponseDto extends ResponseDto {

    @Builder
    CartItemAddResponseDto(Boolean success, String message){
        super(success, message);
    }
}
