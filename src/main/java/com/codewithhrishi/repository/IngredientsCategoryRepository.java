package com.codewithhrishi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.codewithhrishi.entity.IngredientsCategory;


public interface IngredientsCategoryRepository 
extends JpaRepository<IngredientsCategory, Long>{
	
	
//	List<IngredientCategory> findByFoodId(Long menuItemId);
	List<IngredientsCategory> findByRestaurantId(Long id);

	@Query("SELECT e FROM IngredientCategory e "
			+ "WHERE e.restaurant.id = :restaurantId "
			+ "AND lower(e.name) = lower(:name)")
	IngredientsCategory findByRestaurantIdAndNameIgnoreCase(
			@Param("restaurantId") Long restaurantId, @Param("name") String name);
}
