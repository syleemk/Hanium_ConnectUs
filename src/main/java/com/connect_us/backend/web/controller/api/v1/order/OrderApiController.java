package com.connect_us.backend.web.controller.api.v1.order;

import com.connect_us.backend.domain.account.Account;
import com.connect_us.backend.service.account.impl.AccountServiceImp;
import com.connect_us.backend.service.order.OrderService;
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

    @PostMapping("/products")
    public OrderSaveResponseDto save(Authentication authentication, @RequestBody OrderSaveRequestDto requestDto) {
        Account account = accountService.findByEmail(authentication.getName());
        return orderService.save(account, requestDto);
    }

    @GetMapping("/products")
    public OrderListResponseDto findByAccount(Authentication authentication,
                                              @PageableDefault (sort = {"modifiedDate"}, direction = Sort.Direction.DESC) Pageable pageable){
        Account account = accountService.findByEmail(authentication.getName());
        return orderService.findByAccount(account, pageable);
    }
}
