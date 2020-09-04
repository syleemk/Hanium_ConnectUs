package com.connect_us.backend.api;

import com.connect_us.backend.domain.enums.Gender;
import com.connect_us.backend.domain.enums.Role;
import com.connect_us.backend.domain.enums.Social;
import com.connect_us.backend.security.dto.AccountDto;
import com.connect_us.backend.service.account.impl.AccountServiceImp;
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

    /**회원 가입**/
    @PostMapping("users")
    public CreateUserResponse saveUserV1 (@RequestBody @Valid CreateUserRequest request){
        AccountDto accountDto = new AccountDto();
        accountDto.setEmail(request.getEmail());
        accountDto.setName(request.getName());
        accountDto.setPassword(request.getPassword());
        accountDto.setGender(request.getGender());
        accountDto.setSocial(Social.FALSE);
        accountDto.setRole(Role.NOT_PERMITTED);
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



}
