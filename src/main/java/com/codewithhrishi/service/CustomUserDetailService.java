package com.codewithhrishi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.codewithhrishi.domain.USER_ROLE;
import com.codewithhrishi.entity.UserData;
import com.codewithhrishi.exception.ResourceNotFound;
import com.codewithhrishi.repository.UserDataRepository;
@Service
public class CustomUserDetailService implements UserDetailsService {
	@Autowired
private	UserDataRepository userDataRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	UserData userData=userDataRepository.findByEmail(email).orElseThrow( ()->new ResourceNotFound("User", "email", email));
	USER_ROLE role = userData.getRole();
	if(role==null)
		   role = USER_ROLE.ROLE_CUSTOMER;
	System.out.println("role  ---- "+role);
	
	  List<GrantedAuthority> authorities = new ArrayList<>();
	    authorities.add(new SimpleGrantedAuthority(role.toString()));
	
	return new org.springframework.security.core.userdetails.User(
			userData.getEmail(),userData.getPassword(),authorities);
	}
	

}
