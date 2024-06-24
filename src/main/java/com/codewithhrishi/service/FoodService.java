package com.codewithhrishi.service;

import java.util.List;

import com.codewithhrishi.entity.Category;
import com.codewithhrishi.entity.Food;
import com.codewithhrishi.entity.Restaurant;
import com.codewithhrishi.exception.FoodException;
import com.codewithhrishi.exception.RestaurantException;
import com.codewithhrishi.request.CreateFoodRequest;

public interface FoodService {

	public Food createFood(CreateFoodRequest req,Category category,
						   Restaurant restaurant) throws FoodException, RestaurantException;

	void deleteFood(Long foodId) throws FoodException;
	
	public List<Food> getRestaurantsFood(Long restaurantId,
			boolean isVegetarian, boolean isNonveg, boolean isSeasonal,String foodCategory) throws FoodException;
	
	public List<Food> searchFood(String keyword);
	
	public Food findFoodById(Long foodId) throws FoodException;

	public Food updateAvailibilityStatus(Long foodId) throws FoodException;
}
