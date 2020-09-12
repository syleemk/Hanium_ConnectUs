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

    @Column(nullable = false)
    private String name;

    @Column
    private String image;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int stock;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_status")
    private ProductStatus productStatus = ProductStatus.SALE;

    @Builder
    public Product(Category category, Account account, String name, String image, int price, int stock){
        this.category = category;
        this.account = account;
        this.name = name;
        this.image = image;
        this.price = price;
        this.stock = stock;
    }

    //영속성 컨텍스트 (트랜잭션안에서 데이터 가져오면, 해당 데이터는 영속성 컨텍스트가 유지된 상태, 이 상태에서 값 변경하면, 트랜잭션 끝나는 시점에 자동 반영)
    public void update(Category category, String name, String image, int price, int stock, ProductStatus productStatus){
        this.category = category;
        this.name = name;
        this.image = image;
        this.price = price;
        this.stock = stock;
        this.productStatus = productStatus;
    }

    public void soldOut() {
        this.productStatus = ProductStatus.SOLD_OUT;
    }

    public void onSale() {
        this.productStatus = ProductStatus.SALE;
    }
}
