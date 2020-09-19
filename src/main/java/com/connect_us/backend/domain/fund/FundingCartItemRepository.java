package com.connect_us.backend.domain.fund;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FundingCartItemRepository extends JpaRepository<FundingCartItem,Long> {

    FundingCartItem findByIdAndFundingCart_Id(Long fundingCartItemId, Long fundingCartId);

    Optional<FundingCartItem> findById(Long id);
}
