package com.connect_us.backend.web.dto.v1.cart;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CartItemAddResquestDto {
    private Long id;
    private int productCnt;

    @Builder
    public CartItemAddResquestDto(Long id, int productCnt) {
        this.id = id;
        this.productCnt = productCnt;
    }
}
