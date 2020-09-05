package com.connect_us.backend.api;

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
    public Account getProfile(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return accountServiceImp.findByEmail(email);
    }

    @PutMapping("me/{id}")
    public EditUserResponse editProfile(
            @PathVariable Long id,
            @RequestBody EditUserRequest request){
        accountServiceImp.update(request.getName(), request.getAddr(),
                                request.getPhone(),request.getGender());

        Account account = accountServiceImp.findOne(id);
        return new EditUserResponse(account.getId(), account.getName());
    }

    @Data
    static class EditUserRequest{
        private String name;
        private String addr;
        private String phone;
        private Gender gender;
    }
    @Data
    @AllArgsConstructor
    static class EditUserResponse{
        private Long id;
        private String name;
    }
//
//    @PutMapping("me/{id}/password") //비밀번호 수정
//    public EditUserResponse editPassword(
//            @PathVariable("id") Long id,
//            @RequestBody EditPasswordRequest request){
//        accountServiceImp.update(id,request.getName(), request.getAddr(),
//                request.getPhone(),request.getGender());
//        Account account = accountRepository.findOne(id);
//        return new EditUserResponse(account.getId(), account.getName());
//    }

//    @Data
//    static class EditPasswordRequest{
//        private String password;
//    }
//    @Data
//    @AllArgsConstructor
//    static class EditPasswordResponse{
//        private Long id;
//        private String name;
//    }
//
//    @PostMapping("v1/auth/emails/password") //비밀번호 변경 이메일 전송
//    @PostMapping("v1/auth/emails/verification") //인증 이메일 전송
//    @PostMapping("v1/auth/emails/notification") //공지사항 이메일 전송

}
