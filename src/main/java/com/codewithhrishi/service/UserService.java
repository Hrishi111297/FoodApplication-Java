package com.codewithhrishi.service;

import java.util.List;

import com.codewithhrishi.entity.UserData;

public interface UserService {
	public UserData getUserDataFromToken(String token);

	public UserData getUserDataFromEmail(String email);
	public List<UserData> findAllUsers();

	public List<UserData> getPenddingRestaurantOwner();

	void updatePassword(UserData user, String newPassword);

	void sendPasswordResetEmail(UserData user);


}
