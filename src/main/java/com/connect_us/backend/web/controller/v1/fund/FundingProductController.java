package com.connect_us.backend.web.controller.v1.fund;

import com.connect_us.backend.domain.fund.FundingProduct;
import com.connect_us.backend.service.fund.FundingProductService;
import com.connect_us.backend.web.dto.v1.fund.FundingProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FundingProductController {

    private final FundingProductService fundingProductService;

    @GetMapping("/v1/fund/{id}")
    public FundingProductResponseDto findById(@PathVariable Long id) {
        return fundingProductService.findById(id);
    }
}
