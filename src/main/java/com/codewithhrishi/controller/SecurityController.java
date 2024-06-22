package com.codewithhrishi.controller;

import java.util.Collection;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithhrishi.domain.USER_ROLE;
import com.codewithhrishi.entity.Cart;
import com.codewithhrishi.entity.UserData;
import com.codewithhrishi.exception.UserException;
import com.codewithhrishi.repository.CartRepository;
import com.codewithhrishi.repository.UserDataRepository;
import com.codewithhrishi.request.userDataDTO;
import com.codewithhrishi.response.AuthResponse;
import com.codewithhrishi.security.JwtHelper;
import com.codewithhrishi.security.JwtRequest;


import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class SecurityController {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager manager;

	@Autowired
	private JwtHelper helper;
	@Autowired
	CartRepository cartRepository;

	@Autowired
	private UserDataRepository userDataRepository;
	@Autowired
	ModelMapper mp;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	private Logger logger = LoggerFactory.getLogger(SecurityController.class);

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@Valid @RequestBody JwtRequest request, HttpServletResponse response)
			throws InterruptedException {
		this.doAuthenticate(request.getUsername(), request.getPassword());
		System.out.println(request.getUsername() + "&" + request.getPassword());
		Thread.sleep(5000);

		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
		String token = this.helper.generateToken(userDetails);

		AuthResponse authResponse = new AuthResponse();

		authResponse.setMessage("Login Success");
		authResponse.setJwt(token);
		Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

		String roleName = authorities.isEmpty() ? null : authorities.iterator().next().getAuthority();

		authResponse.setRole(USER_ROLE.valueOf(roleName));

		return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);

	
	}

	private void doAuthenticate(String username, String password) {

		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username,
				password);
		try {
			manager.authenticate(authentication);

		} catch (BadCredentialsException e) {
			throw new BadCredentialsException(" Invalid Username or Password  !!");
		}

	}

	@PostMapping("/signup")
	public ResponseEntity<com.codewithhrishi.response.AuthResponse> createUserHandler(
			@Valid @RequestBody userDataDTO user) throws UserException {

		if (userDataRepository.existsByEmail(user.getEmail())) {
			throw new UserException("Email Is Already Used With Another Account");
		}

		// Create new user
		UserData createdUser = mp.map(user, UserData.class);
		createdUser.setPassword(bCryptPasswordEncoder.encode(createdUser.getPassword()));
		UserData savedUser = userDataRepository.save(createdUser);
		Cart cart = new Cart();
		cart.setCustomer(savedUser);
		cartRepository.save(cart);
		String token = this.helper.generateToken(userDetailsService.loadUserByUsername(savedUser.getEmail()));

		AuthResponse authResponse = new AuthResponse();
		authResponse.setJwt(token);
		authResponse.setMessage("Register Success");
		authResponse.setRole(savedUser.getRole());

		return new ResponseEntity<>(authResponse, HttpStatus.OK);

	}




	
}