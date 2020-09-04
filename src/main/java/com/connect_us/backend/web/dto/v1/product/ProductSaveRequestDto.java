package com.connect_us.backend.web.dto.v1.product;

import com.connect_us.backend.domain.account.Account;
import com.connect_us.backend.domain.category.Category;
import com.connect_us.backend.domain.enums.ProductStatus;
import com.connect_us.backend.domain.product.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductSaveRequestDto {
    private String categoryName;
    private String accountEmail;
    private String name;
    private String image;
    private int price;
    private int stock;

    @Builder
    public ProductSaveRequestDto(String categoryName, String accountEmail, String name, String image, int price, int stock, ProductStatus productStatus){
        this.categoryName = categoryName;
        this.accountEmail = accountEmail;
        this.name = name;
        this.image = image;
        this.price = price;
        this.stock = stock;
    }

    public Product toEntity(Category category, Account account) {
        return Product.builder()
                .category(category)
                .account(account)
                .name(name)
                .image(image)
                .price(price)
                .stock(stock)
                .build();
    }

}
