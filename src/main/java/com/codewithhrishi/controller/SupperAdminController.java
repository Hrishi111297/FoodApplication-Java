package com.codewithhrishi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithhrishi.entity.UserData;

@RestController
public class SupperAdminController {
	
	@Autowired
	private com.codewithhrishi.service.UserService userService;
	
	@GetMapping("/api/customers")
	public ResponseEntity<List<UserData>> getAllCustomers() {
		
		List<UserData> users =userService.findAllUsers();
		
		return new ResponseEntity<>(users,HttpStatus.ACCEPTED);

	}
	
	@GetMapping("/api/pending-customers")
	public ResponseEntity<List<UserData>> getPenddingRestaurantUser(){
		List<UserData> users=userService.getPenddingRestaurantOwner();
		return new ResponseEntity<List<UserData>>(users,HttpStatus.ACCEPTED);
		
	}
}
