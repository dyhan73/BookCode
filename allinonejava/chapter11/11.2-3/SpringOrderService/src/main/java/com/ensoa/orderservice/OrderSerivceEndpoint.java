package com.ensoa.orderservice;

import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ensoa.order.domain.Customer;
import com.ensoa.order.domain.Order;
import com.ensoa.order.service.OrderServiceImpl;

@Component
@WebService(serviceName="OrderService")
public class OrderSerivceEndpoint {
	@Autowired
	private OrderServiceImpl orderService;

	@WebMethod
	public Order getOrder(long id) {
		return orderService.getOrder(id);
	}
	@WebMethod
	public List<Order> getOrders(Customer customer) {
		return orderService.getOrders(customer);
	}
	@WebMethod
	public void purchaseOrder(Order order) {
		orderService.purchaseOrder(order);
	}
}
