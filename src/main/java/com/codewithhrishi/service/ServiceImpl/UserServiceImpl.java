package com.codewithhrishi.service.ServiceImpl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.codewithhrishi.entity.PasswordResetToken;
import com.codewithhrishi.entity.UserData;
import com.codewithhrishi.exception.ResourceNotFound;
import com.codewithhrishi.repository.PasswordResetTokenRepository;
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
	
	private PasswordEncoder passwordEncoder;
	private PasswordResetTokenRepository passwordResetTokenRepository;
	private JavaMailSender javaMailSender;
	
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
	
	@Override
	public List<UserData> findAllUsers() {
		// TODO Auto-generated method stub
		return dataRepository.findAll();
	}

	@Override
	public List<UserData> getPenddingRestaurantOwner() {
		
		return dataRepository.getPenddingRestaurantOwners();
	}
	
	@Override
    public void updatePassword(UserData user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        dataRepository.save(user);
    }

	@Override
	public void sendPasswordResetEmail(UserData user) {
		
		// Generate a random token (you might want to use a library for this)
        String resetToken = generateRandomToken();
        
        // Calculate expiry date
        Date expiryDate = calculateExpiryDate();

        // Save the token in the database
        PasswordResetToken passwordResetToken = new PasswordResetToken(resetToken,user,expiryDate);
        passwordResetTokenRepository.save(passwordResetToken);

        // Send an email containing the reset link
        sendEmail(user.getEmail(), "Password Reset", "Click the following link to reset your password: http://localhost:3000/account/reset-password?token=" + resetToken);
	}
	private void sendEmail(String to, String subject, String message) {
	    SimpleMailMessage mailMessage = new SimpleMailMessage();

	    mailMessage.setTo(to);
	    mailMessage.setSubject(subject);
	    mailMessage.setText(message);

	    javaMailSender.send(mailMessage);
	}
	private String generateRandomToken() {
	    return UUID.randomUUID().toString();
	}
	private Date calculateExpiryDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MINUTE, 10);
        return cal.getTime();
    }
	
	

}
