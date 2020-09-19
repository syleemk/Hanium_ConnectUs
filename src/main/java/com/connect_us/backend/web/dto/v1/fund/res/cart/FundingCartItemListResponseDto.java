package com.connect_us.backend.web.dto.v1.fund.res.cart;

import com.connect_us.backend.domain.fund.FundingCartItem;
import com.connect_us.backend.web.dto.v1.ResponseDto;
import com.connect_us.backend.web.dto.v1.fund.data.FundingCartItemData;
import lombok.Builder;
import lombok.Getter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Getter
public class FundingCartItemListResponseDto extends ResponseDto {

    private final List<FundingCartItemData> content;

    @Builder
    public FundingCartItemListResponseDto(ResponseDto responseDto, List<FundingCartItem> fundingCartItemDataList){
        super(responseDto.getSuccess(),responseDto.getMessage());
        this.content = fundingCartItemDataList.stream().map(FundingCartItemData::new).collect(Collectors.toList());
    }
}
