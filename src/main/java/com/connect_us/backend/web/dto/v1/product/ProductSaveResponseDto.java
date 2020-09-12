package com.connect_us.backend.web.dto.v1.product;

import com.connect_us.backend.web.dto.v1.ResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProductSaveResponseDto extends ResponseDto {

    @Builder
    public ProductSaveResponseDto(Boolean success, String message){
        super(success, message);
    }
}
