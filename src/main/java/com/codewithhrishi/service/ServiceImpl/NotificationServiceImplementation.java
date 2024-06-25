package com.codewithhrishi.service.ServiceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithhrishi.entity.Notification;
import com.codewithhrishi.entity.Order;
import com.codewithhrishi.entity.Restaurant;
import com.codewithhrishi.entity.UserData;
import com.codewithhrishi.repository.NotificationRepository;
import com.codewithhrishi.service.NotificationService;

@Service
public class NotificationServiceImplementation implements NotificationService {

	@Autowired
	private NotificationRepository notificationRepository;
	
	@Override
	public Notification sendOrderStatusNotification(Order order) {
		Notification notification = new Notification();
		notification.setMessage("your order is "+order.getOrderStatus()+ " order id is - "+order.getId());
		notification.setCustomer(order.getCustomer());
		notification.setSentAt(new Date());
		
		return notificationRepository.save(notification);
	}

	@Override
	public void sendRestaurantNotification(Restaurant restaurant, String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendPromotionalNotification(UserData user, String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Notification> findUsersNotification(Long userId) {

		return notificationRepository.findByCustomerId(userId);
	}

}
