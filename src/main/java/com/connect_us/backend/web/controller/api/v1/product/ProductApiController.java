package com.connect_us.backend.web.controller.api.v1.product;

import com.connect_us.backend.service.product.ProductService;
import com.connect_us.backend.web.dto.v1.product.ProductSaveRequestDto;
import com.connect_us.backend.web.dto.v1.product.ProductSaveResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/products")
public class ProductApiController {

    private final ProductService productService;

    @PostMapping
    public ProductSaveResponseDto save(@RequestBody ProductSaveRequestDto requestDto) {
        return productService.save(requestDto);
    }
}
