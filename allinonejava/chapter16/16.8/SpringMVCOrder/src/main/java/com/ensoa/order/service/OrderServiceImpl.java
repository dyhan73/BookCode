package com.ensoa.order.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ensoa.order.domain.Customer;
import com.ensoa.order.domain.Order;
import com.ensoa.order.entity.CustomerEntity;
import com.ensoa.order.entity.OrderEntity;
import com.ensoa.order.repository.CustomerRepository;
import com.ensoa.order.repository.OrderRepository;

@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderRepository repository;
	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public void purchaseOrder(Order order) {
		OrderEntity entity = new OrderEntity();
		entity.buildEntity(order);
		CustomerEntity customer = customerRepository.findOne(entity.getCustomer().getId());
		entity.setCustomer(customer);
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
