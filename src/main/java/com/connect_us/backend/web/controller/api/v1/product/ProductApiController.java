package com.connect_us.backend.web.controller.api.v1.product;

import com.connect_us.backend.domain.product.Product;
import com.connect_us.backend.service.product.ProductService;
import com.connect_us.backend.web.dto.v1.product.ProductSaveRequestDto;
import com.connect_us.backend.web.dto.v1.product.ProductSaveResponseDto;
import com.connect_us.backend.web.dto.v1.product.ProductsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/products") //URI는 복수형 사용
public class ProductApiController {

    private final ProductService productService;

    @PostMapping
    public ProductSaveResponseDto save(@RequestBody ProductSaveRequestDto requestDto) {
        return productService.save(requestDto);
    }

    // 리스트 페이징 처리해서 전달
    @GetMapping
    public ResponseEntity<?> findAll
            // 기본 정렬, 수정일자 기준 내림차순 (최신순)
            (@PageableDefault(sort = {"modifiedDate"}, direction = Sort.Direction.DESC) Pageable pageable){
        Page<ProductsResponseDto> page = productService.findAll(pageable).map(ProductsResponseDto::new);
        return ResponseEntity.ok(page);
    }
}
