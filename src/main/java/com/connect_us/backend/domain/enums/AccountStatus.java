package com.connect_us.backend.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AccountStatus {
   NORMAL("NORMAL","일반"),
   DORMANT("DORMANT","휴면"),
   DELETE("DELETE","탈퇴");

   private final String key;
   private final String value;
   public String getKey(){
      return key;
   }
}
