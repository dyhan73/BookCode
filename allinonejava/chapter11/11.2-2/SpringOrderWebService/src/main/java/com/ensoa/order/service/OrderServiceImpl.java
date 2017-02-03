package com.ensoa.order.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensoa.order.domain.Customer;
import com.ensoa.order.domain.Order;
import com.ensoa.order.entity.OrderEntity;
import com.ensoa.order.repository.OrderRepository;
import com.ensoa.order.repository.OrderRepositoryImpl;

//@Service("orderService")
public class OrderServiceImpl implements OrderService {
	// @Autowired
	private OrderRepository orderRepository;
	public void setOrderRepository(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	
	@Override
	public void purchaseOrder(Order order) {
		OrderEntity entity = new OrderEntity();
		entity.buildEntity(order);
		orderRepository.save(entity);
	}

	@Override
	public List<Order> getOrders(Customer customer) {
		System.out.println("getOrders");
		List<Order> orders = new ArrayList<Order>();
		List<OrderEntity> entities = orderRepository.findAll();
		for(OrderEntity entity : entities) {
			Order order = entity.buildDomain();
			orders.add(order);
		}
		return orders;
	}

	@Override
	public Order getOrder(long id) {
		OrderEntity entity = orderRepository.findOne(id);
		return entity.buildDomain();
	}

}
