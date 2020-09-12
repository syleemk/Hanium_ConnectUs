package com.connect_us.backend.domain.order;

import com.connect_us.backend.domain.enums.Status;
import com.connect_us.backend.domain.product.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class ProductOrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_order_item_id")
    private Long id; // PK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product; // FK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "base_order_id")
    private BaseOrder baseOrder; // FK

    @Column
    private int product_cnt;

    @Builder
    public ProductOrderItem(Product product, BaseOrder baseOrder, int product_cnt){
        this.product = product;
        this.baseOrder = baseOrder;
        this.product_cnt = product_cnt;
    }

}
