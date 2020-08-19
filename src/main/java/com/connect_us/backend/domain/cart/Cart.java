package com.connect_us.backend.domain.cart;

import com.connect_us.backend.domain.enums.Status;
import com.connect_us.backend.domain.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    @Column
    private User user;

    @OneToMany(mappedBy = "cart")
    List<CartItem> cartItems = new ArrayList<CartItem>();

    @Enumerated(EnumType.STRING)
    private Status status;
}
