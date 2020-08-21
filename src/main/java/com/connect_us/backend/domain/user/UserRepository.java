package com.connect_us.backend.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    /*
    ** JpaRepository 상속 -> 기본 CRUD 만들 필요 없음 **
    *  save(): 레코드 저장 (insert, update)
    *  findOne(): primary key로 레코드 한건 찾기
    *  findAll(): 전체 레코드 불러오기. 정렬(sort), 페이징(pageable) 가능
    *  count():	레코드 갯수
    *  delete(): 레코드 삭제
    * */
    Optional<User> findByEmail(String email); //이미 가입한 회원인지 확인

}
