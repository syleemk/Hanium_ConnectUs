package com.connect_us.backend.web.controller.api.v1.order;

import com.connect_us.backend.domain.account.Account;
import com.connect_us.backend.service.account.impl.AccountServiceImp;
import com.connect_us.backend.service.order.OrderService;
import com.connect_us.backend.service.payment.KakaoPayService;
import com.connect_us.backend.web.dto.v1.order.OrderListResponseDto;
import com.connect_us.backend.web.dto.v1.order.OrderSaveRequestDto;
import com.connect_us.backend.web.dto.v1.order.OrderSaveResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/orders")
public class OrderApiController {
    private final AccountServiceImp accountService;
    private final OrderService orderService;
    private final KakaoPayService kakaoPayService;

    @PostMapping("/products")
    public OrderSaveResponseDto save(Authentication authentication, @RequestBody OrderSaveRequestDto requestDto) {
        Account account = accountService.findByEmail(authentication.getName());

        //주문등록 (주문 DTO 를 받아와서 결제 API 에 전달)
        orderService.save(account, requestDto);

        //주문 등록 후, 결제 API 호출한다. (Kakao Pay api)
        kakaoPayService.KakaoPayReady();

    }

    @GetMapping("/products")
    public OrderListResponseDto findByAccount(Authentication authentication,
                                              @PageableDefault (sort = {"modifiedDate"}, direction = Sort.Direction.DESC) Pageable pageable){
        Account account = accountService.findByEmail(authentication.getName());
        return orderService.findByAccount(account, pageable);
    }
}
