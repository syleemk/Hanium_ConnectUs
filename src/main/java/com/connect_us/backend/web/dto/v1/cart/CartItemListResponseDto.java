package com.connect_us.backend.web.dto.v1.cart;

import com.connect_us.backend.domain.cart.CartItem;
import com.connect_us.backend.web.dto.v1.ResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

public class CartItemListResponseDto extends ResponseDto {

    private List<Data> data;

    @Getter
    @NoArgsConstructor
    class Data {
        private String productName;
        private int productCnt;
        private int price;

        public Data(CartItem entity){
            this.productName = entity.getProduct().getName();
            this.price = entity.getProduct().getPrice();
            this.productCnt = entity.getProductCnt();
        }
    }

    @Builder
    CartItemListResponseDto(Boolean success, String message, List<CartItem> data){
        super(success, message);
        this.data = data.stream().map(Data::new).collect(Collectors.toList());
    }
}
