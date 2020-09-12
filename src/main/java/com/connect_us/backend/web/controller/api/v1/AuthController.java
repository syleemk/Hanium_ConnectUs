package com.connect_us.backend.web.controller.api.v1;

import com.connect_us.backend.domain.account.Account;
import com.connect_us.backend.domain.account.MailDto;
import com.connect_us.backend.domain.enums.Gender;
import com.connect_us.backend.domain.enums.Role;
import com.connect_us.backend.domain.enums.Social;
import com.connect_us.backend.security.dto.AccountDto;
import com.connect_us.backend.service.account.impl.AccountServiceImp;
import com.connect_us.backend.service.mail.MailService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("v1/auth")
@CrossOrigin
public class AuthController {
    private final AccountServiceImp accountServiceImp;
    private final MailService mailService;

    /**회원 가입**/
    @PostMapping("users")
    public CreateUserResponse saveUser (@RequestBody @Valid CreateUserRequest request){
        AccountDto accountDto = new AccountDto();
        accountDto.setEmail(request.getEmail());
        accountDto.setName(request.getName());
        accountDto.setPassword(request.getPassword());
        accountDto.setGender(request.getGender());
        accountDto.setSocial(Social.FALSE);
        accountDto.setRole(Role.NOT_PERMITTED);
        Long id = accountServiceImp.save(accountDto);

        //이메일 인증 해야함
        Account newAccount = accountServiceImp.findOne(id);
        System.out.println("신규가입"+newAccount.getEmail() );
        MailDto dto = mailService.createValidationEmail(newAccount.getEmail(), id, newAccount.getName());
        mailService.sendEmail(dto);

        return new CreateUserResponse(id, true, "회원가입 성공");
    }

    @Data
    static class CreateUserRequest{
        private String email;
        private String password;
        private String name;
        private Gender gender;
    }

    @Data @AllArgsConstructor
    static class CreateUserResponse{
        private Long id;
        private boolean success;
        private String message;
    }



}
