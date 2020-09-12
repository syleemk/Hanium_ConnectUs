package com.connect_us.backend.service.fund;

import com.connect_us.backend.web.controller.api.v1.fund.FundingProductApiController;
import com.connect_us.backend.web.dto.v1.fund.product.req.FundingProductCreateRequestDto;
import com.connect_us.backend.web.dto.v1.fund.product.res.FundingProductListResponseDto;
import com.connect_us.backend.web.dto.v1.fund.product.res.FundingProductResponseDto;
import com.connect_us.backend.web.dto.v1.fund.product.req.FundingProductUpdateResquestDto;

import java.util.List;

/**
 * @see com.connect_us.backend.service.fund.impl.FundingProductServiceImpl 에서 구현
 * @see FundingProductApiController 에서 사용
 * */
public interface FundingProductService {

    Long save(FundingProductCreateRequestDto createRequestDto);

    Long update(Long id, FundingProductUpdateResquestDto resquestDto);

    void delete(Long id);

    FundingProductResponseDto findById(Long id);

    List<FundingProductListResponseDto> findByNameContaining(String name);

    List<FundingProductListResponseDto> findAllDesc();

}
