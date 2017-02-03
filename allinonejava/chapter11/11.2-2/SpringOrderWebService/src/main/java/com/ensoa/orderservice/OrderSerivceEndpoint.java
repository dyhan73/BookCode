package com.ensoa.orderservice;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;
import javax.servlet.ServletContext;
import javax.xml.ws.WebServiceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ensoa.order.domain.Customer;
import com.ensoa.order.domain.Order;
import com.ensoa.order.service.OrderService;
import com.ensoa.order.service.OrderServiceImpl;

@WebService(serviceName="OrderService")
public class OrderSerivceEndpoint {
	private OrderService orderService;
	@WebMethod(exclude=true)
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

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
