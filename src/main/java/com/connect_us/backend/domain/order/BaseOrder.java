package com.connect_us.backend.domain.order;
import com.connect_us.backend.domain.BaseEntity;
import com.connect_us.backend.domain.account.Account;
import com.connect_us.backend.domain.enums.OrderStatus;
import com.connect_us.backend.domain.enums.OrderType;
import com.connect_us.backend.domain.fund.FundingOrderItem;
import com.connect_us.backend.domain.payment.Payment;
import lombok.Builder;
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

    /**
     * type: 판매 상품 구입 위한 결제인지 펀딩을 위한 결제인지 확인하기 위한 필드
     * */
    @Enumerated(EnumType.STRING)
    private OrderType orderType;

    /**
     * orderStatus: 주문 상태(결제 대기, 결제 완료, 배송 준비, 배송 중, 배송 완료)
     */
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.PAYMENT_WAITING;

    /**
     * 배송 관련 정보 : 수령인 이름, 배송지 주소, 연락처
     */
    @Column(name = "name")
    private String name;
    @Column(name = "addr")
    private String address;
    @Column
    private String number;

    @OneToMany(mappedBy = "fundingBaseOrder",cascade = CascadeType.ALL)
    private List<FundingOrderItem> fundingOrderItems = new ArrayList<>(); // funding_order_item table의 fundingBaseOrder 의해 mapping
    @OneToMany(mappedBy = "baseOrder",cascade = CascadeType.ALL)
    private List<ProductOrderItem> productOrderItems = new ArrayList<>();  // base_order table의 baseOrder 의해 mapping
    @OneToMany(mappedBy = "baseOrder",cascade = CascadeType.ALL)
    private List<Payment> payments = new ArrayList<>(); // payments table의 baseOrder 의해 mapping

    @Builder
    public BaseOrder(Account account, OrderType orderType, String name, String address, String number){
        this.account = account;
        this.orderType = orderType;
        this.name = name;
        this.address = address;
        this.number = number;
    }

    public void paymentComplete(){
        this.orderStatus = OrderStatus.PAYMENT_COMPLETE;
    }

    public void shipping() {
        this.orderStatus = OrderStatus.SHIPPING;
    }

    public void arrived() {
        this.orderStatus = OrderStatus.ARRIVED;
    }

    public void cancle() {
        this.orderStatus = OrderStatus.CANCLE;
    }
}