package com.connect_us.backend.domain.fund;

import com.connect_us.backend.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class FundingCartItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "funding_cart_item_id")
    private Long id; //PK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funding_cart_id",nullable = false)
    private FundingCart fundingCart; // FK

    @Column(name = "funding_investment",nullable = false)
    private int fundingInvestment; //  투자금

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funding_product_id",nullable = false)
    private FundingProduct fundingProduct; // FK

    @Builder
    public FundingCartItem(FundingProduct fundingProduct,FundingCart fundingCart,int fundingInvestment) {
        this.fundingProduct = fundingProduct;
        this.fundingCart = fundingCart;
        this.fundingInvestment = fundingInvestment;
    }

    /**
     * 투자금만 변경 가능하다
     * CartItem을 삭제하기위해선 FundingCart내의 update메소드를 사용할 것
     * */
    public void update(int investment) {
        this.fundingInvestment = investment;
    }
}
