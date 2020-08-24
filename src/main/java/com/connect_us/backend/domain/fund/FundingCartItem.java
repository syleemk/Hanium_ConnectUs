package com.connect_us.backend.domain.fund;

import com.connect_us.backend.domain.enums.FundingStatus;
import com.connect_us.backend.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class FundingCartItem extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "funding_cart_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funding_cart_id",nullable = false)
    FundingCart fundingCart; // FK

    @Column(name = "funding_cnt",nullable = false)
    private int fundingCount; // FundingCartItem 의 개수

    @OneToOne
    @JoinColumn(name = "funding_product_id",nullable = false)
    FundingProduct fundingProduct;

    @Enumerated(EnumType.STRING)
    @Column(name = "funding_status")
    private FundingStatus fundingStatus = FundingStatus.NORMAL;
}
