package com.connect_us.backend.web.controller.api.v1.fund;

import com.connect_us.backend.service.fund.FundingProductService;
import com.connect_us.backend.web.dto.v1.ResponseDto;
import com.connect_us.backend.web.dto.v1.fund.req.product.FundingProductSaveRequestDto;
import com.connect_us.backend.web.dto.v1.fund.res.product.*;
import com.connect_us.backend.web.dto.v1.fund.req.product.FundingProductUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/fund/products")
public class FundingProductApiController {

    private final FundingProductService fundingProductService;

    /**펀딩 등록*/
    @PostMapping
    public FundingProductSaveResponseDto save(Authentication authentication, @RequestBody FundingProductSaveRequestDto requestDto) {
        String accountEmail = authentication.getName();
        return fundingProductService.save(accountEmail,requestDto);
    }

    /**펀딩 수정*/
    @PutMapping("/{id}")
    public FundingProductUpdateResponseDto update(@PathVariable Long id,
                                                  @RequestBody FundingProductUpdateRequestDto requestDto) {
        return fundingProductService.update(id,requestDto);
    }

    /**펀딩 상세 정보*/
    @GetMapping("/{id}")
    public FundingProductFindResponseDto findById(@PathVariable Long id) {
        return fundingProductService.findById(id);
    }

    /**
     * 모든 펀딩 리스트 with 페이징
     * modifiedDate 기준으로 desc(내림차순) 정렬
     * @param pageable
     * @return
     */
    @GetMapping("/all")
    public ResponseEntity<?> findAll(@PageableDefault(sort = {"modifiedDate"}, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<FundingProductListFindResponseDto> page = fundingProductService.findAll(pageable).map(FundingProductListFindResponseDto::new);
        return ResponseEntity.ok(page);
    }

    /**펀딩 이름으로 찾기 (%LIKE%) with 페이징 */
    @GetMapping("/search")
    public ResponseEntity<?> findByNameContaining(@RequestParam String name,
                                                  @PageableDefault(sort = {"modifiedDate"}, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<FundingProductListFindResponseDto> page = fundingProductService.findByNameContaining(name, pageable).map(FundingProductListFindResponseDto::new);
        return ResponseEntity.ok(page);
    }

    /**펀딩 삭제*/
    @DeleteMapping("/{id}")
    public FundingProductDeleteResponseDto delete(@PathVariable Long id) {
        return fundingProductService.delete(id);
    }
}
