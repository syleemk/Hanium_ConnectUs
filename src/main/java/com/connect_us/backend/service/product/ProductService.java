package com.connect_us.backend.service.product;

import com.connect_us.backend.domain.account.Account;
import com.connect_us.backend.domain.account.AccountRepository;
import com.connect_us.backend.domain.category.Category;
import com.connect_us.backend.domain.category.CategoryRepository;
import com.connect_us.backend.domain.product.Product;
import com.connect_us.backend.domain.product.ProductRepository;
import com.connect_us.backend.web.dto.v1.product.ProductSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final AccountRepository accountRepository;

    @Transactional
    public Long save(ProductSaveRequestDto requestDto) {
        Account account = accountRepository.findById(requestDto.getAccount()).orElseThrow(()-> new NoSuchElementException());
        Category category = categoryRepository.findById(requestDto.getCategory()).orElseThrow(() -> new NoSuchElementException());

        Product product = Product.builder()
                .category(category)
                .account(account)
                .name(requestDto.getName())
                .image(requestDto.getImage())
                .price(requestDto.getPrice())
                .stock(requestDto.getStock())
                .productStatus(requestDto.getProductStatus())
                .build();

        return productRepository.save(product).getId();

    }
}
