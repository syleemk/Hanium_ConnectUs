package com.connect_us.backend.web.dto.v1.fund.data;

import com.connect_us.backend.domain.enums.FundingStatus;
import com.connect_us.backend.domain.enums.Status;
import com.connect_us.backend.domain.fund.FundingProduct;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
public class FundingData {
    private Long id;
    private String category; // 카테고리 이름
    private String account; // 사용자 이름
    private String name;
    private String image;
    private int goalPrice;
    private int currentPrice;
    private String address;
    private String information;
    private LocalDateTime due;
    private FundingStatus fundingStatus;
    private Status Status;

    public FundingData(FundingProduct entity) {
        this.id = entity.getId();
        this.category = entity.getCategory().getName();
        this.account = entity.getAccount().getName();
        this.name = entity.getName();
        this.image = entity.getImage();
        this.goalPrice = entity.getGoalPrice();
        this.currentPrice = entity.getCurrentPrice();
        this.address = entity.getAddress();
        this.information = entity.getInformation();
        this.due = entity.getDue();
        this.fundingStatus = entity.getFundingStatus();
        this.Status = entity.getStatus();
    }
}
