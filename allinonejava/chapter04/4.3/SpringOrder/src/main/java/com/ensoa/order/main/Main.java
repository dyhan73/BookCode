package com.ensoa.order.main;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.ensoa.order.config.AppConfig;
import com.ensoa.order.domain.Customer;
import com.ensoa.order.service.CustomerService;

public class Main {

	public static void main(String[] args) {
        System.out.println("Ch4.3 starting");
//		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
//		ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		ApplicationContext ctx = new FileSystemXmlApplicationContext("src/main/java/beans.xml");
		CustomerService customerService = (CustomerService)ctx.getBean("customerService");
		List<Customer> customers = customerService.getCustomers();
		for(Customer customer : customers) {
			System.out.println(customer);
		}
		customers = customerService.getCustomersByPage(3,5);
		for(Customer customer : customers) {
			System.out.println(customer);
		}

		Customer customer = customerService.getCustomer(1);
		System.out.println(customer);		
		customer = new Customer();
		customer.setId(1);
		customer.setName("전병선");
		customer.setAddress("서울시");
		customer.setEmail("bsjun@ensoa.co.kr");
		customerService.saveCustomer(customer);
		customer.setName("홍길동");
		customerService.updateCustomer(customer);
		customerService.deleteCustomer(1);
	}

}
