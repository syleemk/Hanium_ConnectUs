package com.connect_us.backend.web.dto.v1.product;

import com.connect_us.backend.domain.account.Account;
import com.connect_us.backend.domain.category.Category;
import com.connect_us.backend.domain.enums.ProductStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductSaveRequestDto {
    private Long category;
    private Long account;
    private String name;
    private String image;
    private int price;
    private int stock;
    private ProductStatus productStatus;

    @Builder
    public ProductSaveRequestDto(Long category, Long account, String name, String image,int price, int stock, ProductStatus productStatus){
        this.category = category;
        this.account = account;
        this.name = name;
        this.image = image;
        this.price = price;
        this.stock = stock;
        this.productStatus = productStatus;
    }

}
