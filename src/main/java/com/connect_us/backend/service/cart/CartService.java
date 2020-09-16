package com.connect_us.backend.service.cart;

import com.connect_us.backend.domain.account.Account;
import com.connect_us.backend.domain.account.AccountRepository;
import com.connect_us.backend.domain.cart.Cart;
import com.connect_us.backend.domain.cart.CartItem;
import com.connect_us.backend.domain.cart.CartItemRepository;
import com.connect_us.backend.domain.cart.CartRepository;
import com.connect_us.backend.domain.product.Product;
import com.connect_us.backend.domain.product.ProductRepository;
import com.connect_us.backend.web.dto.v1.cart.CartItemAddResponseDto;
import com.connect_us.backend.web.dto.v1.cart.CartItemAddResquestDto;
import com.connect_us.backend.web.dto.v1.cart.CartItemDeleteResponseDto;
import com.connect_us.backend.web.dto.v1.cart.CartItemListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CartService {
    private final ProductRepository productRepository;
    private final AccountRepository accountRepository;
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;

    // 카트 생성 메서드
    @Transactional
    public void create(Account account) {
        cartRepository.save(Cart.builder()
                .account(account)
                .build());
    }

    //카트 삭제 메서드
    @Transactional
    public void delete(Account account){
        Cart cart = account.getCart();
        cart.softDelete();
    }

    // 카트에 물품 추가
    @Transactional
    public CartItemAddResponseDto add(String accountEmail, CartItemAddResquestDto resquestDto) {
        Account account = accountRepository.findByEmail(accountEmail);
        Cart cart = account.getCart();

        Product product = productRepository.findById(resquestDto.getId())
                .orElseThrow(()-> new IllegalArgumentException("해당 상품이 없습니다. id=" + resquestDto.getId()));

        CartItem cartItem = CartItem.builder()
                .cart(cart)
                .product(product)
                .productCnt(resquestDto.getProductCnt())
                .build();

        cartItemRepository.save(cartItem);

        return CartItemAddResponseDto.builder()
                .success(true)
                .message("장바구니에 상품이 추가되었습니다.")
                .build();
    }

    // 카트 물품 목록 조회
    @Transactional(readOnly = true)
    public CartItemListResponseDto get(String accountEmail) {
        Account account = accountRepository.findByEmail(accountEmail);
        Cart cart = cartRepository.getOne(account.getId());

        cart.getCartItems().forEach(e -> System.out.println(e.getProduct().getName()));
        return CartItemListResponseDto.builder()
                .success(true)
                .message("장바구니 목록 조회 성공")
                .data(cart.getCartItems())
                .build();
    }

    // 카트 물품 삭제
    @Transactional
    public CartItemDeleteResponseDto delete(String accountEmail, Long productId){
        Account account = accountRepository.findByEmail(accountEmail);
        Cart cart = account.getCart();
        
        // 삭제해야할 장바구니 상품 가져오기
        CartItem cartItem = cartItemRepository.findByCartIdAndProductId(cart.getId(), productId);

        cartItemRepository.delete(cartItem);

        return CartItemDeleteResponseDto.builder()
                .success(true)
                .message("상품이 장바구니에서 삭제되었습니다.")
                .build();
    }
}
