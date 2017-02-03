package com.ensoa.orderamqp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ensoa.order.domain.Customer;
import com.ensoa.order.domain.Order;
import com.ensoa.order.service.CustomerService;
import com.ensoa.order.service.OrderService;

@Component
public class OrderListener {
	@Autowired
	private CustomerService customerService;
	@Autowired
	private OrderService orderService;
	public void receiveCustomerMessage(Customer customer) {
		try {
			customerService.saveCustomer(customer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void receiveOrderMessage(Order order) {
		try {
			orderService.purchaseOrder(order);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
