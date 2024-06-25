package com.codewithhrishi.service;

import com.codewithhrishi.entity.Cart;
import com.codewithhrishi.entity.CartItem;
import com.codewithhrishi.exception.CartException;
import com.codewithhrishi.exception.CartItemException;
import com.codewithhrishi.exception.FoodException;
import com.codewithhrishi.exception.UserException;
import com.codewithhrishi.request.AddCartItemRequest;


public interface CartSerive {

	public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws UserException, FoodException, CartException, CartItemException;

	public CartItem updateCartItemQuantity(Long cartItemId,int quantity) throws CartItemException;

	public Cart removeItemFromCart(Long cartItemId, String jwt) throws UserException, CartException, CartItemException;

	public Long calculateCartTotals(Cart cart) throws UserException;
	
	public Cart findCartById(Long id) throws CartException;
	
	public Cart findCartByUserId(Long userId) throws CartException, UserException;
	
	public Cart clearCart(Long userId) throws CartException, UserException;
	

	

}
