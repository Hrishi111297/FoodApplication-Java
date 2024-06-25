package com.codewithhrishi.service;

import com.codewithhrishi.entity.Order;
import com.codewithhrishi.entity.PaymentResponse;
import com.stripe.exception.StripeException;

public interface PaymentService {
	
	public PaymentResponse generatePaymentLink(Order order) throws StripeException;

}
