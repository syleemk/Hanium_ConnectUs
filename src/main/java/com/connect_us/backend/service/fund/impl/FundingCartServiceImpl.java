package com.connect_us.backend.service.fund.impl;

import com.connect_us.backend.domain.account.Account;
import com.connect_us.backend.domain.account.AccountRepository;
import com.connect_us.backend.domain.fund.*;
import com.connect_us.backend.service.fund.FundingCartService;
import com.connect_us.backend.web.dto.v1.ResponseDto;
import com.connect_us.backend.web.dto.v1.fund.req.cart.FundingCartItemAddRequestDto;
import com.connect_us.backend.web.dto.v1.fund.req.cart.FundingCartItemUpdateRequestDto;
import com.connect_us.backend.web.dto.v1.fund.res.cart.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class FundingCartServiceImpl implements FundingCartService {

    /**
     * Repository 변수에 private final 선언 안 해주면 null로 인식 WTF
     */
    private final FundingCartRepository fundingCartRepository;
    private final FundingCartItemRepository fundingCartItemRepository;
    private final FundingProductRepository fundingProductRepository;
    private final AccountRepository accountRepository;

    /**
     * 펀딩 장바구니 생성
     * @param accountEmail
     */
    @Transactional
    @Override
    public FundingCartCreateResponseDto createFundingCart(String accountEmail) {

        Account account = accountRepository.findByEmail(accountEmail);

        log.error("createFundingCart getFundingCart :"+account.getFundingCart());

        ResponseDto responseDto = new ResponseDto(false,"펀딩 장바구니가 이미 생성되었습니다");

        if (account.getFundingCart() == null){
            fundingCartRepository.save(FundingCart.builder()
                    .account(account)
                    .build());

            responseDto = new ResponseDto(true,"펀딩 장바구니가 생성되었습니다");
        }

        return FundingCartCreateResponseDto.builder()
                .responseDto(responseDto)
                .build();
    }

    /**
     * 펀딩 장바구니 아이템 추가
     * @param accountEmail
     * @param requestDto
     * @return
     */
    @Transactional
    @Override
    public FundingCartItemAddResponseDto addFundingCartItem(String accountEmail, FundingCartItemAddRequestDto requestDto) {
        Account account = accountRepository.findByEmail(accountEmail);

        FundingCart fundingCart = account.getFundingCart();

        FundingProduct fundingProduct = fundingProductRepository.findById(requestDto.getFundingProductId())
                .orElseThrow(() -> new IllegalArgumentException("펀드 상품이 존재하지 않습니다. id="+requestDto.getFundingProductId()));

        ResponseDto responseDto = new ResponseDto(false,"이미 존재하는 상품입니다.");

        if (!fundingCart.getFundingProductIds().contains(requestDto.getFundingProductId())) {
            FundingCartItem fundingCartItem = FundingCartItem.builder()
                    .fundingCart(fundingCart)
                    .fundingProduct(fundingProduct)
                    .fundingInvestment(requestDto.getFundingInvestment())
                    .build();

            fundingCartItemRepository.save(fundingCartItem);

            responseDto = new ResponseDto(true,"펀딩 장바구니에 상품이 추가되었습니다");
        }


        return FundingCartItemAddResponseDto.builder()
                .responseDto(responseDto)
                .build();
    }

    /**
     * 펀딩 장바구니 리스트 불러오기
     * @param accountEmail
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public FundingCartItemListResponseDto getFundingCartItemList(String accountEmail) {
        Account account = accountRepository.findByEmail(accountEmail);

        FundingCart fundingCart = fundingCartRepository.getOne(account.getFundingCart().getId());

        fundingCart.getFundingCartItems().forEach(e -> System.out.println(e.getFundingProduct().getName()));

        List<FundingCartItem> list = fundingCart.getFundingCartItems();

        return FundingCartItemListResponseDto.builder()
                .responseDto(new ResponseDto(true,"펀딩 장바구니 아이템 리스트 조회 성공"))
                .fundingCartItemDataList(list)
                .build();
    }

    /**
     * 펀딩 장바구니 아이템의 투자금 정보 업데이트
     * @param accountEmail
     * @param requestDto
     * @return
     */
    @Transactional
    @Override
    public FundingCartItemUpdateResponseDto updateFundingCartItem(String accountEmail, FundingCartItemUpdateRequestDto requestDto) {

        Account account = accountRepository.findByEmail(accountEmail);
        FundingCart fundingCart = account.getFundingCart();

        FundingCartItem fundingCartItem = fundingCartItemRepository.findByIdAndFundingCart_Id(requestDto.getFundingCartItemId(),fundingCart.getId());

        ResponseDto responseDto = new ResponseDto(false,"펀딩 장바구니에 해당 아이템이 없습니다 itemId="+requestDto.getFundingCartItemId());

        if (fundingCartItem != null){
            fundingCartItem.update(requestDto.getFundingInvestment());
            responseDto = new ResponseDto(true,"펀딩 장바구니 아이템 업데이트 성공");
        }


        return FundingCartItemUpdateResponseDto.builder()
                .responseDto(responseDto)
                .build();
    }

    /**
     * 펀딩 장바구니 아이템 삭제
     * @param id
     * @return
     */
    @Transactional
    @Override
    public FundingCartItemDeleteResponseDto deleteFundingCartItem(Long id) {

        Optional<FundingCartItem> fundingCartItem = fundingCartItemRepository.findById(id);

        ResponseDto responseDto = new ResponseDto(false,"펀딩 장바구니에 해당 아이템이 존재하지 않습니다 itemId="+id);

        if (fundingCartItem.isPresent()){
            fundingCartItemRepository.delete(fundingCartItem.get());
            responseDto = new ResponseDto(true,"펀딩 장바구니 아이템 삭제 성공");
        }

        return FundingCartItemDeleteResponseDto.builder()
                .responseDto(responseDto)
                .build();
    }


    /**
     * 펀딩 장바구니 속 아이템 모두 삭제
     * @return
     */
    @Transactional
    @Override
    public FundingCartItemDeleteResponseDto deleteAllFundingCartItem() {

        fundingCartItemRepository.deleteAll();

        return FundingCartItemDeleteResponseDto.builder()
                .responseDto(new ResponseDto(true,"펀딩 장바구니 아이템 모두 삭제 성공"))
                .build();
    }
}
