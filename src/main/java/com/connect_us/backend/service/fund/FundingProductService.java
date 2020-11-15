package com.connect_us.backend.service.fund;

import com.connect_us.backend.domain.fund.FundingProduct;
import com.connect_us.backend.service.fund.impl.FundingProductServiceImpl;
import com.connect_us.backend.web.controller.api.v1.fund.FundingProductApiController;
import com.connect_us.backend.web.dto.v1.fund.req.product.*;
import com.connect_us.backend.web.dto.v1.fund.res.product.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @see FundingProductServiceImpl 에서 구현
 * @see FundingProductApiController 에서 사용
 * */
public interface FundingProductService {

    FundingProductSaveResponseDto save(String accountEmail,FundingProductSaveRequestDto requestDto);

    FundingProductUpdateResponseDto update(Long id, FundingProductUpdateRequestDto requestDto);

    FundingProductDeleteResponseDto delete(Long id);

    FundingProductFindResponseDto findById(Long id);

    Page<FundingProduct> findByNameContaining(String name, Pageable pageable);

    Page<FundingProduct> findAll(Pageable pageable);

}
