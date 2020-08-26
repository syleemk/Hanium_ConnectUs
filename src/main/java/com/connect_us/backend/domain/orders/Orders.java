package com.connect_us.backend.domain.orders;

import com.connect_us.backend.domain.account.Account;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Orders {

    @Id @GeneratedValue
    @Column(name="orders_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="account_id")
    private Account account;
}
