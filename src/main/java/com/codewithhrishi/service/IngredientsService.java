package com.codewithhrishi.service;

import java.util.List;

import com.codewithhrishi.entity.IngredientsCategory;
import com.codewithhrishi.entity.IngredientsItems;
import com.codewithhrishi.exception.RestaurantException;


public interface IngredientsService {
	
	public IngredientsCategory createIngredientsCategory(
			String name,Long restaurantId) throws RestaurantException;

	public IngredientsCategory findIngredientsCategoryById(Long id) throws Exception;

	public List<IngredientsCategory> findIngredientsCategoryByRestaurantId(Long id) throws Exception;
	
	public List<IngredientsItems> findRestaurantsIngredients(
			Long restaurantId);

	
	public IngredientsItems createIngredientsItem(Long restaurantId, 
			String ingredientName,Long ingredientCategoryId) throws Exception;

	public IngredientsItems updateStoke(Long id) throws Exception;
	
}
