package com.connect_us.backend.web.dto.v1.product;

import com.connect_us.backend.domain.enums.ProductStatus;
import com.connect_us.backend.domain.product.Product;
import com.connect_us.backend.web.dto.v1.ResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductFindResponseDto extends ResponseDto {

    private Data data;

    @Getter
    @NoArgsConstructor
    class Data {
        private Long id;
        private String category;
        private String account;
        private String name;
        private String image;
        private int price;
        private int stock;
        private ProductStatus productStatus;

        Data(Product entity){
            this.id = entity.getId();
            this.category = entity.getCategory().getName();
            this.account = entity.getAccount().getName();
            this.name = entity.getName();
            this.image = entity.getImage();
            this.price = entity.getPrice();
            this.stock = entity.getStock();
            this.productStatus = entity.getProductStatus();
        }
    }

    @Builder
    public ProductFindResponseDto(Boolean success, String message, Product data){
        super(success, message);
        this.data = new Data(data);
    }
}
