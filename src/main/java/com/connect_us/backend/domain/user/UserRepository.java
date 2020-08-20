package com.connect_us.backend.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    //JpaRepository 상속 -> 기본 CRUD 만들 필요 없음
    Optional<User> findByEmail(String email); //이미 가입한 회원인지 확인
}
