package com.connect_us.backend.domain.order;

import com.connect_us.backend.domain.enums.Status;
import com.connect_us.backend.domain.product.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class ProductOrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "orderItem_id")
    private Long id; // PK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product; // FK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private BaseOrder baseOrder; // FK

    @Column
    private int product_cnt;

    @Enumerated(EnumType.STRING)
    private Status status;
}
