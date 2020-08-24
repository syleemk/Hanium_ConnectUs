package com.connect_us.backend.domain.account;

import com.connect_us.backend.domain.BaseEntity;
import com.connect_us.backend.domain.cart.Cart;
import com.connect_us.backend.domain.enums.Gender;
import com.connect_us.backend.domain.enums.Role;
import com.connect_us.backend.domain.enums.Social;
import com.connect_us.backend.domain.enums.Status;
import com.connect_us.backend.domain.fund.FundingProduct;
import com.connect_us.backend.domain.order.Order;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
//@Column: not necessary if the field has no specific condition

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Account extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="account_id")
    private Long id;

    @Column(nullable = false, unique = true)//같은 이메일 찾기
    private String email;

    private String password;

    @Column(nullable=false)
    private String name;

    private String phone;

    private String addr;

    @Enumerated(EnumType.STRING)
    @Column
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Social social;

    private Long point;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)//cart table의 account 의해 mapping
    private Cart cart;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL) //order table의 account 의해 mapping
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "account",cascade = CascadeType.ALL) // funding_product table의 account 의해 mapping
    private List<FundingProduct> fundingProducts = new ArrayList<>();

    @Builder//initialize
    public Account(String email, String password, String name, Social social, Gender gender ,Role role){
        this.email=email;
        this.password=password;
        this.name=name;
        this.social=social;
        this.role=role;
        this.gender=gender;
    }

    public Account update(String name){//social에서 사용자 정보 업데이트시 자동 반영
        this.name=name;
        return this;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }

}
