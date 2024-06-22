package com.codewithhrishi.controller;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithhrishi.security.JwtHelper;
import com.codewithhrishi.security.JwtRequest;
import com.codewithhrishi.security.JwtResponse;

import jakarta.servlet.http.Cookie;
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


  

    private Logger logger = LoggerFactory.getLogger(SecurityController.class);


    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody JwtRequest request, HttpServletResponse response) throws InterruptedException {
        this.doAuthenticate(request.getUsername(), request.getPassword());
        System.out.println(request.getUsername() + "&" + request.getPassword());
        Thread.sleep(5000);

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = this.helper.generateToken(userDetails);

        // Set token in cookie
        Cookie cookie = new Cookie("jwtToken", token);
        cookie.setHttpOnly(true); // Ensures the cookie is only accessible via HTTP (not accessible via JavaScript)
        cookie.setPath("/"); // Set the cookie path to root ("/") so it's accessible from all paths
        response.addCookie(cookie);

//        JwtResponse jwtResponse = JwtResponse.builder()
//                .token(token)
//                .username(userDetails.getUsername())
//                .employeeData(modelMapper.map(userDetails, EmployeeDto.class))
//                .build();
        JwtResponse jwtResponse = JwtResponse.builder()
                .token(token)
                .build();

        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
    }

    private void doAuthenticate(String username, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }
    @GetMapping("/status")
    public ResponseEntity<String> getStatus() {
    	 logger.info("Server is running");
             return new ResponseEntity<>("Server is running", HttpStatus.OK);
            
    }
    }