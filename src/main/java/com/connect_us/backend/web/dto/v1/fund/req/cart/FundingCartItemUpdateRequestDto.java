package com.connect_us.backend.web.dto.v1.fund.req.cart;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FundingCartItemUpdateRequestDto {

    private Long fundingCartItemId;
    private int fundingInvestment;

    @Builder
    public FundingCartItemUpdateRequestDto(Long fundingCartItemId, int fundingInvestment) {
        this.fundingCartItemId = fundingCartItemId;
        this.fundingInvestment = fundingInvestment;
    }
}
