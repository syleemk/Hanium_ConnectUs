package com.connect_us.backend.security.dto;

import com.connect_us.backend.domain.account.Account;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;

    public SessionUser(Account account){
        this.name= account.getName();
        this.email=account.getEmail();
    }
}
