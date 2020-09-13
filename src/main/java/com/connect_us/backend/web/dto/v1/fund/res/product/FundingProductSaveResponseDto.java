package com.connect_us.backend.web.dto.v1.fund.res.product;

import com.connect_us.backend.web.dto.v1.ResponseDto;
import lombok.Builder;

public class FundingProductSaveResponseDto extends ResponseDto {

    @Builder
    public FundingProductSaveResponseDto(Boolean success, String message) {
        super(success, message);
    }
}
