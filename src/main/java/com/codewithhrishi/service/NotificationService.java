package com.codewithhrishi.service;

import java.util.List;

import com.codewithhrishi.entity.Notification;
import com.codewithhrishi.entity.Order;
import com.codewithhrishi.entity.Restaurant;
import com.codewithhrishi.entity.UserData;

public interface NotificationService {
	
	public Notification sendOrderStatusNotification(Order order);
	public void sendRestaurantNotification(Restaurant restaurant, String message);
	public void sendPromotionalNotification(UserData user, String message);
	
	public List<Notification> findUsersNotification(Long userId);

}
