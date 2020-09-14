package com.connect_us.backend.web.dto.v1.auth;

import com.connect_us.backend.web.dto.v1.ResponseDto;
import javafx.beans.binding.BooleanExpression;
import lombok.AllArgsConstructor;
import lombok.Builder;

public class CreateUserResponseDto extends ResponseDto {
    private Data data;

    class Data{
        private Long id;
        public Data(Long id) {
            this.id = id;
        }
    }

    public CreateUserResponseDto(Boolean success, String message, Long id){
        super(success,message);
        this.data = new Data(id);
    }
}
