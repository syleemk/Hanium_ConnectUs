package com.connect_us.backend.web.controller.api.v1.email;

import com.connect_us.backend.web.dto.v1.email.MailDto;
import com.connect_us.backend.domain.enums.Role;
import com.connect_us.backend.service.account.impl.AccountServiceImp;
import com.connect_us.backend.service.mail.MailService;
import com.connect_us.backend.web.dto.v1.email.MailValidationRequestDto;
import com.connect_us.backend.web.dto.v1.email.MailValidationResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/v1/email")
@CrossOrigin
public class EmailController {
    private final MailService mailService;
    private final AccountServiceImp accountServiceImp;

    @GetMapping("userVerification")
    public  MailValidationResponseDto acceptVerification(@RequestParam("id") Long id, @RequestParam("key") String key){
        try {
            String email = accountServiceImp.findOne(id).getEmail();
            accountServiceImp.changeRole(email, Role.USER);
            return new MailValidationResponseDto(true, "USER 권한 부여 성공");
        }catch(NullPointerException exception){
            exception.printStackTrace();
            return new MailValidationResponseDto(false, "존재하지 않는 USER임");
        }
    }


    @PostMapping("password")
    public MailValidationResponseDto sendPasswordEmail(@RequestBody @Valid MailValidationRequestDto request) {
        //내가 보낼 이메일 주소와 현재 로그인한 이메일 주소가 같음을 확인
        String message = null;
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String getEmail = authentication.getName();

            if (getEmail.equals(request.getEmail())) {
                String name = accountServiceImp.findByEmail(request.getEmail()).getName();
                try {
                    MailDto dto = mailService.createPasswordChangeEmail(request.getEmail(), name);
                    mailService.sendEmail(dto);
                    message = "메일 발송 완료";
                    return new MailValidationResponseDto(true,message);
                } catch (MailException mailException) {
                    //log (data binding+ exception stack trace)
                    log.error("request data: {}", request, mailException);
                    message = "메일 발송 실패";
                    return new MailValidationResponseDto(false,message);
                }
            } else {
                message = "현재 로그인한 이미지와 입력 이메일이 일치하지 않습니다.";
                return new MailValidationResponseDto(false,message);
            }
        } catch (NullPointerException nullPointerException) {
            log.error("token doesn't exist: {}", nullPointerException);
            message = "토큰 만료 또는 존재하지 않음";
            return new MailValidationResponseDto(false,message);
        }
    }
}
