package com.codewithhrishi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithhrishi.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
