package com.connect_us.backend.web.controller.api.v1.order;

import com.connect_us.backend.domain.account.Account;
import com.connect_us.backend.service.account.AccountService;
import com.connect_us.backend.service.account.impl.AccountServiceImp;
import com.connect_us.backend.service.order.OrderService;
import com.connect_us.backend.web.dto.v1.order.OrderSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/v1/order")
public class OrderApiController {
    private final AccountServiceImp accountService;
    private final OrderService orderService;

    @PostMapping
    public void save(Authentication authentication, @RequestBody OrderSaveRequestDto requestDto) {
        Account account = accountService.findByEmail(authentication.getName());
        orderService.save(account, requestDto);
    }
}
