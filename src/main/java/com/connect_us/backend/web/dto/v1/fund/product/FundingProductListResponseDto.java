package com.connect_us.backend.web.dto.v1.fund.product;

import com.connect_us.backend.domain.account.Account;
import com.connect_us.backend.domain.category.Category;
import com.connect_us.backend.domain.enums.FundingStatus;
import com.connect_us.backend.domain.fund.FundingProduct;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FundingProductListResponseDto {

    private Long id;
    private Category category;
    private Account account;
    private String name;
    private String image;
    private int goalPrice;
    private int currentPrice;
    private String address;
    private LocalDateTime due;
    private FundingStatus fundingStatus;

    public FundingProductListResponseDto(FundingProduct entity) {
        this.id = entity.getId();
        this.category = entity.getCategory();
        this.account = entity.getAccount();
        this.name = entity.getName();
        this.image = entity.getImage();
        this.goalPrice = entity.getGoalPrice();
        this.currentPrice = entity.getCurrentPrice();
        this.address = entity.getAddress();
        this.due = entity.getDue();
        this.fundingStatus = entity.getFundingStatus();
    }
}
