package com.connect_us.backend.domain.user;

import com.connect_us.backend.domain.BaseTimeEntity;
import com.connect_us.backend.domain.enums.Gender;
import com.connect_us.backend.domain.enums.Role;
import com.connect_us.backend.domain.enums.Social;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Social social;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable=false)
    private String name;

    @Column
    private String addr;

    @Column
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column
    private Gender gender;

    @Column
    private Integer point;

    @Builder

    public User(String email, String password, Social social, Role role, String name
    , String addr, String phoneNumber, Gender gender, int point){
        this.email=email;
        this.password=password;
        this.social=social;
        this.role=role;
        this.name=name;
        this.addr=addr;
        this.phoneNumber=phoneNumber;
        this.gender=gender;
        this.point=point;
    }

    public User updateSocial(String name, String addr, String phoneNumber){//social에서 사용자 정보 업데이트시 자동 반영
        this.name=name;
        this.addr=addr;
        this.phoneNumber=phoneNumber;

        return this;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }

}
