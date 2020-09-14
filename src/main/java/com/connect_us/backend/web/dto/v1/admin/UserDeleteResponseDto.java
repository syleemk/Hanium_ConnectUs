package com.connect_us.backend.web.dto.v1.admin;

import com.connect_us.backend.web.dto.v1.ResponseDto;

public class UserDeleteResponseDto extends ResponseDto {
    private Data data;
    class Data{
        private Long id;
        public Data(Long id) {
            this.id = id;
        }
    }

    public UserDeleteResponseDto(Boolean success, String message, Long id) {
        super(success, message);
        this.data = new Data(id);
    }
}
