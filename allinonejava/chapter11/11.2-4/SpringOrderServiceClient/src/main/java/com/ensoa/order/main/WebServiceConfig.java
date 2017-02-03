package com.ensoa.order.main;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.jaxws.JaxWsPortProxyFactoryBean;
import org.springframework.remoting.jaxws.SimpleJaxWsServiceExporter;

import com.ensoa.orderservice.OrderSerivceEndpoint;

@Configuration
public class WebServiceConfig {
	@Bean
	public JaxWsPortProxyFactoryBean orderService() throws MalformedURLException {
		JaxWsPortProxyFactoryBean factory = new JaxWsPortProxyFactoryBean();
		factory.setWsdlDocumentUrl(new URL("http://localhost:8080/orderservice/OrderService?WSDL"));
		factory.setServiceInterface(OrderSerivceEndpoint.class);
		factory.setServiceName("OrderService");
		return factory;
	}
}
