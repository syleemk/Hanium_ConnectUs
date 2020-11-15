package com.connect_us.backend.web.dto.v1.fund.req.cart;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FundingCartItemAddRequestDto {
    private Long fundingProductId;
    private int fundingInvestment;

    @Builder
    public FundingCartItemAddRequestDto(Long fundingProductId, int fundingInvestment){
        this.fundingProductId = fundingProductId;
        this.fundingInvestment = fundingInvestment;
    }
}
