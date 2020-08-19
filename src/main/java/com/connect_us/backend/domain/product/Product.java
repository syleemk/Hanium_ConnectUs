package com.connect_us.backend.domain.product;

import com.connect_us.backend.domain.BaseTimeEntity;
import com.connect_us.backend.domain.category.Category;
import com.connect_us.backend.domain.enums.ProductStatus;
import com.connect_us.backend.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Null;

@Getter
@NoArgsConstructor
@Entity
public class Product extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private String name;

    @Column
    private String image;

    @Column
    private int price;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @Column
    private int stock;

    @Builder
    public Product(Category category, User user, String name, String image, int price, ProductStatus status, int stock){
        this.category = category;
        this.user = user;
        this.name = name;
        this.image = image;
        this.price = price;
        this.status = status;
        this.stock = stock;
    }

}
