package com.connect_us.backend.web.dto.v1.order;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class OrderSaveRequestDto {

    private String name;
    private String address;
    private String number;
    // 여러 개의 상품을 한번에 주문하는 경우가 있기에, 주문 상품들을 배열 형태로 전달받는다.
    private List<OrderItem> products;

    @Getter
    @NoArgsConstructor
    public static class OrderItem {
        private Long productId;
        private int productCnt;

        @Builder
        public OrderItem(Long productId, int productCnt){
            this.productId = productId;
            this.productCnt = productCnt;
        }
    }

    @Builder
    public OrderSaveRequestDto(String name, String address, String number, OrderItem[] orderItems){
        this.name = name;
        this.address = address;
        this.number = number;
        this.products = Arrays.stream(orderItems).collect(Collectors.toList());
    }
}
