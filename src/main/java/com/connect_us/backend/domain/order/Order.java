package com.connect_us.backend.domain.order;

import com.connect_us.backend.domain.BaseTimeEntity;
import com.connect_us.backend.domain.enums.OrderStatus;
import com.connect_us.backend.domain.enums.OrderType;
import com.connect_us.backend.domain.enums.Status;
import com.connect_us.backend.domain.user.User;
import lombok.Builder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class Order extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<OrderItem>();

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


    @Enumerated(EnumType.STRING)
    private Status status;

    @Builder
    public Order(User user, OrderType orderType, OrderStatus orderStatus, Long price, String name, String address, String number, Status status){
        this.user = user;
        this.orderType = orderType;
        this.orderStatus = orderStatus;
        this.price = price;
        this.name = name;
        this.address = address;
        this.number = number;
        this.status = status;
    }
}
