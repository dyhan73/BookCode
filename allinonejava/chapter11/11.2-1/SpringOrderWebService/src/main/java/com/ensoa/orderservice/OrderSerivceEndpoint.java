package com.ensoa.orderservice;

import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.servlet.ServletContext;
import javax.xml.ws.WebServiceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ensoa.order.domain.Customer;
import com.ensoa.order.domain.Order;
import com.ensoa.order.service.OrderService;
import com.ensoa.order.service.OrderServiceImpl;

@WebService(serviceName="OrderService")
public class OrderSerivceEndpoint extends SpringBeanAutowiringSupport {
	@Autowired
	private OrderService orderService;
	/*
	 @Resource
	private WebServiceContext context;
	*/
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
	/*
	private OrderService orderService() {
		OrderService OrderService = null;
		ServletContext servletContext = (ServletContext) context
			    .getMessageContext().get("javax.xml.ws.servlet.context");
			WebApplicationContext webApplicationContext = 
					WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
			OrderService = (OrderService) webApplicationContext.
					getAutowireCapableBeanFactory().getBean("orderService");
			return OrderService;
	}
	*/
}
