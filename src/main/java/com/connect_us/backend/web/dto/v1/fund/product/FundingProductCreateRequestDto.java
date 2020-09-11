package com.connect_us.backend.web.dto.v1.fund.product;

import com.connect_us.backend.domain.enums.FundingStatus;
import com.connect_us.backend.domain.fund.FundingProduct;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class FundingProductCreateRequestDto {

    private String name;
    private String image;
    private int goalPrice;
    private String address;
    private String information;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime due;

    /**
     * Builder로 생성할 경우, name값을 설정하지 않는다면 NULL값이 들어간다.
     * */
    @Builder
    public FundingProductCreateRequestDto(String name, String image, int goalPrice, String address, String information, LocalDateTime due) {
        this.name = name;
        this.image = image;
        this.goalPrice = goalPrice;
        this.address = address;
        this.information = information;
        this.due = due;
    }

    public FundingProduct toEntity() {
        return FundingProduct.builder()
                .name(name)
                .image(image)
                .goalPrice(goalPrice)
                .address(address)
                .information(information)
                .due(due)
                .build();
    }

}
