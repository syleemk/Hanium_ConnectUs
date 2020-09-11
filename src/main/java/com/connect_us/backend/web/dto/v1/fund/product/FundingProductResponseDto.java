package com.connect_us.backend.web.dto.v1.fund.product;

import com.connect_us.backend.domain.account.Account;
import com.connect_us.backend.domain.category.Category;
import com.connect_us.backend.domain.enums.FundingStatus;
import com.connect_us.backend.domain.fund.FundingProduct;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * Entity 클래스 (DB Layer) 를 res/req 클래스로 사용 하면 안된다.
 * res/req 용도의 클래스 Dto 클래스 (View Layer)를 사용할 것
 * Dto 클래스 : res / req
 *      responseDto : Getter
 *      requestDto : NoArgsConstructor, Builder
 * */

@Getter
public class FundingProductResponseDto {

    private Long id;
    private Category category;
    private Account account;
    private String name;
    private String image;
    private int goalPrice;
    private int currentPrice;
    private String address;
    private String information;
    private LocalDateTime due;
    private FundingStatus fundingStatus;

    public FundingProductResponseDto(FundingProduct entity) {
        this.id = entity.getId();
        this.category = entity.getCategory();
        this.account = entity.getAccount();
        this.name = entity.getName();
        this.image = entity.getImage();
        this.goalPrice = entity.getGoalPrice();
        this.currentPrice = entity.getCurrentPrice();
        this.address = entity.getAddress();
        this.information = entity.getInformation();
        this.due = entity.getDue();
        this.fundingStatus = entity.getFundingStatus();
    }
}
