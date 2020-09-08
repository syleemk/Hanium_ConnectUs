package com.connect_us.backend.service.cart;

import com.connect_us.backend.domain.account.Account;
import com.connect_us.backend.domain.account.AccountRepository;
import com.connect_us.backend.domain.cart.Cart;
import com.connect_us.backend.domain.cart.CartItemRepository;
import com.connect_us.backend.domain.cart.CartRepository;
import com.connect_us.backend.domain.product.Product;
import com.connect_us.backend.domain.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CartService {
    private final ProductRepository productRepository;
    private final AccountRepository accountRepository;
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;

    public void add(String accountEmail, Long id) {
        Account account = accountRepository.findByEmail(accountEmail);
        Cart cart = account.getCart();

        Product product = productRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 상품이 없습니다. id=" + id));


    }

    public void get(String accountEmail) {

    }

    public void delete(String accountEmail, Long id){

    }
}
