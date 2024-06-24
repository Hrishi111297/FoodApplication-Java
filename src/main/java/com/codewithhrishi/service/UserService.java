package com.codewithhrishi.service;

import com.codewithhrishi.entity.UserData;

public interface UserService {
	public UserData getUserDataFromToken(String token);

	public UserData getUserDataFromEmail(String email);

}
