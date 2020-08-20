package com.connect_us.backend.domain.fund;

import com.connect_us.backend.domain.BaseTimeEntity;
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
public class FundingProduct extends BaseTimeEntity {

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

    private int goal_price; // 목표 모금액

    @Column(nullable = false)
    private int current_price; // 현재 모금액

    @Enumerated(EnumType.STRING)
    private FundingStatus status;

    @Column(name = "addr") // addr : 물품을 배송할 재난지역 주소
    private String address;

    private LocalDateTime due; // due : 펀딩 마감 날짜
}
