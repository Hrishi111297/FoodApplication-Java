package com.codewithhrishi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithhrishi.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
