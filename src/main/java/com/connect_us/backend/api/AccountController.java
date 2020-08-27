package com.connect_us.backend.api;

import com.connect_us.backend.domain.enums.Gender;
import com.connect_us.backend.security.dto.AccountDto;
import com.connect_us.backend.service.account.impl.AccountServiceImp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AccountController {
    private final AccountServiceImp accountServiceImp;

    /**회원 가입**/
    @PostMapping("v1/auth/users")
    public CreateUserResponse saveUserV1 (@RequestBody @Valid CreateUserRequest request){
        AccountDto accountDto = new AccountDto();
        accountDto.setEmail(request.getEmail());
        accountDto.setName(request.getName());
        accountDto.setPassword(request.getPassword());
        accountDto.setGender(request.getGender());
        Long id = accountServiceImp.save(accountDto);
        return new CreateUserResponse(id);
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

    }

    /**일반 로그인 - formLogin 사용할 수 없음**/


    /**정보 수정**/
}
