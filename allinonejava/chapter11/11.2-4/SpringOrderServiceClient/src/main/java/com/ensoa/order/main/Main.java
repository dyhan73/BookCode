package com.ensoa.order.main;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ensoa.orderservice.Customer;
import com.ensoa.orderservice.Order;
import com.ensoa.orderservice.OrderItem;
import com.ensoa.orderservice.OrderSerivceEndpoint;

public class Main {

	public static void main(String[] args) {
//		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		ApplicationContext ctx = new AnnotationConfigApplicationContext(WebServiceConfig.class);
		OrderSerivceEndpoint orderService = (OrderSerivceEndpoint)ctx.getBean("orderService");
		Customer customer = new Customer();
		List<Order> orders = orderService.getOrders(customer);
		printOrders(orders);
		Order order = orderService.getOrder(1);
		printOrder(order);
	}
	private static void printOrder(Order order) {
		System.out.println(order.getId() + " : " + order.getOrderDate());
		for(OrderItem item : order.getItems())
			System.out.println(item.getId() + " : " + item.getProduct().getName() + ", " + item.getAmount());
	}
	
	private static void printOrders(List<Order> orders) {
		for(Order order : orders)
			printOrder(order);
	}

}
