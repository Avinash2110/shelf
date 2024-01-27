package com.shelf.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtils {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public String encrptPassword(String password) {
		return passwordEncoder.encode(password);
	}
	
	public boolean validatePassword(String rawPassword, String encryptedPassword) {
		return passwordEncoder.matches(rawPassword, encryptedPassword);
	}

}
