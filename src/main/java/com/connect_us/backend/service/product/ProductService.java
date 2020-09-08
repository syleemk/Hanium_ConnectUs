package com.connect_us.backend.service.product;

import com.connect_us.backend.domain.account.Account;
import com.connect_us.backend.domain.account.AccountRepository;
import com.connect_us.backend.domain.category.Category;
import com.connect_us.backend.domain.category.CategoryRepository;
import com.connect_us.backend.domain.product.Product;
import com.connect_us.backend.domain.product.ProductRepository;
import com.connect_us.backend.web.dto.v1.ResponseDto;
import com.connect_us.backend.web.dto.v1.product.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public ProductSaveResponseDto save(String accountEmail, ProductSaveRequestDto requestDto) {

        // 폼에서 선택한 카테고리 이름 전송 받음
        Category category = categoryRepository.findByName(requestDto.getCategoryName());
        // user 정보에서 email 가져와서 검색 (현재는 일단 클라이언트에서 전송받는 것으로 구현)
        Account account = accountRepository.findByEmail(accountEmail);

        Product product = requestDto.toEntity(category, account);

        productRepository.save(product);

        return new ProductSaveResponseDto().builder()
                .success(true)
                .message("상품이 등록되었습니다.")
                .build();
    }

    @Transactional
    public ProductUpdateResponseDto update(Long id, ProductUpdateRequestDto requestDto){
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        Category category = product.getCategory();

        if(product.getCategory().getName() != requestDto.getCategoryName()){ // 카테고리 달라진경우
            category = categoryRepository.findByName(requestDto.getCategoryName());
        }

        product.update(category, requestDto.getName(), requestDto.getImage(), requestDto.getPrice(),
                requestDto.getStock(), requestDto.getProductStatus());

        return new ProductUpdateResponseDto().builder()
                .success(true)
                .message("상품 정보가 수정되었습니다.")
                .build();
    }

    @Transactional(readOnly = true)
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public ProductFindResponseDto findById(Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 상품이 없습니다. id="+id));

        return new ProductFindResponseDto().builder()
                .success(true)
                .message("상품 상세 정보 조회 성공")
                .data(product)
                .build();
    }
}
