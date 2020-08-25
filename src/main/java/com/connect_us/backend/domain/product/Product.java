package com.connect_us.backend.domain.product;

import com.connect_us.backend.domain.BaseEntity;
import com.connect_us.backend.domain.account.Account;
import com.connect_us.backend.domain.category.Category;
import com.connect_us.backend.domain.enums.ProductStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @Column
    private String name;

    @Column
    private String image;

    @Column
    private int price;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_status")
    private ProductStatus productStatus;

    @Column
    private int stock;

    @Builder
    public Product(Category category, Account account, String name, String image, int price, ProductStatus productStatus, int stock){
        this.category = category;
        this.account = account;
        this.name = name;
        this.image = image;
        this.price = price;
        this.productStatus = productStatus;
        this.stock = stock;
    }

}
