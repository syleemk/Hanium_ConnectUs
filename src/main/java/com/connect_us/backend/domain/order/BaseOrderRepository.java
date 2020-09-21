package com.connect_us.backend.domain.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BaseOrderRepository extends JpaRepository<BaseOrder,Long> {
    @Query("SELECT o FROM BaseOrder o WHERE o.account.id = :aid")
    Page<BaseOrder> findByAccountId(Pageable pageable, @Param("aid") Long aid);
}
