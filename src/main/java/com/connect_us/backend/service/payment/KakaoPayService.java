package com.connect_us.backend.service.payment;

import com.connect_us.backend.security.config.KakaoPayProperties;
import com.connect_us.backend.web.dto.v1.payment.KakaoPayApprovalVO;
import com.connect_us.backend.web.dto.v1.payment.KakaoPayReadyVO;
import lombok.extern.java.Log;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Service
@Log
public class KakaoPayService {
    private static final String HOST = KakaoPayProperties.HOST;

    private KakaoPayReadyVO kakaoPayReadyVO;
    private KakaoPayApprovalVO kakaoPayApprovalVO;

    // 결제 준비
    public String KakaoPayReady() {
        RestTemplate restTemplate = new RestTemplate();

        //서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + KakaoPayProperties.ADMIN_KEY);
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        //서버로 요청할 Body (xxx-form 형식 데이터는 MultiValueMap으로 요청해야함 (body에 쿼리스트링 형식으로 들어감))
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid",KakaoPayProperties.TEST_CID); // 가맹점 코드 (테스트 코드 사용)
        params.add("partner_order_id", "1"); // 가맹점 주문번호 (order_id)
        params.add("partner_user_id","1"); // 가맹점 회원 id (user_id)
        params.add("item_name","초코파이"); // 상품명 (product name + 외 몇개)
        params.add("quantity", "1"); // 상품 수량
        params.add("total_amount","2100"); // 상품 총액
        params.add("tax_free_amount","0"); // 상품 비과세 금액
        params.add("approval_url",""); // 결제 성공시 redirect url
        params.add("cancel_url",""); // 결제 취소시 redirect url
        params.add("fail_url",""); // 결제 실패시 redirect url

        HttpEntity<?> request = new HttpEntity<MultiValueMap<String,String>>(params, headers);

        try {
            kakaoPayReadyVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), request, KakaoPayReadyVO.class);

            log.info("" + kakaoPayReadyVO);

            // 결제 대기 요청시 팝업화면으로 카카오 페이 결제 창이 뜬다
            return kakaoPayReadyVO.getNext_redirect_pc_url();
        } catch (RestClientException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return "/pay";
    }

    // 결제 승인
    public KakaoPayApprovalVO kakaoPayInfo(String pg_token) {
        log.info("KakaoPayInfoDto...........................");
        log.info("--------------------------");

        RestTemplate restTemplate = new RestTemplate();

        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + KakaoPayProperties.ADMIN_KEY);
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);

        // 서버로 요청할 Body
        MultiValueMap<String,String> params = new LinkedMultiValueMap<String,String> ();
        params.add("cid", KakaoPayProperties.TEST_CID);
        params.add("tid", kakaoPayReadyVO.getTid()); // 결제 고유 번호 (결제 준비 api의 응답으로 얻을 수 있음)
        params.add("partner_order_id", "1"); // 가맹점 주문 번호, 결제 준비 api에서 요청한 값과 일치해야함
        params.add("partner_user_id", "2"); // 가맹점 회원 id, 결제 준비 api에서 요청한 값과 일치해야함
        params.add("pg_token",pg_token); // 결제 승인 요청을 인증하는 토큰, 사용자가 결제수단 선택 완료시 approval url로 redirection해줄 때, pg_token을 query string 으로 넘겨줌
        params.add("total_amount", "2100"); // 상품 총액, 결제 준비 api에서 요청한 값과 일치해야함

        HttpEntity<?> request = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        try {
            kakaoPayApprovalVO = restTemplate.postForObject(new URI(HOST + "/vq/payment/approve"), request, KakaoPayApprovalVO.class);
        } catch (RestClientException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return null;
    }
}
