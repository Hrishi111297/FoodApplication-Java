package com.codewithhrishi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithhrishi.entity.Events;

public interface EventRepository extends JpaRepository<Events, Long>{

	public List<Events> findEventsByRestaurantId(Long id);
}
