package com.connect_us.backend.web.controller.api.v1.cart;

import com.connect_us.backend.service.cart.CartService;
import com.connect_us.backend.web.dto.v1.cart.CartItemAddResponseDto;
import com.connect_us.backend.web.dto.v1.cart.CartItemAddResquestDto;
import com.connect_us.backend.web.dto.v1.cart.CartItemListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "api/v1/cart")
public class CartApiController {

    private final CartService cartService;

    @PostMapping
    public CartItemAddResponseDto add(Authentication authentication, @RequestBody CartItemAddResquestDto resquestDto){
        // authenticaiton에서 user 정보 가져오는 것
        // 추후 accountService의 메서드로 대체하는 것이 좋아보임
        String accountEmail = authentication.getName();
        return cartService.add(accountEmail, resquestDto);
    }

    @GetMapping
    public CartItemListResponseDto get(Authentication authentication) {
        // authenticaiton에서 user 정보 가져오는 것
        // 추후 accountService의 메서드로 대체하는 것이 좋아보임
        String accountEmail = authentication.getName();
        return cartService.get(accountEmail);
    }

    @DeleteMapping(path = "/products/{id}")
    public void delete(Authentication authentication, @PathVariable Long id) {

    }
}
