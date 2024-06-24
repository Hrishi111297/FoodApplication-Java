package com.codewithhrishi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithhrishi.entity.UserData;
import com.codewithhrishi.exception.UserException;
import com.codewithhrishi.service.UserService;
import com.codewithhrishi.util.JwtConstants;

@RestController
@RequestMapping("/api/users")
public class UserController {
@Autowired
private UserService userService;
@GetMapping("/profile")
public ResponseEntity<UserData> getUserProfileHandler(@RequestHeader("Authorization") String jwt) throws UserException {
	
	UserData user = userService.getUserDataFromToken(jwt);

	return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
}

}
