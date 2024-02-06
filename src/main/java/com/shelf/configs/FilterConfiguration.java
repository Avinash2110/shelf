package com.shelf.configs;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shelf.filters.AuthenticationFilter;

@Configuration
public class FilterConfiguration {
	
	@Bean
	public FilterRegistrationBean<AuthenticationFilter> authenticationFilter(){
		FilterRegistrationBean<AuthenticationFilter> filter = new FilterRegistrationBean<>();
		filter.setFilter(new AuthenticationFilter());
		filter.setOrder(-1);
		filter.addUrlPatterns("/api/*");
		return filter;
	}

}
