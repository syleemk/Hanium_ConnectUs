package com.connect_us.backend.web.controller.api.v1.fund.res;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SimpleResponse {
    private Long id;
    private boolean success;
    private String message;
}
