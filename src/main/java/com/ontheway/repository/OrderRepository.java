package com.ontheway.repository;

import com.ontheway.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserUserId(Long userId);
    List<Order> findByMerchantMerchantId(Long merchantId);
}
