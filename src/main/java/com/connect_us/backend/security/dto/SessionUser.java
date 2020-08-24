package com.connect_us.backend.security.dto;

import com.connect_us.backend.domain.account.Account;

import java.io.Serializable;

public class SessionUser implements Serializable {
    private String name;
    private String email;

    public SessionUser(Account account){
        this.name= account.getName();
        this.email=account.getEmail();
    }
}
