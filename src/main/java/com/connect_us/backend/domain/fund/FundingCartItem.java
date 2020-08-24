package com.connect_us.backend.domain.fund;

import com.connect_us.backend.domain.enums.FundingStatus;
import com.connect_us.backend.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class FundingCartItem extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "funding_cart_item_id")
    private Long id; //PK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funding_cart_id",nullable = false)
    private FundingCart fundingCart; // FK

    @Column(name = "funding_cnt",nullable = false)
    private int fundingCount; // FundingCartItem 의 개수

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funding_product_id",nullable = false)
    private FundingProduct fundingProduct; // FK

    @Enumerated(EnumType.STRING)
    @Column(name = "funding_status",nullable = false)
    private FundingStatus fundingStatus = FundingStatus.NORMAL;
}
