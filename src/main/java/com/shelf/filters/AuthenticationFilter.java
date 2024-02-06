package com.shelf.filters;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.shelf.models.Customer;
import com.shelf.repositories.CustomerRepository;
import com.shelf.utils.JwtUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthenticationFilter extends OncePerRequestFilter{
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authHeader = request.getHeader("Authorization");
		if(authHeader==null || !authHeader.startsWith("Bearer ")) {
			throw new RuntimeException("User not authenticated");
		}
		
		if(customerRepository==null){
            ServletContext servletContext = request.getServletContext();
            WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
            customerRepository = webApplicationContext.getBean(CustomerRepository.class);
        }
		
		if(jwtUtils==null){
            ServletContext servletContext = request.getServletContext();
            WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
            jwtUtils = webApplicationContext.getBean(JwtUtils.class);
        }
		
		String token = authHeader.substring(7);
		if(jwtUtils.isTokenExpired(token)) {
			throw new RuntimeException("User session expired");
		}
		
		String username = jwtUtils.extractUsername(token);
		if(username==null || username=="") {
			throw new RuntimeException("Invalid identity");
		}
		Customer customer = customerRepository.findByEmail(username);
		if(customer==null) {
			throw new RuntimeException("Invalid identity");
		}
		filterChain.doFilter(request, response);
		
	}

}
