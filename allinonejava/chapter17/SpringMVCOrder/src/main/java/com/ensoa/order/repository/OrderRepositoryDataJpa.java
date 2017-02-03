package com.ensoa.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ensoa.order.entity.OrderEntity;

public interface OrderRepositoryDataJpa extends JpaRepository<OrderEntity, Long> {

}
