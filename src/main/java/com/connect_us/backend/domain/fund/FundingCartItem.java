package com.connect_us.backend.domain.fund;

import com.connect_us.backend.domain.enums.FundingStatus;
import com.connect_us.backend.domain.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class FundingCartItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

//    @Enumerated(EnumType.STRING)
//    @Column(name = "funding_status",nullable = false)
//    private FundingStatus fundingStatus = FundingStatus.NORMAL; // 삭제해도 무방하지 않을까? 어차피 fundingProduct에서 볼수 있는 정보잖아

    @Builder
    public FundingCartItem(int fundingCount) {
        this.fundingCount = fundingCount;
    }

    /**
     * 개수만 변경 가능하다
     * CartItem을 삭제하기위해선 FundingCart내의 update메소드를 사용할 것
     * */
    public void update(int fundingCount) {
        this.fundingCount = fundingCount;
    }
}
