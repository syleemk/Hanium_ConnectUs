package com.connect_us.backend.domain.order;

import com.connect_us.backend.domain.BaseTimeEntity;
import com.connect_us.backend.domain.enums.Status;
import com.connect_us.backend.domain.product.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class OrderItem extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private BaseOrder baseOrder;

    @Column
    private int product_cnt;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Builder
    public OrderItem(Product product, BaseOrder baseOrder, int product_cnt, Status status){
        this.product = product;
        this.baseOrder = baseOrder;
        this.product_cnt = product_cnt;
        this.status = status;
    }
}
