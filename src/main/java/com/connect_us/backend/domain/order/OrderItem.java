package com.connect_us.backend.domain.order;

import com.connect_us.backend.domain.enums.Status;
import com.connect_us.backend.domain.product.Product;

import javax.persistence.*;
import java.beans.FeatureDescriptor;

public class OrderItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderItem_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column
    private int product_cnt;

    @Enumerated(EnumType.STRING)
    private Status status;
}
