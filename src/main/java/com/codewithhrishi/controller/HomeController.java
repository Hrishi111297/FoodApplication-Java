package com.codewithhrishi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class HomeController {
	@GetMapping("/test")
	public ResponseEntity<String> getStatus() {
		return new ResponseEntity<>("Server is running", HttpStatus.OK);

	}
}
