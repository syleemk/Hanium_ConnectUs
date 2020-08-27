package com.connect_us.backend.domain.order;

import com.connect_us.backend.domain.BaseEntity;
import com.connect_us.backend.domain.account.Account;
import com.connect_us.backend.domain.enums.OrderStatus;
import com.connect_us.backend.domain.enums.OrderType;
import com.connect_us.backend.domain.enums.Status;
import com.connect_us.backend.domain.fund.FundingOrderItem;
import com.connect_us.backend.domain.payment.Payment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class BaseOrder extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "base_order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany(mappedBy = "baseOrder", cascade = CascadeType.ALL)
    private List<ProductOrderItem> productOrderItems = new ArrayList<ProductOrderItem>();

    @Enumerated(EnumType.STRING)
    private OrderType orderType; // fund, product 주문 구분

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus; // 배송 상태, 구매 상태 표시

    @Column
    private Long price; // 총액

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private String number;

    @OneToMany(mappedBy = "fundingBaseOrder",cascade = CascadeType.ALL)
    private List<FundingOrderItem> fundingOrderItems = new ArrayList<>(); // funding_order_item table의 fundingBaseOrder 의해 mapping

    @OneToMany(mappedBy = "baseOrder",cascade = CascadeType.ALL)
    private List<Payment> payments = new ArrayList<>(); // payments table의 baseOrder 의해 mapping

    @Enumerated(EnumType.STRING)
    private Status status;

    @Builder
    public BaseOrder(Account account, OrderType orderType, OrderStatus orderStatus, Long price, String name, String address, String number, Status status){
        this.account = account;
        this.orderType = orderType;
        this.orderStatus = orderStatus;
        this.price = price;
        this.name = name;
        this.address = address;
        this.number = number;
        this.status = status;
    }
}
