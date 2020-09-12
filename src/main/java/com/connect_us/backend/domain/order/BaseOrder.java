package com.connect_us.backend.domain.order;
import com.connect_us.backend.domain.BaseEntity;
import com.connect_us.backend.domain.account.Account;
import com.connect_us.backend.domain.fund.FundingOrderItem;
import com.connect_us.backend.domain.payment.Payment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Getter @Setter
@NoArgsConstructor
public class BaseOrder extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="base_order_id")
    private Long id; // PK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="account_id")
    private Account account; // FK
    @Column(name = "price")
    private int price;
    /**
     * type: 판매 상품 구입 위한 결제인지 펀딩을 위한 결제인지 확인하기 위한 필드
     * */
    @Column(name = "type")
    private String type;
    @Column(name = "name")
    private String name;
    @Column(name = "addr")
    private String address;
    @OneToMany(mappedBy = "fundingBaseOrder",cascade = CascadeType.ALL)
    private List<FundingOrderItem> fundingOrderItems = new ArrayList<>(); // funding_order_item table의 fundingBaseOrder 의해 mapping
    @OneToMany(mappedBy = "baseOrder",cascade = CascadeType.ALL)
    private List<ProductOrderItem> productOrderItems = new ArrayList<>();  // base_order table의 baseOrder 의해 mapping
    @OneToMany(mappedBy = "baseOrder",cascade = CascadeType.ALL)
    private List<Payment> payments = new ArrayList<>(); // payments table의 baseOrder 의해 mapping
}