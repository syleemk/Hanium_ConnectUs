package com.connect_us.backend.web.dto.v1.fund.req.product;

import com.connect_us.backend.domain.enums.FundingStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class FundingProductUpdateRequestDto {

    private String name;
    private String image;
    private int goalPrice;
    private String address;
    private String information;
    private LocalDateTime due;
    private FundingStatus fundingStatus;

    @Builder
    public FundingProductUpdateRequestDto(String name, String image, int goalPrice, String address, String information, LocalDateTime due, FundingStatus fundingStatus) {
        this.name = name;
        this.image = image;
        this.goalPrice = goalPrice;
        this.address = address;
        this.information = information;
        this.due = due;
        this.fundingStatus = fundingStatus;
    }
}
