package com.ensoa.order.entity.mapper;
import java.util.List;

import com.ensoa.order.entity.OrderItemEntity;

public interface OrderItemMapper {
	List<OrderItemEntity> findByOrderId(long orderId);
	OrderItemEntity findById(long id);
    void insert(OrderItemEntity orderItem);
    void update(OrderItemEntity orderItem);
    void delete(long id);
}
