package com.codewithhrishi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithhrishi.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
