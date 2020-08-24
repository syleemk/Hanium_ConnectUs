package com.connect_us.backend.service.fund;

import com.connect_us.backend.domain.fund.FundingProduct;
import com.connect_us.backend.web.dto.v1.fund.FundingProductResponseDto;

public interface FundingProductService {
    void createFundingProduct(FundingProduct fundingProduct);
    FundingProductResponseDto findById(Long id);
}
