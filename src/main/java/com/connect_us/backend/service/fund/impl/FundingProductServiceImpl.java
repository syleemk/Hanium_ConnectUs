package com.connect_us.backend.service.fund.impl;

import com.connect_us.backend.domain.fund.FundingProduct;
import com.connect_us.backend.domain.fund.FundingProductRepository;
import com.connect_us.backend.service.fund.FundingProductService;
import com.connect_us.backend.web.dto.v1.fund.FundingProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FundingProductServiceImpl implements FundingProductService {
    private final FundingProductRepository fundingProductRepository;

    @Transactional
    @Override
    public void createFundingProduct(FundingProduct fundingProduct) {
        fundingProductRepository.save(fundingProduct);
    }

    @Override
    public FundingProductResponseDto findById(Long id) {
        FundingProduct entity = fundingProductRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 펀드가 없습니다. id="+id));

        return new FundingProductResponseDto(entity);
    }


}
