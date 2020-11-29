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
        
        // 결제 준비 요청 성공시 팝업으로 결제창 띄움
        return "redirect:" + kakaoPayService.KakaoPayReady();
    }

    // 결제 요청이 끝나면 결제 승인 api가 호출됨 (결제 준비단계에서 등록한 approval url에 pg_token을 쿼리 문자열로 넣어 보냄)
    @GetMapping("/kakaoPaySuccess")
    public void kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model){
        log.info("kakaoPaySucces get................................");
        log.info("kakaoPaySuccess pg_token : " +pg_token);

        //결제 승인 요청, 요청 결과로 결제 정보를 리턴한다
        kakaoPayService.kakaoPayInfo(pg_token);

        //리턴한 결제 정보 (필요한 정보만) 따로 DB에 저장하는 코드 추가

        // 모델에 넣어 보내지 않고 JSON 형식으로 리턴하도록 수정
        model.addAttribute("info", kakaoPayService.kakaoPayInfo(pg_token));
    }
}
