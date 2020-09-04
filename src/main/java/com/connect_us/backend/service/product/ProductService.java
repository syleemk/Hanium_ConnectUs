package com.connect_us.backend.service.product;

import com.connect_us.backend.domain.account.Account;
import com.connect_us.backend.domain.account.AccountRepository;
import com.connect_us.backend.domain.category.Category;
import com.connect_us.backend.domain.category.CategoryRepository;
import com.connect_us.backend.domain.product.Product;
import com.connect_us.backend.domain.product.ProductRepository;
import com.connect_us.backend.web.dto.v1.product.ProductSaveRequestDto;
import com.connect_us.backend.web.dto.v1.product.ProductSaveResponseDto;
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
    public ProductSaveResponseDto save(ProductSaveRequestDto requestDto) {

        // 폼에서 선택한 카테고리 이름 전송 받음
        Category category = categoryRepository.findByName(requestDto.getCategoryName());
        // user 정보에서 email 가져와서 검색 (현재는 일단 클라이언트에서 전송받는 것으로 구현)
        Account account = accountRepository.findByEmail(requestDto.getAccountEmail());

        Product product = requestDto.toEntity(category, account);

        productRepository.save(product);

        return new ProductSaveResponseDto().builder()
                .message("상품이 등록되었습니다.")
                .success(true)
                .build();
    }
}
