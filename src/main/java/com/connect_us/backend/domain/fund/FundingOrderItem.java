package com.connect_us.backend.domain.fund;

import com.connect_us.backend.domain.order.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class FundingOrderItem {

    @Id
    @GeneratedValue
    @Column(name = "funding_order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funding_product_id")
    FundingProduct fundingProduct;

    @Column(name = "funding_cnt")
    private int fundingCount;

    @Column(name = "funding_name")
    private String fundingName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;
}
