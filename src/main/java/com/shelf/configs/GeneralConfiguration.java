package com.shelf.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shelf.utils.JwtUtils;

@Configuration
public class GeneralConfiguration {
	
	@Bean
	public JwtUtils jwtUtils() {
		return new JwtUtils();
	}

}
