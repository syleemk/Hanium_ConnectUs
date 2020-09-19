package com.connect_us.backend.web.dto.v1.fund.res.product;

import com.connect_us.backend.domain.fund.FundingProduct;
import com.connect_us.backend.web.dto.v1.ResponseDto;
import com.connect_us.backend.web.dto.v1.fund.data.FundingData;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Entity 클래스 (DB Layer) 를 res/req 클래스로 사용 하면 안된다.
 * res/req 용도의 클래스 Dto 클래스 (View Layer)를 사용할 것
 * Dto 클래스 : res / req
 *      responseDto : Getter
 *      requestDto : NoArgsConstructor, Builder
 * */

@Getter
@NoArgsConstructor
public class FundingProductFindResponseDto extends ResponseDto {

    private FundingData fundingData;

    @Builder
    public FundingProductFindResponseDto(Boolean success, String message, FundingProduct entity) {
        super(success,message);
        this.fundingData = new FundingData(entity);
    }
}
