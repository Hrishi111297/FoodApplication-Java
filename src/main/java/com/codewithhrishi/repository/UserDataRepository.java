package com.codewithhrishi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithhrishi.entity.UserData;

public interface UserDataRepository extends JpaRepository<UserData, Long> {
public Optional<UserData>	 findByEmail(String email);
public boolean existsByEmail(String email);
}
