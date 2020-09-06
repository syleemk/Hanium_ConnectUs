package com.connect_us.backend.web.dto.v1.product;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductUpdateResponseDto {
    private Boolean success;
    private String message;

    @Builder
    public ProductUpdateResponseDto(Boolean success, String message){
        this.success = success;
        this.message = message;
    }
}
