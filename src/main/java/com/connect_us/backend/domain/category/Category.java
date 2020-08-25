package com.connect_us.backend.domain.category;

import com.connect_us.backend.domain.BaseEntity;
import com.connect_us.backend.domain.fund.FundingProduct;
import com.connect_us.backend.domain.product.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Product> Products = new ArrayList<Product>();

    @OneToMany(mappedBy = "category")
    private List<FundingProduct> fundingProducts = new ArrayList<>();

}
