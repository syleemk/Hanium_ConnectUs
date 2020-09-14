package com.connect_us.backend.web.controller.api.v1.admin;

import com.connect_us.backend.domain.account.Account;
import com.connect_us.backend.domain.enums.Role;
import com.connect_us.backend.domain.enums.Status;
import com.connect_us.backend.service.account.impl.AccountServiceImp;
import com.connect_us.backend.web.dto.v1.admin.RoleChangeRequestDto;
import com.connect_us.backend.web.dto.v1.admin.RoleChangeResponseDto;
import com.connect_us.backend.web.dto.v1.admin.UserDeleteResponseDto;
import com.connect_us.backend.web.dto.v1.admin.UserListResponseDto;
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
@RequestMapping("api/v1/admin")
@CrossOrigin
public class AdminController {
    private final AccountServiceImp accountServiceImp;

    /** 회원 조회 **/
    @GetMapping("users")
    public UserListResponseDto userList(){
        List<Account> accounts = accountServiceImp.findAll();
        List<Account> results = new ArrayList<>();
        for (Account account : accounts){
            if(account.getStatus().equals(Status.NORMAL)){
                results.add(account);
            }
        }
        return new UserListResponseDto( true, "사용자 불러오기 성공",results);
    }



    /** 회원 권한 변경 **/

    @PostMapping("users/{id}")
    public RoleChangeResponseDto userRoleChange(@PathVariable Long id, @RequestBody RoleChangeRequestDto roleChangeRequestDto){
        Role before = accountServiceImp.findOne(id).getRole();
        Role after =Role.NOT_PERMITTED;
        System.out.println(roleChangeRequestDto.getRole());
        if(roleChangeRequestDto.getRole().equals("USER")){
            after =Role.USER;
        }
        else if(roleChangeRequestDto.getRole().equals("SELLER")){
            after =Role.SELLER;
        }
        else if(roleChangeRequestDto.getRole().equals("ADMIN")){
            after =Role.ADMIN;
        }
        String email = accountServiceImp.findOne(id).getEmail();
        accountServiceImp.changeRole(email,after);
        return new RoleChangeResponseDto(true,"회원 권한 변경", before, after);
    }


    /** 회원 삭제 **/

    @PostMapping("users/delete/{id}")
    public UserDeleteResponseDto userDelete(@PathVariable Long id){
        accountServiceImp.deleteUser(id);
        return new UserDeleteResponseDto( true, "사용자 삭제완료",id);
    }


}
