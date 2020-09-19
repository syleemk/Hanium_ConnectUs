package com.connect_us.backend.domain.fund;

import com.connect_us.backend.domain.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface FundingCartRepository extends JpaRepository<FundingCart,Long> {

    /**
     * https://jogeum.net/9
     * 사용자 email로 펀딩카트 찾기
     * @param accountEmail
     * @return
     */
    FundingCart findByAccount_Email(String accountEmail);
}
