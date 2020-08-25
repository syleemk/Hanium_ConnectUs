package com.connect_us.backend.domain.order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOrderItemRepository extends JpaRepository<ProductOrderItem, Long> {
}
