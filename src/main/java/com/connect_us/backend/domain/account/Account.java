package com.connect_us.backend.domain.account;

import com.connect_us.backend.domain.BaseEntity;
import com.connect_us.backend.domain.enums.Gender;
import com.connect_us.backend.domain.enums.Role;
import com.connect_us.backend.domain.enums.Social;
import com.connect_us.backend.domain.order.BaseOrder;
import com.connect_us.backend.domain.cart.Cart;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
//@Column: not necessary if the field has no specific condition

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Account extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="account_id")
    private Long id;

    @Column(nullable = false)
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
    @OneToOne(mappedBy = "account")//cart table의 user에 의해 mapping
    private Cart cart;

    @OneToMany(mappedBy = "account") //order table의 user에 의해 mapping
    private List<BaseOrder> baseOrder = new ArrayList<>();


    @Builder//initialize
    public Account(String email, String password, String name, String phone, String addr,
                   Gender gender, Role role, Social social, Long point){
        this.email=email;
        this.password=password;
        this.name=name;
        this.social=social;
        this.role=role;
        this.phone=phone;
        this.addr=addr;
        this.gender=gender;
        this.point=point;
    }

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
        return this.role.name();
    }

}
