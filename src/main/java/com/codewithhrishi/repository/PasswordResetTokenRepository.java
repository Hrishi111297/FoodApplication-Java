package com.codewithhrishi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithhrishi.entity.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Integer> {
	PasswordResetToken findByToken(String token);
}
