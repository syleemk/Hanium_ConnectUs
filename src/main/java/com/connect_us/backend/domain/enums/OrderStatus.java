package com.connect_us.backend.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {
   PAYMENT_WAITING("PAYMENT_WAITING", "결제대기"),
   PAYMENT_COMPLETE("PAYMENT_COMPLETE","결제완료"),
   PREPARE_SHIPPING("PREPARE_SHIPPING", "배송준비중"),
   SHIPPING("SHIPPING", "배송중"),
   ARRIVED("ARRIVED","배송완료"),
   CANCLE("CANCLE","주문취소");

   private final String key;
   private final String value;
   public String getKey(){
      return key;
   }
}
