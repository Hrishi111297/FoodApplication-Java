package com.codewithhrishi.service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithhrishi.entity.OrderItem;
import com.codewithhrishi.repository.OrderItemRepository;
import com.codewithhrishi.service.OrderItemService;
@Service
public class OrderItemServiceImplementation implements OrderItemService {
	@Autowired
	 private OrderItemRepository orderItemRepository;

	    @Override
	    public OrderItem createOrderIem(OrderItem orderItem) {
	    	
	    	OrderItem newOrderItem=new OrderItem();
//	    	newOrderItem.setMenuItem(orderItem.getMenuItem());
//	    	newOrderItem.setOrder(orderItem.getOrder());
	    	newOrderItem.setQuantity(orderItem.getQuantity());
	        return orderItemRepository.save(newOrderItem);
	    }
	    





		





}
