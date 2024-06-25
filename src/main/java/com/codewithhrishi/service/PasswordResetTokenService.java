package com.codewithhrishi.service;

import com.codewithhrishi.entity.PasswordResetToken;

public interface PasswordResetTokenService {

	public PasswordResetToken findByToken(String token);

	public void delete(PasswordResetToken resetToken);

}
