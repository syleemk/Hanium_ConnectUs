package com.connect_us.backend.web.controller.api.v1.account;

import com.connect_us.backend.domain.account.Account;
import com.connect_us.backend.service.account.impl.AccountServiceImp;
import com.connect_us.backend.web.dto.v1.account.EditPasswordRequestDto;
import com.connect_us.backend.web.dto.v1.account.EditUserRequestDto;
import com.connect_us.backend.web.dto.v1.account.EditResponseDto;
import com.connect_us.backend.web.dto.v1.account.ProfileResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/v1/users")
@CrossOrigin
public class AccountController {
    private final AccountServiceImp accountServiceImp;

    @GetMapping("me") //유저 한명 프로필 조회
    public ProfileResponseDto getProfile(){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String email = authentication.getPrincipal().toString();
            Account account = accountServiceImp.findByEmail(email);
            return new ProfileResponseDto(true, "프로필 조회", account);
        }catch(NullPointerException exception){
            return new ProfileResponseDto(false,"토큰 만료, 존재하지 않는 유저",null);
        }
    }

    @PutMapping("me")
    public EditResponseDto editProfile(
            @RequestBody EditUserRequestDto request){
        accountServiceImp.update(request.getName(), request.getAddr(),
                                request.getPhone(),request.getGender());
        return new EditResponseDto(true,"수정 완료");
    }

    @PutMapping("me/password") //비밀번호 수정
    public EditResponseDto editPassword(
            @RequestBody EditPasswordRequestDto request){
        accountServiceImp.updatePassword(request.getPassword());
        return new EditResponseDto(true,"비밀번호 수정 완료");
    }

}
