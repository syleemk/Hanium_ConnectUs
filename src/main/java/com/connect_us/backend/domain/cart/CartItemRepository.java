package com.connect_us.backend.domain.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    @Query("SELECT ci FROM CartItem ci WHERE ci.cart.id = :cid and ci.product.id = :pid")
    public CartItem findByCartIdAndProductId(@Param("cid") Long cid,@Param("pid")Long pid);
}
