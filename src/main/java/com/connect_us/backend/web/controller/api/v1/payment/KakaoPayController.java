package com.connect_us.backend.web.controller.api.v1.payment;

import com.connect_us.backend.service.payment.KakaoPayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Log
@RequiredArgsConstructor
@Controller
public class KakaoPayController {

    private final KakaoPayService kakaoPayService;

    @GetMapping("/kakaoPay")
    public void kakaoPayGet() {

    }

    @PostMapping("/kakaoPay")
    public String kakaoPay() {
        log.info("kakaoPay Post.....................");

        return "redirect:" + kakaoPayService.KakaoPayReady();
    }

    @GetMapping("/kakaoPaySuccess")
    public void kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model){
        log.info("kakaoPaySucces get................................");
        log.info("kakaoPaySuccess pg_token : " +pg_token);

        model.addAttribute("info", kakaoPayService.kakaoPayInfo(pg_token));
    }
}
