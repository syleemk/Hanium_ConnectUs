package com.connect_us.backend.domain.users;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status {
   NORMAL("NORMAL","일반"),
   DORMANT("DORMANT","휴면"),
   DELETE("DELETE","탈퇴");

   private final String key;
   private final String value;
   public String getKey(){
      return key;
   }
}
