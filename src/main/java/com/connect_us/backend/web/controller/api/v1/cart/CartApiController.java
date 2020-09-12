package com.connect_us.backend.web.controller.api.v1.cart;

import com.connect_us.backend.service.cart.CartService;
import com.connect_us.backend.web.dto.v1.cart.CartItemAddResponseDto;
import com.connect_us.backend.web.dto.v1.cart.CartItemAddResquestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "api/v1/cart")
public class CartApiController {

    private final CartService cartService;

    @PutMapping(path = "/products")
    public CartItemAddResponseDto add(Authentication authentication, @RequestBody CartItemAddResquestDto resquestDto){
        String accountEmail = authentication.getName();
        return cartService.add(accountEmail, resquestDto);
    }

    @GetMapping
    public void get(Authentication authentication) {

    }

    @DeleteMapping(path = "/products/{id}")
    public void delete(Authentication authentication, @PathVariable Long id) {

    }
}
