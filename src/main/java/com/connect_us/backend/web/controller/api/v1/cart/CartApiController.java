package com.connect_us.backend.web.controller.api.v1.cart;

import com.connect_us.backend.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "api/v1/cart")
public class CartApiController {

    private final CartService cartService;

    @PutMapping(path = "/products/{id}")
    public void add(Authentication authentication, @PathVariable Long id){

    }

    @GetMapping
    public void get(Authentication authentication) {

    }

    @DeleteMapping(path = "/products/{id}")
    public void delete(Authentication authentication, @PathVariable Long id) {

    }
}
