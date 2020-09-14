package com.connect_us.backend.web.dto.v1.email;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MailDto {
    private String email;
    private String title;
    private String message;

    @Builder
    public MailDto(String email, String title, String message){
        this.email=email;
        this.title=title;
        this.message=message;
    }
}
