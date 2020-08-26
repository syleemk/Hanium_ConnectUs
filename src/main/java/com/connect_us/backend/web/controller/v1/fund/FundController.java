package com.connect_us.backend.web.controller.v1.fund;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
/**
 * Request => html 파일을 보내준다
 * */
public class FundController {

    @GetMapping("/v1/fund/product")
    public String index() {
        log.info("fund product homepage");
        return "/v1/fund/product/home"; // /v1/fund/product/home.html
    }

    @GetMapping("/v1/fund/product/create")
    public String createPage() {
        return "/v1/fund/product/create";
    }
}
