package com.connect_us.backend.domain.category;

import com.connect_us.backend.domain.BaseTimeEntity;
import com.connect_us.backend.domain.product.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Category extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "category")
    List<Product> Products = new ArrayList<Product>();

    @Builder
    public Category(String name){
        this.name = name;
    }
}
