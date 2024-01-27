package com.shelf.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shelf.payloads.AuthResponse;
import com.shelf.payloads.LoginRequest;
import com.shelf.payloads.SignupRequest;
import com.shelf.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody SignupRequest body){
		AuthResponse response = authService.signup(body);
		return new ResponseEntity<AuthResponse>(response, HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest request){
		AuthResponse response = authService.login(request);
		return new ResponseEntity<AuthResponse>(response, HttpStatus.ACCEPTED);
	}

}
