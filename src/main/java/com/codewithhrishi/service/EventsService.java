package com.codewithhrishi.service;

import java.util.List;

import com.codewithhrishi.entity.Events;
import com.codewithhrishi.exception.RestaurantException;

public interface EventsService {
	
	public Events createEvent(Events event,Long restaurantId) throws RestaurantException;
	
	public List<Events> findAllEvent();
	
	public List<Events> findRestaurantsEvent(Long id);
	
	public void deleteEvent(Long id) throws Exception;
	
	public Events findById(Long id) throws Exception;

}
