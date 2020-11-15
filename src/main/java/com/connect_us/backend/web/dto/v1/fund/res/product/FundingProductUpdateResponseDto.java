package com.connect_us.backend.web.dto.v1.fund.res.product;

import com.connect_us.backend.domain.fund.FundingProduct;
import com.connect_us.backend.web.dto.v1.ResponseDto;
import com.connect_us.backend.web.dto.v1.fund.data.FundingData;
import lombok.Builder;

public class FundingProductUpdateResponseDto extends ResponseDto {

    private FundingData fundingData;
    @Builder
    public FundingProductUpdateResponseDto(Boolean success, String message, FundingProduct entity) {
        super(success, message);

        this.fundingData = new FundingData(entity);
    }
}
