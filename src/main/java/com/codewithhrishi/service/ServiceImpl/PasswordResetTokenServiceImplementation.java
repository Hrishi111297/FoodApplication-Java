package com.codewithhrishi.service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithhrishi.entity.PasswordResetToken;
import com.codewithhrishi.repository.PasswordResetTokenRepository;
import com.codewithhrishi.service.PasswordResetTokenService;
@Service
public class PasswordResetTokenServiceImplementation implements PasswordResetTokenService {
	@Autowired
	private PasswordResetTokenRepository passwordResetTokenRepository;

	@Override
	public PasswordResetToken findByToken(String token) {
		PasswordResetToken passwordResetToken =passwordResetTokenRepository.findByToken(token);
		return passwordResetToken;
	}

	@Override
	public void delete(PasswordResetToken resetToken) {
		passwordResetTokenRepository.delete(resetToken);
		
	}

}
