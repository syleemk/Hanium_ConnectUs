package com.connect_us.backend.web.dto.v1.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginModel {
   private String username;
   private String password;
}
