package com.ensoa.order.httpservice;



import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;

import com.ensoa.order.service.ProductService;

@Configuration
@EnableWebMvc
public class RootConfiguration extends WebMvcConfigurerAdapter {
	@Autowired
	private ProductService productService;
	@Bean
	public HttpInvokerServiceExporter productHttpService() {
		HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
		exporter.setService(productService);
		exporter.setServiceInterface(ProductService.class);
		return exporter;
	}
	@Bean
	public SimpleUrlHandlerMapping urlMapping() {
		SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
		Properties props = new Properties();
		props.put("/product.service", "productHttpService");
		mapping.setMappings(props);
		return mapping;
	}
}
