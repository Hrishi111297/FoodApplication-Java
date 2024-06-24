package com.codewithhrishi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithhrishi.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
