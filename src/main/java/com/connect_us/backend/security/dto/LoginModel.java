package com.connect_us.backend.security.dto;

import com.connect_us.backend.domain.account.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginModel {
   private String username;
   private String password;
}
