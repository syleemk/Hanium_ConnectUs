package com.connect_us.backend.web.controller.v1.fund;

import com.connect_us.backend.service.fund.FundingProductService;
import com.connect_us.backend.web.dto.v1.fund.product.FundingProductCreateRequestDto;
import com.connect_us.backend.web.dto.v1.fund.product.FundingProductResponseDto;
import com.connect_us.backend.web.dto.v1.fund.product.FundingProductUpdateResquestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class FundingProductApiController {

    private final FundingProductService fundingProductService;

    @PostMapping("/v1/fund/product")
    public Long save(@RequestBody FundingProductCreateRequestDto requestDto) {
        return fundingProductService.save(requestDto);
    }

    @PutMapping("/v1/fund/product/{id}")
    public Long update(@PathVariable Long id,
                       @RequestBody FundingProductUpdateResquestDto resquestDto) {
        return fundingProductService.update(id,resquestDto);
    }

    @GetMapping("/v1/fund/product/{id}")
    public FundingProductResponseDto findById(@PathVariable Long id) {
        return fundingProductService.findById(id);
    }

    @DeleteMapping("/v1/fund/product/{id}")
    public Long delete(@PathVariable Long id) {
        fundingProductService.delete(id);
        return id;
    }
}
