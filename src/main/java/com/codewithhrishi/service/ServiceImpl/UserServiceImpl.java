package com.codewithhrishi.service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithhrishi.entity.UserData;
import com.codewithhrishi.exception.ResourceNotFound;
import com.codewithhrishi.repository.UserDataRepository;
import com.codewithhrishi.security.JwtHelper;
import com.codewithhrishi.service.UserService;
import com.codewithhrishi.util.JwtConstants;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	JwtHelper helper;
	@Autowired
	UserDataRepository  dataRepository;
	@Override
	public UserData getUserDataFromToken(String token) {
		 if (token != null && token.startsWith(JwtConstants.BEARER)) {
	         //looking good
			 token = token.substring(7);
			 }
	String email=	helper.getUsernameFromToken(token);
	return getUserDataByEmail(email);
	}

	@Override
	public UserData getUserDataFromEmail(String email) {
		return getUserDataByEmail(email);
	}
	private UserData getUserDataByEmail(String email) {
		UserData  data=	dataRepository.findByEmail(email).orElseThrow(()->new ResourceNotFound("User Details Not Found for the", "email", email));
		return data;
		
	}

}
