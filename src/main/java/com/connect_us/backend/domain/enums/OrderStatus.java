package com.connect_us.backend.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {
   DONE("DONE","결제완료"),
   PREPARE("PREPARE", "배송준비중"),
   SHIP("SHIP", "배송중"),
   ARRIVE("ARRIVE","배송완료"),
   CANCLE("CANCLE","주문취소");

   private final String key;
   private final String value;
   public String getKey(){
      return key;
   }
}
