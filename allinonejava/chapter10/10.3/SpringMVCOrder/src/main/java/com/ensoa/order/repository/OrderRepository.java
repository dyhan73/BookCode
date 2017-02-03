package com.ensoa.order.repository;

import java.util.List;

import com.ensoa.order.entity.OrderEntity;
import com.ensoa.order.entity.Pageable;

public interface OrderRepository {
	OrderEntity findOne(long id);
	List<OrderEntity> findAll();
	List<OrderEntity> findAll(Pageable page);
	void save(OrderEntity order);
	void delete(long id);
}
