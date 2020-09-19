package com.connect_us.backend.domain.fund;

import com.connect_us.backend.domain.BaseEntity;
import com.connect_us.backend.domain.account.Account;
import com.connect_us.backend.domain.enums.Status;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class FundingCart extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "funding_cart_id")
    private Long id; // PK

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account; // FK

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fundingCart", cascade = CascadeType.ALL)
    private List<FundingCartItem> fundingCartItems = new ArrayList<>(); // funding_cart_item table의 fundingCart 의해 mapping

    /**
     * 카트에 담을 아이템들을 삭제하거나 추가할 수 있다.
     * */
    public void update(List<FundingCartItem> fundingCartItems) {
        this.fundingCartItems = fundingCartItems;
    }

    public List<Long> getFundingProductIds() {
        List<Long> list = new ArrayList<>();
        this.fundingCartItems.forEach(e -> list.add(e.getFundingProduct().getId()));
        return list;
    }

    @Builder
    public FundingCart(Account account) {
        this.account = account;
    }
}
