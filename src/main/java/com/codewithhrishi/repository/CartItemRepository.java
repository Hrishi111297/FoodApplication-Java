package com.codewithhrishi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithhrishi.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {


//    CartItem findByFoodIsContaining

}
