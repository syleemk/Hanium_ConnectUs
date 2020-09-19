package com.connect_us.backend.web.dto.v1.fund.data;

import com.connect_us.backend.domain.fund.FundingCartItem;
import com.connect_us.backend.domain.fund.FundingProduct;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FundingCartItemData {

    private Long id;
    private String fundingName;
    private int fundingCurrentPrice;
    private int fundingInvestment;

    public FundingCartItemData(FundingCartItem entity) {
        this.id = entity.getId();
        this.fundingName = entity.getFundingProduct().getName();
        this.fundingCurrentPrice = entity.getFundingProduct().getCurrentPrice();
        this.fundingInvestment = entity.getFundingProduct().getFundingCartItem().getFundingInvestment();
    }
}
