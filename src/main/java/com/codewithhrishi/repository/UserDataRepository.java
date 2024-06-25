package com.codewithhrishi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.codewithhrishi.entity.UserData;

public interface UserDataRepository extends JpaRepository<UserData, Long> {
public Optional<UserData>	 findByEmail(String email);
public boolean existsByEmail(String email);
@Query("SELECT u FROM UserData u Where u.status='PENDING'")
public List<UserData> getPenddingRestaurantOwners();
}
