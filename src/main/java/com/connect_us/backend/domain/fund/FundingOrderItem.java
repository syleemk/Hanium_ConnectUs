package com.connect_us.backend.domain.fund;

import com.connect_us.backend.domain.order.Order;
import lombok.Builder;
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
    private Long id; //PK

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funding_product_id")
    private FundingProduct fundingProduct; // FK

    @Column(name = "funding_cnt",nullable = false)
    private int fundingCount;

    @Column(name = "funding_name",nullable = false)
    private String fundingName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order fundingOrder; // FK

    @Builder
    public FundingOrderItem(int fundingCount,String fundingName) {
        this.fundingCount = fundingCount;
        this.fundingName = fundingName;
    }
}
