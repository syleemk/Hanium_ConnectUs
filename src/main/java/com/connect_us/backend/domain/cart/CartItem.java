package com.connect_us.backend.domain.cart;
import com.connect_us.backend.domain.BaseEntity;
import com.connect_us.backend.domain.product.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class CartItem extends BaseEntity {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @Column
    private int productCnt;

    @Builder
    public CartItem(Product product, Cart cart, int productCnt) {
        this.product = product;
        this.cart = cart;
        this.productCnt = productCnt;
    }
}
