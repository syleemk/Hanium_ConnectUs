package com.connect_us.backend.domain.fund;

import com.connect_us.backend.domain.enums.FundingStatus;
import com.connect_us.backend.domain.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class FundingCartItem extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "funding_cart_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funding_cart_id")
    FundingCart fundingCart; // FK

    @Enumerated(EnumType.STRING)
    private FundingStatus status; // 펀드 상품의 상태를 나타낸다

    @Column(name = "funding_cnt")
    private int fundingCount; // FundingCartItem 의 개수

    @Column(name = "funding_product_id")
    FundingProduct fundingProductId;
}
