package com.connect_us.backend.web.dto.v1.cart;

import com.connect_us.backend.web.dto.v1.ResponseDto;
import lombok.Builder;

public class CartItemDeleteResponseDto extends ResponseDto {
    @Builder
    public CartItemDeleteResponseDto(Boolean success, String message) {
        super(success,message);
    }
}
