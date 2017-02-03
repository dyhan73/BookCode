package com.ensoa.order.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ensoa.order.entity.OrderEntity;
import com.ensoa.order.entity.OrderItemEntity;
import com.ensoa.order.entity.Pageable;

@Repository("orderRepository")
public class OrderRepositoryImpl implements OrderRepository {
	private ProductRepository productRepository;

	@Override
	public OrderEntity findOne(long id) {
		OrderEntity order = new OrderEntity();
		order.setId(id);
		order.setOrderDate(new Date());
		List<OrderItemEntity> items = new ArrayList<OrderItemEntity>();
		for(int i = 0; i < 10; ++i) {
			OrderItemEntity item = new OrderItemEntity();
			item.setId(i);
			item.setAmount((i+1) * 10);
			item.setProduct(productRepository.findOne(i));
			items.add(item);
		}
		order.setItems(items);	
		return order;
	}

	@Override
	public List<OrderEntity> findAll() {
		List<OrderEntity> orders = new ArrayList<OrderEntity>();
		for(int i = 0; i < 10; ++i) {
			OrderEntity order = new OrderEntity();
			order.setId(i);
			order.setOrderDate(new Date());
			List<OrderItemEntity> items = new ArrayList<OrderItemEntity>();
			for(int j = 0; j < 10; ++j) {
				OrderItemEntity item = new OrderItemEntity();
				item.setId(j);
				item.setAmount((j+1) * 10);
				item.setProduct(productRepository.findOne(j));
				items.add(item);
			}
			order.setItems(items);
		}
		return orders;
	}

	@Override
	public List<OrderEntity> findAll(Pageable page) {
		List<OrderEntity> orders = new ArrayList<OrderEntity>();
		int index = page.getIndex();
		int size = page.getSize();
		for(int i = index; i < index+size; ++i) {
			OrderEntity order = new OrderEntity();
			order.setId(i);
			order.setOrderDate(new Date());
			List<OrderItemEntity> items = new ArrayList<OrderItemEntity>();
			for(int j = 0; j < 10; ++j) {
				OrderItemEntity item = new OrderItemEntity();
				item.setId(j);
				item.setAmount((j+1) * 10);
				item.setProduct(productRepository.findOne(j));
				items.add(item);
			}
			order.setItems(items);
		}
		return orders;
	}

	@Override
	public void save(OrderEntity order) {
		System.out.println(order + "가 저장되었습니다.");
	}

	@Override
	public void delete(long id) {
		OrderEntity order = findOne(id);
		System.out.println(order + "가 삭되었습니다.");
	}

}
