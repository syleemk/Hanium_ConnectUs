package com.connect_us.backend.domain.order;

import com.connect_us.backend.domain.enums.Status;
import com.connect_us.backend.domain.fund.FundingOrderItem;
import com.connect_us.backend.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Order{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<OrderItem>();

    @OneToMany(mappedBy = "fundingOrder")
    private List<FundingOrderItem> fundingOrderItems = new ArrayList<>();

    @Column
    private int price;

    @Enumerated(EnumType.STRING)
    private Status status;


}
