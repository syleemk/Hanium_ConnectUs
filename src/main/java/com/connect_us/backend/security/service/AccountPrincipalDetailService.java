package com.connect_us.backend.security.service;

import com.connect_us.backend.domain.account.Account;
import com.connect_us.backend.domain.account.AccountRepository;
import com.connect_us.backend.security.dto.AccountPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountPrincipalDetailService implements UserDetailsService {
    /** UserDetails 상속, db에서 유저정보 가져와서 담음 **/
    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       Account account= this.accountRepository.findByEmail(email);//사용자 존재하는지 검증
       if(account==null){
           if(accountRepository.countByEmail(email)==0){
               throw new UsernameNotFoundException("No user found with this email:" + email );
           }
       }
        AccountPrincipal accountPrincipal = new AccountPrincipal(account);
        return accountPrincipal;

    }

}
