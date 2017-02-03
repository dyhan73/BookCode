package com.ensoa.order.entity.mapper;

import java.util.List;

import com.ensoa.order.entity.OrderEntity;


public interface OrderMapper {
	List<OrderEntity> findAll();
	OrderEntity findById(long id);
    void insert(OrderEntity order);
    void update(OrderEntity order);
    void delete(long id);
}
