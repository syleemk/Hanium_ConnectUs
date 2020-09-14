package com.connect_us.backend.web.controller.api.v1.auth;

import com.connect_us.backend.domain.account.Account;
import com.connect_us.backend.web.dto.v1.email.MailDto;
import com.connect_us.backend.domain.enums.Role;
import com.connect_us.backend.domain.enums.Social;
import com.connect_us.backend.web.dto.v1.auth.AccountDto;
import com.connect_us.backend.service.account.impl.AccountServiceImp;
import com.connect_us.backend.service.mail.MailService;
import com.connect_us.backend.web.dto.v1.auth.CreateUserRequestDto;
import com.connect_us.backend.web.dto.v1.auth.CreateUserResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/v1/auth")
@CrossOrigin
public class AuthController {
    private final AccountServiceImp accountServiceImp;
    private final MailService mailService;

    /**회원 가입**/
    @PostMapping("users")
    public CreateUserResponseDto saveUser (@RequestBody @Valid CreateUserRequestDto request){
        AccountDto accountDto = new AccountDto(request.getEmail(),request.getPassword(),request.getName(),
                Social.FALSE,request.getGender(),Role.NOT_PERMITTED);
        Long id = accountServiceImp.save(accountDto);
        //이메일 인증 해야함
        Account newAccount = accountServiceImp.findOne(id);
        System.out.println("신규가입"+newAccount.getEmail() );
        MailDto dto = mailService.createValidationEmail(newAccount.getEmail(), id, newAccount.getName());
        mailService.sendEmail(dto);
        return new CreateUserResponseDto(id, true, "회원가입 성공");
    }

    /** 로그아웃
     *  로그아웃 API 호출하면
     *  현재 jwt를  redis에 넣음
     *  로그인 했는지 확인하는 API에서 validaton 할 때 redis에 토큰이 있는지 확인,  redis에 있으면 로그아웃 한 것으로 간주
     * **/



}
