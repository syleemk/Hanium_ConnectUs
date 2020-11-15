package com.connect_us.backend.domain.fund;

import com.connect_us.backend.domain.BaseEntity;
import com.connect_us.backend.domain.account.Account;
import com.connect_us.backend.domain.category.Category;
import com.connect_us.backend.domain.enums.FundingStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class FundingProduct extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "funding_product_id")
    private Long id; // PK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category; // FK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account; // FK

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "image",nullable = false)
    private String image;

    @Column(name = "goal_price")
    private int goalPrice; // 목표 모금액

    @Column(name = "current_price")
    private int currentPrice; // 현재 모금액

    @Column(name = "address") // addr : 물품을 배송할 재난지역 주소
    private String address;

    @Column(name = "information")
    private String information;

    @Column(name = "due")
    private LocalDateTime due; // due : 펀딩 마감 날짜

    @Column(name = "funding_status")
    @Enumerated(EnumType.STRING)
    private FundingStatus fundingStatus = FundingStatus.NORMAL;

    @OneToOne(mappedBy = "fundingProduct")
    private FundingCartItem fundingCartItem; // funding_cart_item table의 fundingProduct 의해 mapping

    @OneToOne(mappedBy = "fundingProduct")
    private FundingOrderItem fundingOrderItem; // funding_order_item table의 fundingProduct 의해 mapping

    @Builder
    public FundingProduct(Category category,Account account, String name,String image,int goalPrice,int currentPrice,String address,String information,LocalDateTime due) {
        this.category = category;
        this.account = account;
        this.name = name;
        this.image = image;
        this.goalPrice = goalPrice;
        this.currentPrice = currentPrice;
        this.address = address;
        this.information = information;
        this.due = due;
    }

    /**
     * currentPrice 정보는 변경불가
     * @see com.connect_us.backend.service.fund.impl.FundingProductServiceImpl
     * */
    public void update(String name,String image,int goalPrice,String address,String information,LocalDateTime due) {
        this.name = name;
        this.image = image;
        this.goalPrice = goalPrice;
        this.address = address;
        this.information = information;
        this.due = due;
    }

}
