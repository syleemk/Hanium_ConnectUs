package com.connect_us.backend.web.dto.v1.product;

import com.connect_us.backend.domain.product.Product;
import lombok.Getter;

// products list api요청에 사용되는 dto
// list 형식으로 표시할 정보만 넣어둔다
@Getter
public class ProductsFindResponseDto {
    private Long id;
    private String account;
    private String name;
    private String image;
    int price;

    public ProductsFindResponseDto(Product entity){
        this.id = entity.getId();
        this.account = entity.getAccount().getName();
        this.name = entity.getName();
        this.image = entity.getImage();
        this.price = entity.getPrice();
    }
}
