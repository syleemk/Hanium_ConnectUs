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
@RequestMapping(path = "/v1/fund/products")
public class FundingProductApiController {

    private final FundingProductService fundingProductService;

    @PostMapping
    public Long save(@RequestBody FundingProductCreateRequestDto requestDto) {
        return fundingProductService.save(requestDto);
    }

    @PutMapping("/{id}")
    public Long update(@PathVariable Long id,
                       @RequestBody FundingProductUpdateResquestDto resquestDto) {
        return fundingProductService.update(id,resquestDto);
    }

    @GetMapping("/{id}")
    public FundingProductResponseDto findById(@PathVariable Long id) {
        return fundingProductService.findById(id);
    }

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        fundingProductService.delete(id);
        return id;
    }
}
