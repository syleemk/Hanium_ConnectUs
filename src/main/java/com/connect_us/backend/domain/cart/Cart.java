package com.connect_us.backend.domain.cart;

import com.connect_us.backend.domain.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cart_id")
    private Long id;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;
}
