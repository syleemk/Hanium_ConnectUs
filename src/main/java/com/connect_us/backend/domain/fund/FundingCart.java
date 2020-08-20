package com.connect_us.backend.domain.fund;

import com.connect_us.backend.domain.BaseTimeEntity;
import com.connect_us.backend.domain.enums.Status;
import com.connect_us.backend.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class FundingCart extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "funding_cart_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; // FK

    @OneToMany(mappedBy = "funding_cart")
    private List<FundingCartItem> fundingCartItemList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Status status;
}
