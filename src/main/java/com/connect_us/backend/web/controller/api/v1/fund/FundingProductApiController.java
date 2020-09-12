package com.connect_us.backend.web.controller.api.v1.fund;

import com.connect_us.backend.web.controller.api.v1.fund.res.SimpleResponse;
import com.connect_us.backend.service.fund.FundingProductService;
import com.connect_us.backend.web.dto.v1.fund.product.req.FundingProductCreateRequestDto;
import com.connect_us.backend.web.dto.v1.fund.product.res.FundingProductListResponseDto;
import com.connect_us.backend.web.dto.v1.fund.product.res.FundingProductResponseDto;
import com.connect_us.backend.web.dto.v1.fund.product.req.FundingProductUpdateResquestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/v1/fund/products")
public class FundingProductApiController {

    private final FundingProductService fundingProductService;

    /**펀딩 등록*/
    @PostMapping
    public SimpleResponse save(@RequestBody FundingProductCreateRequestDto requestDto) {
        Long id = fundingProductService.save(requestDto);
        return new SimpleResponse(id, true, "펀딩 등록 성공");
    }

    /**펀딩 수정*/
    @PutMapping("/{id}")
    public Long update(@PathVariable Long id,
                       @RequestBody FundingProductUpdateResquestDto resquestDto) {
        return fundingProductService.update(id,resquestDto);
    }

    /**펀딩 상세 정보*/
    @GetMapping("/{id}")
    public FundingProductResponseDto findById(@PathVariable Long id)  {
        return fundingProductService.findById(id);
    }

    /**모든 펀딩*/
    @GetMapping("/all")
    public List<FundingProductListResponseDto> findAllDesc() {
        return fundingProductService.findAllDesc();
    }

    /**펀딩 이름으로 찾기 (LIKE) */
    @GetMapping("/search")
    public List<FundingProductListResponseDto> findByNameContaining(@RequestParam String name) {
        return fundingProductService.findByNameContaining(name);
    }

    /**펀딩 삭제*/
    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        fundingProductService.delete(id);
        return id;
    }
}
