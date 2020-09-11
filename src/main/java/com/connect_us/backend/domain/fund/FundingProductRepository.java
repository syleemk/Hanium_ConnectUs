package com.connect_us.backend.domain.fund;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FundingProductRepository extends JpaRepository<FundingProduct,Long> {

    @Query("SELECT f FROM FundingProduct f ORDER BY f.id DESC")
    List<FundingProduct> findAllDesc();


}
