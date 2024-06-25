package com.codewithhrishi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codewithhrishi.entity.Cart;
import com.codewithhrishi.entity.CartItem;
import com.codewithhrishi.entity.UserData;
import com.codewithhrishi.exception.CartException;
import com.codewithhrishi.exception.CartItemException;
import com.codewithhrishi.exception.FoodException;
import com.codewithhrishi.exception.UserException;
import com.codewithhrishi.request.AddCartItemRequest;
import com.codewithhrishi.request.UpdateCartItemRequest;
import com.codewithhrishi.service.CartSerive;
import com.codewithhrishi.service.UserService;

@RestController
@RequestMapping("/api")
public class CartController {
	@Autowired
	private CartSerive cartService;
	@Autowired
	private UserService userService;

	@PutMapping("/cart/add")
	public ResponseEntity<CartItem> addItemToCart(@RequestBody AddCartItemRequest req,
			@RequestHeader("Authorization") String jwt) throws UserException, FoodException, CartException, CartItemException {
		CartItem cart = cartService.addItemToCart(req, jwt);
		return ResponseEntity.ok(cart);

	}

	@PutMapping("/cart-item/update")
	public ResponseEntity<CartItem> updateCartItemQuantity(
			@RequestBody UpdateCartItemRequest req, 
			@RequestHeader("Authorization") String jwt) throws CartItemException {
		CartItem cart = cartService.updateCartItemQuantity(req.getCartItemId(), req.getQuantity());
		return ResponseEntity.ok(cart);
	}

	@DeleteMapping("/cart-item/{id}/remove")
	public ResponseEntity<Cart> removeItemFromCart(@PathVariable Long id,
			@RequestHeader("Authorization") String jwt) throws UserException, CartException, CartItemException {

		Cart cart = cartService.removeItemFromCart(id, jwt);
		return ResponseEntity.ok(cart);

	}

	@GetMapping("/cart/total")
	public ResponseEntity<Double> calculateCartTotals(@RequestParam Long cartId,
			@RequestHeader("Authorization") String jwt) throws UserException, CartException {

		
		UserData user = userService.getUserDataFromToken(jwt);
		
		Cart cart =cartService.findCartByUserId(user.getId());
		double total = cartService.calculateCartTotals(cart);
		return ResponseEntity.ok(total);
	}
	
	@GetMapping("/cart/")
	public ResponseEntity<Cart> findUserCart(
			@RequestHeader("Authorization") String jwt) throws UserException, CartException {
UserData user=userService.getUserDataFromToken(jwt);
		Cart cart = cartService.findCartByUserId(user.getId());
		return ResponseEntity.ok(cart);
	}
	
	@PutMapping("/cart/clear")
	public ResponseEntity<Cart> cleareCart(
			@RequestHeader("Authorization") String jwt) throws UserException, CartException {
UserData user=userService.getUserDataFromToken(jwt);
		Cart cart = cartService.clearCart(user.getId());
		return ResponseEntity.ok(cart);
	}

}
