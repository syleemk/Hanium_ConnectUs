package com.connect_us.backend.service.fund.impl;

import com.connect_us.backend.domain.account.Account;
import com.connect_us.backend.domain.account.AccountRepository;
import com.connect_us.backend.domain.category.Category;
import com.connect_us.backend.domain.category.CategoryRepository;
import com.connect_us.backend.domain.fund.FundingProduct;
import com.connect_us.backend.domain.fund.FundingProductRepository;
import com.connect_us.backend.service.fund.FundingProductService;
import com.connect_us.backend.web.dto.v1.fund.req.product.*;
import com.connect_us.backend.web.dto.v1.fund.res.product.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class FundingProductServiceImpl implements FundingProductService {

    private final FundingProductRepository fundingProductRepository;
    private final CategoryRepository categoryRepository;
    private final AccountRepository accountRepository;
    /**
     * interface가 아닌 class속에 @Transactional 어노테이션 선언하는게 바람직하다.
     * save : 펀드생성
     * */
    @Transactional
    @Override
    public FundingProductSaveResponseDto save(String accountEmail,FundingProductSaveRequestDto requestDto) {

        // 폼에서 선택한 카테고리 이름 전송 받음
        Category category = categoryRepository.findByName(requestDto.getCategoryName());
        // user 정보에서 email 가져와서 검색 (현재는 일단 클라이언트에서 전송받는 것으로 구현)
        Account account = accountRepository.findByEmail(accountEmail);

        Long id = fundingProductRepository.save(requestDto.toEntity(category, account)).getId();

        return FundingProductSaveResponseDto.builder()
                .success(true)
                .message("펀드 상품 생성 성공 id="+id)
                .build();
    }

    /**
     * 펀드 정보 수정
     * */
    @Transactional
    @Override
    public FundingProductUpdateResponseDto update(Long id, FundingProductUpdateRequestDto requestDto) {
        FundingProduct fundingProduct = fundingProductRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 펀드가 없습니다. id="+id));

        fundingProduct.update(
                requestDto.getName(),
                requestDto.getImage(),
                requestDto.getGoalPrice(),
                requestDto.getAddress(),
                requestDto.getInformation(),
                requestDto.getDue());

        return FundingProductUpdateResponseDto.builder()
                .success(true)
                .message("펀드 상품 정보 업데이트 성공 id="+id)
                .entity(fundingProduct)
                .build();
    }

    /**
     * 펀드 삭제 (soft delete)
     *
     **/
    @Transactional
    @Override
    public FundingProductDeleteResponseDto delete(Long id) {

        FundingProduct fundingProduct = fundingProductRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("해당 펀드가 없습니다. id="+id));

        /*
        fundingProductRepository.delete(fundingProduct); // 완전 삭제
        */

        fundingProduct.softDelete(); // 소프트 딜리트

        return FundingProductDeleteResponseDto.builder()
                .success(true)
                .message("펀드 상품 소프트 딜리트 완료 getStatus(id="+id+") : "+fundingProduct.getStatus())
                .build();
    }

    /**
     * 펀드 찾기
     * */
    @Transactional(readOnly = true)
    @Override
    public FundingProductFindResponseDto findById(Long id) {
        FundingProduct entity = fundingProductRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("해당 펀드가 없습니다. id="+id));


        return FundingProductFindResponseDto.builder()
                .success(true)
                .message("펀딩 검색 성공")
                .entity(entity)
                .build();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<FundingProduct> findByNameContaining(String name, Pageable pageable) {
        return fundingProductRepository.findByNameContaining(name,pageable);
    }


    @Transactional(readOnly = true)
    @Override
    public Page<FundingProduct> findAll(Pageable pageable) {
        return fundingProductRepository.findAll(pageable);
    }

}
