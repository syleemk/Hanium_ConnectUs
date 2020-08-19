package com.connect_us.backend.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Long> {
    //Optional<Users> findByEmail(String email); //이미 가입한 회원인지 확인
}
