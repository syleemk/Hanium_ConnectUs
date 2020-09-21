package com.connect_us.backend.security.dto;

import com.connect_us.backend.domain.account.Account;

import java.io.Serializable;

public class SocialUser implements Serializable {
    private String email;
    private String name;

    public SocialUser(Account account){
        this.email=account.getEmail();
        this.name=account.getName();
    }
}
