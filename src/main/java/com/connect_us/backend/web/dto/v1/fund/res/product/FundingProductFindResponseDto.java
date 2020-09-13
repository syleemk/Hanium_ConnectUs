package com.connect_us.backend.web.dto.v1.fund.res.product;

import com.connect_us.backend.domain.enums.FundingStatus;
import com.connect_us.backend.domain.enums.Status;
import com.connect_us.backend.domain.fund.FundingProduct;
import com.connect_us.backend.web.dto.v1.ResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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

    private Entity entity;

    @Getter
    @NoArgsConstructor
    static class Entity{
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

        public Entity(FundingProduct entity) {
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


    @Builder
    public FundingProductFindResponseDto(boolean success, String message, FundingProduct entity) {
        super(success,message);
        this.entity = new Entity(entity);
    }
}
