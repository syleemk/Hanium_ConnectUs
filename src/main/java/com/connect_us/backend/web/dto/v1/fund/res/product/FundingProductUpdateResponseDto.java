package com.connect_us.backend.web.dto.v1.fund.res.product;

import com.connect_us.backend.web.dto.v1.ResponseDto;
import lombok.Builder;

public class FundingProductUpdateResponseDto extends ResponseDto {

    @Builder
    public FundingProductUpdateResponseDto(Boolean success, String message) {
        super(success, message);
    }
}
