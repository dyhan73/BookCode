package com.ensoa.order.rmi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;

import com.ensoa.order.service.CustomerService;
import com.ensoa.order.service.CustomerServiceImpl;

@Configuration
@ComponentScan(basePackages={
		"com.ensoa.order.service", 
		"com.ensoa.order.repository", 
		"com.ensoa.order.aspect"}
)	
public class RmiServiceConfig {
	@Bean
	public CustomerService customerService() {
		return new CustomerServiceImpl(); 
	}
	@Bean
	public RmiServiceExporter serviceExporter() throws Exception {
		RmiServiceExporter exporter = new RmiServiceExporter(); 
		exporter.setServiceName("customerService"); 
		exporter.setService(customerService()); 
		exporter.setServiceInterface(CustomerService.class); 
		//exporter.setRegistryHost("localhost"); 
		//exporter.setRegistryPort(1199); 
		//exporter.afterPropertiesSet();
		return exporter;
	}
}
