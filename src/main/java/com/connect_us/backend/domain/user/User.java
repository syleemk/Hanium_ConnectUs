package com.connect_us.backend.domain.user;

import com.connect_us.backend.domain.BaseTimeEntity;
import com.connect_us.backend.domain.cart.Cart;
import com.connect_us.backend.domain.enums.Gender;
import com.connect_us.backend.domain.enums.Role;
import com.connect_us.backend.domain.enums.Social;
import com.connect_us.backend.domain.enums.UserStatus;
import com.connect_us.backend.domain.order.Order;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;
//@Column: not necessary if the field has no specific condition

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends BaseTimeEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
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

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @OneToOne(mappedBy = "user")//cart table의 user에 의해 mapping
    private Cart cart;

    @OneToMany(mappedBy = "user") //order table의 user에 의해 mapping
    private List<Order> orders = new ArrayList<>();

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

    public User update(String name){//social에서 사용자 정보 업데이트시 자동 반영
        this.name=name;
        return this;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }

    /*UserDetails 상속, db에서 유저정보 가져와서 담음*/

    //권한 Collection 형태로 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        for(Role r : Role.values()){ //enum list로 가져옴
            roles.add(new SimpleGrantedAuthority(r.name()));
        }
        return roles;
    }

    //사용자 email 반환
    @Override
    public String getUsername() {
        return email;
    }

    //사용자 pw 반환
    @Override
    public String getPassword() {
        return password;
    }

    //계정 만료 여부 반환, true: 만료되지 않음
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //계정 잠금 여부 반환
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //pw 만료 여부 반환
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //계정 사용 가능 여부 반환
    @Override
    public boolean isEnabled() {
        return true;
    }
}
