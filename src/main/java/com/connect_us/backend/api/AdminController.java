package com.connect_us.backend.api;

import com.connect_us.backend.domain.account.Account;
import com.connect_us.backend.domain.enums.Role;
import com.connect_us.backend.domain.enums.Status;
import com.connect_us.backend.service.account.impl.AccountServiceImp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("v1/admin")
@CrossOrigin
public class AdminController {
    private final AccountServiceImp accountServiceImp;

    /** 회원 조회 **/
    @GetMapping("users")
    public UserListResponse userList(){
        List<Account> accounts = accountServiceImp.findAll();
        List<Account> results = new ArrayList<>();
        for (Account account : accounts){
            if(account.getStatus().equals(Status.NORMAL)){
                results.add(account);
            }
        }
        return new UserListResponse( results,true, "사용자 불러오기 성공");
    }

    @Data
    @AllArgsConstructor
    static class UserListResponse{
        List<Account> accounts;
        private boolean success;
        private String message;
    }

    /** 회원 권한 변경 **/

    @PostMapping("users/{id}")
    public UserRoleChangeResponse userRoleChange(@PathVariable Long id, @RequestBody UserRoleChangeRequest userRoleChangeRequest){
        Role before = accountServiceImp.findOne(id).getRole();
        Role after =Role.NOT_PERMITTED;
        System.out.println(userRoleChangeRequest.getRole());
        if(userRoleChangeRequest.getRole().equals("USER")){
            after =Role.USER;
        }
        else if(userRoleChangeRequest.getRole().equals("SELLER")){
            after =Role.SELLER;
        }
        else if(userRoleChangeRequest.getRole().equals("ADMIN")){
            after =Role.ADMIN;
        }
        String email = accountServiceImp.findOne(id).getEmail();
        accountServiceImp.changeRole(email,after);
        return new UserRoleChangeResponse(true,"회원 권한 변경", before, after);
    }

    @Data
    static class UserRoleChangeRequest{
        private String role;
    }

    @Data
    @AllArgsConstructor
    static class UserRoleChangeResponse{
        private boolean success;
        private String message;
        private Role before;
        private Role after;
    }

    /** 회원 삭제 **/

    @PostMapping("users/delete/{id}")
    public UserDeleteResponse userDelete(@PathVariable Long id){
        accountServiceImp.deleteUser(id);
        return new UserDeleteResponse(id, true, "사용자 삭제완료");
    }

    @Data
    @AllArgsConstructor
    static class UserDeleteResponse{
        private Long id;
        private boolean success;
        private String message;
    }

}
