package com.connect_us.backend.domain.cart;

import com.connect_us.backend.domain.BaseTimeEntity;
import com.connect_us.backend.domain.enums.Status;
import com.connect_us.backend.domain.user.User;
import lombok.Builder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class Cart extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    @Column
    private User user;

    @OneToMany(mappedBy = "cart")
    List<CartItem> cartItems = new ArrayList<CartItem>();

    @Enumerated(EnumType.STRING)
    private Status status;

    @Builder
    public Cart(User user, Status status){
        this.user = user;
        this.status = status;
    }
}
