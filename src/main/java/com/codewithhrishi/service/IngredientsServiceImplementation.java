package com.codewithhrishi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithhrishi.entity.IngredientsCategory;
import com.codewithhrishi.entity.IngredientsItems;
import com.codewithhrishi.entity.Restaurant;
import com.codewithhrishi.exception.RestaurantException;
import com.codewithhrishi.repository.IngredientsCategoryRepository;
import com.codewithhrishi.repository.IngredientsItemRepository;

@Service
public class IngredientsServiceImplementation implements IngredientsService {

	@Autowired
	private IngredientsCategoryRepository ingredientsCategoryRepo;
	
	@Autowired
	private IngredientsItemRepository ingredientsItemRepository;
	
	
	
	@Autowired
	private RestaurantService restaurantService;
	
	@Override
	public IngredientsCategory createIngredientsCategory(
			String name,Long restaurantId) throws RestaurantException {
		
		IngredientsCategory isExist=ingredientsCategoryRepo
				.findByRestaurantIdAndNameIgnoreCase(restaurantId,name);
		
		if(isExist!=null) {
			return isExist;
		}

		Restaurant restaurant=restaurantService.findRestaurantById(restaurantId);
		
		IngredientsCategory ingredientCategory=new IngredientsCategory();
		ingredientCategory.setRestaurant(restaurant);
		ingredientCategory.setName(name);
		
		IngredientsCategory createdCategory = ingredientsCategoryRepo.save(ingredientCategory);
		
		return createdCategory;
	}

	@Override
	public IngredientsCategory findIngredientsCategoryById(Long id) throws Exception {
		Optional<IngredientsCategory> opt=ingredientsCategoryRepo.findById(id);
		if(opt.isEmpty()){
			throw new Exception("ingredient category not found");
		}
		return opt.get();
	}

	@Override
	public List<IngredientsCategory> findIngredientsCategoryByRestaurantId(Long id) throws Exception {
		return ingredientsCategoryRepo.findByRestaurantId(id);
	}

	@Override
	public List<IngredientsItems> findRestaurantsIngredients(Long restaurantId) {

		return ingredientsItemRepository.findByRestaurantId(restaurantId);
	}
	

	@Override
	public IngredientsItems createIngredientsItem(Long restaurantId, 
			String ingredientName, Long ingredientCategoryId) throws Exception {
		
		IngredientsCategory category = findIngredientsCategoryById(ingredientCategoryId);
		
		IngredientsItems isExist = ingredientsItemRepository.
				findByRestaurantIdAndNameIngoreCase(restaurantId, ingredientName,category.getName());
		if(isExist!=null) {
			System.out.println("is exists-------- item");
			return isExist;
		}
		
		Restaurant restaurant=restaurantService.findRestaurantById(
				restaurantId);
		IngredientsItems item=new IngredientsItems();
		item.setName(ingredientName);
		item.setRestaurant(restaurant);
		item.setCategory(category);
		
		IngredientsItems savedIngredients = ingredientsItemRepository.save(item);
		category.getIngredientsItems().add(savedIngredients);

		return savedIngredients;
	}


	@Override
	public IngredientsItems updateStoke(Long id) throws Exception {
		Optional<IngredientsItems> item=ingredientsItemRepository.findById(id);
		if(item.isEmpty()) {
			throw new Exception("ingredient not found with id "+item);
		}
		IngredientsItems ingredient=item.get();
		ingredient.setInStock(!ingredient.isInStock());
		return ingredientsItemRepository.save(ingredient);
	}

	

	

}
