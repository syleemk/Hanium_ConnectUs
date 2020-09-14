
package com.connect_us.backend;

import com.connect_us.backend.domain.account.Account;
import com.connect_us.backend.domain.account.AccountRepository;
import com.connect_us.backend.domain.enums.Gender;
import com.connect_us.backend.domain.enums.Role;
import com.connect_us.backend.domain.enums.Social;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DBInit implements CommandLineRunner {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        //initialize
        this.accountRepository.deleteAll();

        //user, admin+password encode
        Account user = new Account("user", passwordEncoder.encode("1234"),"user",Social.FALSE, Gender.MALE, Role.USER);
        Account seller = new Account("seller", passwordEncoder.encode("1234"),"seller",Social.FALSE, Gender.MALE, Role.SELLER);
        Account admin = new Account("admin", passwordEncoder.encode("1234"),"admin",Social.FALSE,Gender.MALE, Role.ADMIN);
        Account test = new Account("rain7i@naver.com", passwordEncoder.encode("1234"),"test",Social.FALSE,Gender.MALE, Role.USER);

        List<Account> accounts = Arrays.asList(user,seller,admin,test);
        this.accountRepository.saveAll(accounts);


    }
}

