package com.ensoa.orderamqp;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ensoa.order.domain.Customer;
import com.ensoa.order.domain.Order;
import com.ensoa.order.service.CustomerService;
import com.ensoa.order.service.OrderService;
import com.rabbitmq.client.Channel;

@Component
public class CustomerListener {
	@Autowired
	private CustomerService customerService;
	public void handleMessage(Customer customer) {
		try {
			customerService.saveCustomer(customer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
