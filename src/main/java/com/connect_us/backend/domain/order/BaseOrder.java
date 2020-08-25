package com.connect_us.backend.domain.order;

import com.connect_us.backend.domain.account.Account;
import com.connect_us.backend.domain.fund.FundingOrderItem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class BaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="account_id")
    private Account account;

    @OneToMany(mappedBy = "fundingBaseOrder",cascade = CascadeType.ALL)
    private List<FundingOrderItem> fundingOrderItems = new ArrayList<>(); // funding_order_item table의 fundingOrder 의해 mapping

    @OneToMany(mappedBy = "baseOrder",cascade = CascadeType.ALL)
    private List<ProductOrderItem> productOrderItems = new ArrayList<>();
}
