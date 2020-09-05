package com.connect_us.backend.web.dto.v1.product;

import com.connect_us.backend.domain.enums.ProductStatus;
import com.connect_us.backend.domain.product.Product;
import lombok.Getter;

@Getter
public class ProductFindResponseDto {
    private Long id;
    private String category;
    private String account;
    private String name;
    private String image;
    private int price;
    private int stock;
    private ProductStatus productStatus;

    public ProductFindResponseDto(Product entity){
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
