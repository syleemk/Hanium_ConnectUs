package com.connect_us.backend.web.controller.api.v1.fund;

import com.connect_us.backend.domain.account.Account;
import com.connect_us.backend.service.fund.FundingCartService;
import com.connect_us.backend.web.dto.v1.fund.req.cart.*;
import com.connect_us.backend.web.dto.v1.fund.res.cart.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping(path = "/api/v1/fund/carts")
public class FundingCartApiController {

    private final FundingCartService fundingCartService;

    /** 펀딩 카트 생성*/
    @PostMapping("/create")
    FundingCartCreateResponseDto createFundingCart(Authentication authentication){
        String accountEmail = authentication.getName();

        log.error("accountEmail "+accountEmail);

        return fundingCartService.createFundingCart(accountEmail);
    }

    @PostMapping("/add")
    FundingCartItemAddResponseDto addFundingCartItem(Authentication authentication,
                                                     @RequestBody FundingCartItemAddRequestDto requestDto){
        String accountEmail = authentication.getName();

        return fundingCartService.addFundingCartItem(accountEmail, requestDto);
    }

    @GetMapping
    FundingCartItemListResponseDto getFundingCartItemList(Authentication authentication){
        String accountEmail = authentication.getName();

        return fundingCartService.getFundingCartItemList(accountEmail);
    }

    @PutMapping("/update")
    FundingCartItemUpdateResponseDto updateFundingCartItem(Authentication authentication,
                                                           @RequestBody FundingCartItemUpdateRequestDto requestDto){
        String accountEmail = authentication.getName();

        return fundingCartService.updateFundingCartItem(accountEmail, requestDto);
    }

    @DeleteMapping("/{id}")
    FundingCartItemDeleteResponseDto deleteFundingCartItem(@PathVariable Long id){
        return fundingCartService.deleteFundingCartItem(id);
    }

    @DeleteMapping
    FundingCartItemDeleteResponseDto deleteAllFundingCartItem() {
        return fundingCartService.deleteAllFundingCartItem();
    }

}
