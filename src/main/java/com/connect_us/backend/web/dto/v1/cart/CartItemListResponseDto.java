package com.connect_us.backend.web.dto.v1.cart;

import com.connect_us.backend.domain.cart.CartItem;
import com.connect_us.backend.web.dto.v1.ResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
//@NoArgsConstructor -> 내부 data class 접근하기 위해 필요 (외부 클래스 인스턴스가 있어야 내부 인스턴스 클래스 접근가능)
//하지만 내부 클래스를 static으로 선언하면 외부클래스 생성없이 내부클래스 생성이 가능함
public class CartItemListResponseDto extends ResponseDto {

    private final List<Data> data;// = new ArrayList<>();

    @Getter // 이거 있어야 serialize 오류안남
    static class Data {
        private String productName;
        private int productCnt;
        private int price;

        public Data(CartItem entity){
            this.productName = entity.getProduct().getName();
            this.price = entity.getProduct().getPrice();
            this.productCnt = entity.getProductCnt();
        }
    }

    @Builder // builder패턴 또한 static inner class임
    CartItemListResponseDto(Boolean success, String message, List<CartItem> data){
        super(success, message);
        // Data 형식으로 map해주기 위해선 외부클래스에 NoArgsConstructor필요함
        // 그래야만 inner class에 접근할 수 있음 -> inner class 를 static하게 선언해주면 해결
        // 데이터 스트림을 array로 변환하기위해 serializer (getter)가 필요
        this.data = data.stream().map(Data::new).collect(Collectors.toList()); 
    }
}
