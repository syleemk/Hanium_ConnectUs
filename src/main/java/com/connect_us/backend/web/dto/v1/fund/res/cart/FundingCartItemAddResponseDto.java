package com.connect_us.backend.web.dto.v1.fund.res.cart;

import com.connect_us.backend.web.dto.v1.ResponseDto;
import lombok.Builder;

public class FundingCartItemAddResponseDto extends ResponseDto {

    @Builder
    public FundingCartItemAddResponseDto(ResponseDto responseDto) {
        super(responseDto.getSuccess(),responseDto.getMessage());
    }
}
