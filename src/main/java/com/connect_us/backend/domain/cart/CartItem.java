package com.connect_us.backend.domain.cart;

import com.connect_us.backend.domain.enums.Status;

import javax.persistence.*;

public class CartItem {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private Status status;
}
