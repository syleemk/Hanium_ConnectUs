package com.connect_us.backend.web.dto.v1.fund.res.product;

import com.connect_us.backend.web.dto.v1.ResponseDto;
import lombok.Builder;

public class FundingProductDeleteResponseDto extends ResponseDto {

    @Builder
    public FundingProductDeleteResponseDto(Boolean success, String message) {
        super(success, message);
    }
}
