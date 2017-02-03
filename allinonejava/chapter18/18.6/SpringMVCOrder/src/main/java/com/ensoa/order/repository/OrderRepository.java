package com.ensoa.order.repository;

import java.util.List;

import com.ensoa.order.entity.OrderEntity;

public interface OrderRepository {
	OrderEntity findOne(long id);
	List<OrderEntity> findAll();
	void save(OrderEntity order);
	void delete(long id);
}
