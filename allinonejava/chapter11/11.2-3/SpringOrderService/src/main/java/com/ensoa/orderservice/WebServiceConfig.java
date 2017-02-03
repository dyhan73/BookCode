package com.ensoa.orderservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.jaxws.SimpleJaxWsServiceExporter;

@Configuration
@ComponentScan(basePackages={
		"com.ensoa.order.service", 
		"com.ensoa.order.repository", 
		"com.ensoa.orderservice"}
)	
public class WebServiceConfig {
	@Bean
	public SimpleJaxWsServiceExporter serviceExporter() {
		SimpleJaxWsServiceExporter exporter = new SimpleJaxWsServiceExporter(); 
		exporter.setBaseAddress("http://localhost:8080/orderservice/");
		return exporter;
	}
}
