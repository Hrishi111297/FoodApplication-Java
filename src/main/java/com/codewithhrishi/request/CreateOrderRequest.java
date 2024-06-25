package com.codewithhrishi.request;

import com.codewithhrishi.entity.Address;

import lombok.Data;

@Data
public class CreateOrderRequest {
 
	private Long restaurantId;
	
	private Address deliveryAddress;
	
    
}
