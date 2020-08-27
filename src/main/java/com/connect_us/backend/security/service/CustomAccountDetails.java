package com.connect_us.backend.security.service;

import com.connect_us.backend.domain.account.Account;
import com.connect_us.backend.domain.account.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service("userDetailsService")
public class CustomAccountDetails implements UserDetailsService {
    /** UserDetails 상속, db에서 유저정보 가져와서 담음 **/
    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       Account account= accountRepository.findByEmail(email);//사용자 존재하는지 검증
       if(account==null){
           if(accountRepository.countByEmail(email)==0){
               throw new UsernameNotFoundException("No user found with this email:" + email );
           }
       }
        List<GrantedAuthority> roles = new ArrayList<>(); //사용자 권한정보 생성
        roles.add(new SimpleGrantedAuthority(account.getRoleKey()));
        System.out.println("User:"+account.getEmail()+" Role: "+ roles.get(0));
        AccountContext accountContext = new AccountContext(account,roles);

        return accountContext;

    }

}
