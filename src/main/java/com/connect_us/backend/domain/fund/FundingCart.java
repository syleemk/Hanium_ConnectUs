package com.connect_us.backend.domain.fund;

import com.connect_us.backend.domain.BaseEntity;
import com.connect_us.backend.domain.account.Account;
import com.connect_us.backend.domain.enums.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Entity
public class FundingCart extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
}
