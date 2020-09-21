package com.connect_us.backend.web.dto.v1.order;

import com.connect_us.backend.domain.enums.OrderStatus;
import com.connect_us.backend.domain.order.BaseOrder;
import com.connect_us.backend.domain.order.ProductOrderItem;
import com.connect_us.backend.web.dto.v1.ResponseDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class OrderListResponseDto extends ResponseDto {
    private List<Data> data;

    @Getter
    static class Data {
        private Long orderId;
        private LocalDateTime orderDate;
        private List<ProductInfo> products;
        private OrderStatus orderStatus;

        public Data(BaseOrder entity) {
            this.orderId = entity.getId();
            this.orderDate = entity.getCreatedDate();
            this.products = entity.getProductOrderItems().stream().map(ProductInfo::new).collect(Collectors.toList());
            this.orderStatus = entity.getOrderStatus();
        }
    }

    @Getter
    static class ProductInfo {
        private String image;
        private String name;
        private int price;
        private int productCnt;

        public ProductInfo(ProductOrderItem entity){
            this.image = entity.getProduct().getImage();
            this.name = entity.getProduct().getName();
            this.price = entity.getProduct().getPrice();
            this.productCnt = entity.getProduct_cnt();
        }
    }

    @Builder
    public OrderListResponseDto(Boolean success, String message, List<BaseOrder> data){
        super(success, message);
        this.data = data.stream().map(Data::new).collect(Collectors.toList());
    }
}
