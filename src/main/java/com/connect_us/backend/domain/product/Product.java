package com.connect_us.backend.domain.products;

import com.connect_us.backend.domain.categories.Category;
import com.connect_us.backend.domain.enums.ProductStatus;
import com.connect_us.backend.domain.users.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    Category category;

    @ManyToOne
    @JoinColumn(name = "userid")
    private Users users;

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
}
