package com.connect_us.backend.web.dto.v1.product;

import com.connect_us.backend.web.dto.v1.ResponseDto;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProductUpdateResponseDto extends ResponseDto {

    @Builder
    public ProductUpdateResponseDto(Boolean success, String message){
        super(success, message);
    }
}
