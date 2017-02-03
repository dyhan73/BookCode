package com.ensoa.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ensoa.order.service.CustomerService;
import com.ensoa.order.service.CustomerServiceImpl;

@Configuration
public class AppConfig {
	@Bean
	CustomerService customerService() {
		return new CustomerServiceImpl();
	}
}
