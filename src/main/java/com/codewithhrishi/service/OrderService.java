package com.codewithhrishi.service;

import java.util.List;

import com.codewithhrishi.entity.Order;
import com.codewithhrishi.entity.PaymentResponse;
import com.codewithhrishi.entity.UserData;
import com.codewithhrishi.exception.CartException;
import com.codewithhrishi.exception.OrderException;
import com.codewithhrishi.exception.RestaurantException;
import com.codewithhrishi.exception.UserException;
import com.codewithhrishi.request.CreateOrderRequest;
import com.stripe.exception.StripeException;


public interface OrderService {
	
	 public PaymentResponse createOrder(CreateOrderRequest order, UserData user) throws UserException, RestaurantException, CartException, StripeException;
	 
	 public Order updateOrder(Long orderId, String orderStatus) throws OrderException;
	 
	 public void cancelOrder(Long orderId) throws OrderException;
	 
	 public List<Order> getUserOrders(Long userId) throws OrderException;
	 
	 public List<Order> getOrdersOfRestaurant(Long restaurantId,String orderStatus) throws OrderException, RestaurantException;
	 

}
