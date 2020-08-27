package com.connect_us.backend.domain.cart;

import com.connect_us.backend.domain.BaseTimeEntity;
import com.connect_us.backend.domain.account.Account;
import com.connect_us.backend.domain.enums.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Cart extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    @OneToOne
    @JoinColumn(name="account_id")
    private Account account;

    @OneToMany(mappedBy = "cart")
    List<CartItem> cartItems = new ArrayList<CartItem>();

    @Enumerated(EnumType.STRING)
    private Status status;

    @Builder
    public Cart(Account account, Status status){
        this.account = account;
        this.status = status;
    }
}
