package com.connect_us.backend.service.fund;

import com.connect_us.backend.web.dto.v1.fund.product.FundingProductCreateRequestDto;
import com.connect_us.backend.web.dto.v1.fund.product.FundingProductListResponseDto;
import com.connect_us.backend.web.dto.v1.fund.product.FundingProductResponseDto;
import com.connect_us.backend.web.dto.v1.fund.product.FundingProductUpdateResquestDto;

import java.util.List;

/**
 * @see com.connect_us.backend.service.fund.impl.FundingProductServiceImpl 에서 구현
 * @see com.connect_us.backend.web.controller.v1.fund.FundingProductApiController 에서 사용
 * */
public interface FundingProductService {

    Long save(FundingProductCreateRequestDto createRequestDto);

    Long update(Long id, FundingProductUpdateResquestDto resquestDto);

    void delete(Long id);

    FundingProductResponseDto findById(Long id);

    List<FundingProductListResponseDto> findAllDesc();

}
