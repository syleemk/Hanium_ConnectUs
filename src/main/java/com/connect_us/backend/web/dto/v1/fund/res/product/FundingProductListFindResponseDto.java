package com.connect_us.backend.web.dto.v1.fund.res.product;

import com.connect_us.backend.domain.enums.FundingStatus;
import com.connect_us.backend.domain.enums.Status;
import com.connect_us.backend.domain.fund.FundingProduct;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FundingProductListFindResponseDto {

    private Long id;
    private String name;
    private String image;
    private int goalPrice;
    private int currentPrice;
    private LocalDateTime due;
    private FundingStatus fundingStatus;
    private Status status;

    /**
     * FundingProductListFindResponseDto 생성자에
     * @param entity 만 존재해야한다.
     * @see com.connect_us.backend.service.fund.impl.FundingProductServiceImpl findAllDesc()함수의
     * map(FundingProductListFindResponseDto::new) 코드떄문
     */
    @Builder
    public FundingProductListFindResponseDto(FundingProduct entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.image = entity.getImage();
        this.goalPrice = entity.getGoalPrice();
        this.currentPrice = entity.getCurrentPrice();
        this.due = entity.getDue();
        this.fundingStatus = entity.getFundingStatus();
        this.status = entity.getStatus();
    }
}
