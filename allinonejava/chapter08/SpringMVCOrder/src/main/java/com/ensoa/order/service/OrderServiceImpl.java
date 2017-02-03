package com.ensoa.order.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensoa.order.domain.Customer;
import com.ensoa.order.domain.Order;
import com.ensoa.order.entity.OrderEntity;
import com.ensoa.order.repository.OrderRepository;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderRepository repository;
	
	@Override
	public void purchaseOrder(Order order) {
		OrderEntity entity = new OrderEntity();
		entity.buildEntity(order);
		repository.save(entity);
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
