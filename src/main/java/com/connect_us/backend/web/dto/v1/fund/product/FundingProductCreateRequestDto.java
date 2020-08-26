package com.connect_us.backend.web.dto.v1.fund.product;

import com.connect_us.backend.domain.enums.FundingStatus;
import com.connect_us.backend.domain.fund.FundingProduct;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class FundingProductCreateRequestDto {

    private String name;
    private String image;
    private int goalPrice;
    private String address;
    private LocalDateTime due;
    private FundingStatus fundingStatus;

    @Builder
    public FundingProductCreateRequestDto(String name, String image, int goalPrice, String address, LocalDateTime due, FundingStatus fundingStatus) {
        this.name = name;
        this.image = image;
        this.goalPrice = goalPrice;
        this.address = address;
        this.due = due;
        this.fundingStatus = fundingStatus;
    }

    public FundingProduct toEntity() {
        return FundingProduct.builder()
                .name(name)
                .image(image)
                .goalPrice(goalPrice)
                .address(address)
                .due(due)
                .fundingStatus(fundingStatus)
                .build();
    }

    @Override
    public String toString() {
        return "FundingProductCreateRequestDto{" +
                "name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", goalPrice=" + goalPrice +
                ", address='" + address + '\'' +
                ", due=" + due +
                ", fundingStatus=" + fundingStatus +
                '}';
    }
}
