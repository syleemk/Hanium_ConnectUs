package com.connect_us.backend.domain.fund;

import com.connect_us.backend.domain.BaseEntity;
import com.connect_us.backend.domain.category.Category;
import com.connect_us.backend.domain.enums.FundingStatus;
import com.connect_us.backend.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class FundingProduct extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "funding_product_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String name;

    private String image;

    @Column(name = "goal_price")
    private int goalPrice; // 목표 모금액

    @Column(name = "current_price",nullable = false)
    private int currentPrice; // 현재 모금액

    @Column(name = "addr") // addr : 물품을 배송할 재난지역 주소
    private String address;

    private LocalDateTime due; // due : 펀딩 마감 날짜

    @Enumerated(EnumType.STRING)
    @Column(name = "funding_status")
    private FundingStatus fundingStatus = FundingStatus.NORMAL;
}
