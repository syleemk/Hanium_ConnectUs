package com.connect_us.backend.web.dto.v1.fund.res.cart;

import com.connect_us.backend.web.dto.v1.ResponseDto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FundingCartItemDeleteResponseDto extends ResponseDto {

    @Builder
    public FundingCartItemDeleteResponseDto(ResponseDto responseDto){
        super(responseDto.getSuccess(),responseDto.getMessage());
    }
}
