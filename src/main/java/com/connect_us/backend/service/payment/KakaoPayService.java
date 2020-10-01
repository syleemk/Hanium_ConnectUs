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
        params.add("cid",KakaoPayProperties.TEST_CID);
        params.add("partner_order_id", "1");
        params.add("partner_user_id","1");
        params.add("item_name","초코파이");
        params.add("quantity", "1");
        params.add("total_amount","2100");
        params.add("tax_free_amount","0");
        params.add("approval_url","");
        params.add("cancel_url","");
        params.add("fail_url","");

        HttpEntity<?> request = new HttpEntity<MultiValueMap<String,String>>(params, headers);

        try {
            kakaoPayReadyVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), request, KakaoPayReadyVO.class);

            log.info("" + kakaoPayReadyVO);

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
        params.add("tid", kakaoPayReadyVO.getTid());
        params.add("partner_order_id", "1");
        params.add("partner_user_id", "2");
        params.add("pg_token",pg_token);
        params.add("total_amount", "2100");

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
