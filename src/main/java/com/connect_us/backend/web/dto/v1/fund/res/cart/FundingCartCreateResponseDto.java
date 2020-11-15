package com.connect_us.backend.web.dto.v1.fund.res.cart;

import com.connect_us.backend.web.dto.v1.ResponseDto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FundingCartCreateResponseDto extends ResponseDto {

    @Builder
    public FundingCartCreateResponseDto(ResponseDto responseDto){
        super(responseDto.getSuccess(),responseDto.getMessage());
    }
}
