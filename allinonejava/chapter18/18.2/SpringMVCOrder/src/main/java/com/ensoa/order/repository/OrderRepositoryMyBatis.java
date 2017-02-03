package com.ensoa.order.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ensoa.order.domain.OrderItem;
import com.ensoa.order.entity.OrderEntity;
import com.ensoa.order.entity.OrderItemEntity;
import com.ensoa.order.entity.mapper.OrderItemMapper;
import com.ensoa.order.entity.mapper.OrderMapper;

@Repository("orderRepository")
@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
public class OrderRepositoryMyBatis implements OrderRepository {
	private ProductRepository productRepository;
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderItemMapper itemMapper;

	@Override
	public OrderEntity findOne(long id) {
		OrderEntity order = orderMapper.findById(id);
		return order;
	}

	@Override
	public List<OrderEntity> findAll() {
		List<OrderEntity> orders = orderMapper.findAll();
		return orders;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void save(OrderEntity order) {
		orderMapper.insert(order);
		List<OrderItemEntity> items = order.getItems();
		for(OrderItemEntity item : items) {
			item.setOrderId(order.getId());
			itemMapper.insert(item);
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(long id) {
		OrderEntity order = orderMapper.findById(id);
		List<OrderItemEntity> items = order.getItems();
		for(OrderItemEntity item : items) {
			itemMapper.delete(item.getId());
		}
		orderMapper.delete(id);
	}

}
