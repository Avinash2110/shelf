package com.shelf.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shelf.models.Customer;
import com.shelf.payloads.AuthResponse;
import com.shelf.payloads.LoginRequest;
import com.shelf.payloads.SignupRequest;
import com.shelf.repositories.CustomerRepository;
import com.shelf.utils.JwtUtils;
import com.shelf.utils.PasswordUtils;

@Service
public class AuthService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private PasswordUtils passwordUtils;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	public AuthResponse signup(SignupRequest request) {
		
		Customer customer = customerRepository.findByEmail(request.getEmail());
		if(customer!=null) {
			throw new RuntimeException("User already exists");
		}
		
		Customer customerData = new Customer();
		customerData.setFirstName(request.getFirstName());
		if(request.getLastName()!=null) {
			customerData.setLastName(request.getLastName());
		}
		customerData.setEmail(request.getEmail());
		customerData.setPassword(passwordUtils.encrptPassword(request.getPassword()));
		customerData.setCustomerType(request.getCustomerType());
		
		Customer savedCustomer = customerRepository.save(customerData);
		String token = null;
		if(savedCustomer!=null) {
			token = jwtUtils.generateToken(savedCustomer.getEmail());
		}
		
		AuthResponse response = new AuthResponse();
		response.setStatus("SUCCESS");
		response.setMessage("Customer registered successfylly");
		response.setToken(token);
		
		return response;
	}
	
	public AuthResponse login(LoginRequest request) {
		Customer customer = customerRepository.findByEmail(request.getEmail());
		if(customer==null) {
			throw new RuntimeException("Username is wrong");
		}
		String encryptedPassword = passwordUtils.encrptPassword(request.getPassword());
		System.out.println(encryptedPassword);
		if(!passwordUtils.validatePassword(request.getPassword(), encryptedPassword)) {
			throw new RuntimeException("Incorrect password");
		}
		String token = jwtUtils.generateToken(customer.getEmail());
		
		AuthResponse response = new AuthResponse();
		response.setStatus("SUCCESS");
		response.setMessage("User logged in successully");
		response.setToken(token);
		return response;
	}

}
