package com.connect_us.backend.web.dto.v1.order;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderItemDto {
    private Long productId;
    private int productCnt;

    @Builder
    public OrderItemDto(Long productId, int productCnt){
        this.productId = productId;
        this.productCnt = productCnt;
    }
}
