package com.ensoa.order.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ensoa.order.domain.Customer;
import com.ensoa.order.domain.Order;
import com.ensoa.order.entity.OrderEntity;
import com.ensoa.order.repository.OrderRepository;

@Service("orderService")
public class OrderAmqpServiceImpl implements OrderService {
	@Autowired
	private OrderRepository repository;
	@Autowired
	@Qualifier("orderRabbitTemplate")
	private RabbitTemplate rabbitTemplate;
	
	@Override
	public void purchaseOrder(final Order order) {
		rabbitTemplate.convertAndSend(order);
	}

	@Override
	public List<Order> getOrders(Customer customer) {
		List<Order> orders = new ArrayList<Order>();
		List<OrderEntity> entities = repository.findAll();
		for(OrderEntity entity : entities) {
			Order order = entity.buildDomain();
			orders.add(order);
		}
		return orders;
	}

	@Override
	public Order getOrder(long id) {
		OrderEntity entity = repository.findOne(id);
		return entity.buildDomain();
	}

}
