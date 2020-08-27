package com.connect_us.backend.domain.account;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    //JpaRepository 상속 -> 기본 CRUD 만들 필요 없음
    Optional<Account> findByEmail(String email); //이미 가입한 회원인지 확인
}
