package com.connect_us.backend.web.controller.api.v1;

import com.connect_us.backend.domain.account.Account;
import com.connect_us.backend.domain.enums.Gender;
import com.connect_us.backend.service.account.impl.AccountServiceImp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("v1/users")
@CrossOrigin
public class AccountController {
    private final AccountServiceImp accountServiceImp;

    @GetMapping("me") //유저 한명 프로필 조회
    public CreateProfileResponse getProfile(){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String email = authentication.getPrincipal().toString();
            Account account = accountServiceImp.findByEmail(email);
            return new CreateProfileResponse(account, true, "프로필 조회");
        }catch(NullPointerException exception){
            return new CreateProfileResponse(null,false,"토큰 만료 또는 존재하지 않음");
        }
    }

    @Data @AllArgsConstructor
    static class CreateProfileResponse{
        private Account account;
        private boolean success;
        private String message;
    }

    @PutMapping("me")
    public EditUserResponse editProfile(
            @RequestBody EditUserRequest request){
        accountServiceImp.update(request.getName(), request.getAddr(),
                                request.getPhone(),request.getGender());

        return new EditUserResponse();
    }

    @Data
    static class EditUserRequest{
        private String name;
        private String addr;
        private String phone;
        private Gender gender;
    }

    @Data
    static class EditUserResponse{
        private boolean success=true;
        private String message="수정 완료";
    }

    @PutMapping("me/password") //비밀번호 수정
    public EditUserResponse editPassword(
            @RequestBody EditPasswordRequest request){
        accountServiceImp.updatePassword(request.password);
        return new EditUserResponse();
    }

    @Data
    static class EditPasswordRequest{
        private String password;
    }
    @Data
    static class EditPasswordResponse{
        private boolean success=true;
        private String message="비밀번호 수정 완료";
    }



}
