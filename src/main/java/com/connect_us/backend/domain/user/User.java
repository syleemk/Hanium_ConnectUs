package com.connect_us.backend.domain.user;

import com.connect_us.backend.domain.BaseTimeEntity;
import com.connect_us.backend.domain.enums.Gender;
import com.connect_us.backend.domain.enums.Role;
import com.connect_us.backend.domain.enums.Social;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
//@Column: not necessary if the field has no specific condition

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
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
    @Column(nullable = false)
    private Social social;

    private Long point;

    @Builder//initialize
    public User(String email, String password, String name, String phone, String addr,
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

    public User updateSocial(String name, String addr, String phone){//social에서 사용자 정보 업데이트시 자동 반영
        this.name=name;
        this.addr=addr;
        this.phone=phone;

        return this;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }

}
