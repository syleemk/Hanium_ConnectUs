package com.connect_us.backend.service.fund;

import com.connect_us.backend.service.fund.impl.FundingCartServiceImpl;
import com.connect_us.backend.web.controller.api.v1.fund.FundingCartApiController;
import com.connect_us.backend.web.dto.v1.fund.req.cart.FundingCartItemAddRequestDto;
import com.connect_us.backend.web.dto.v1.fund.req.cart.FundingCartItemUpdateRequestDto;
import com.connect_us.backend.web.dto.v1.fund.res.cart.*;

/**
 * @see FundingCartServiceImpl 에서 구현
 * @see FundingCartApiController 에서 사용
 */
public interface FundingCartService {

    FundingCartCreateResponseDto createFundingCart(String accountEmail);

    FundingCartItemAddResponseDto addFundingCartItem(String accountEmail, FundingCartItemAddRequestDto requestDto);

    FundingCartItemListResponseDto getFundingCartItemList(String accountEmail);

    FundingCartItemUpdateResponseDto updateFundingCartItem(String accountEmail, FundingCartItemUpdateRequestDto requestDto);

    FundingCartItemDeleteResponseDto deleteFundingCartItem(Long id);

    FundingCartItemDeleteResponseDto deleteAllFundingCartItem();
}
