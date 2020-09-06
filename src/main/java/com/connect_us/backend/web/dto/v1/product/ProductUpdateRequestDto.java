package com.connect_us.backend.web.dto.v1.product;

import com.connect_us.backend.domain.enums.ProductStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductUpdateRequestDto {
    private String categoryName;
    private String name;
    private String image;
    private int price;
    private int stock;
    private ProductStatus productStatus;
}
