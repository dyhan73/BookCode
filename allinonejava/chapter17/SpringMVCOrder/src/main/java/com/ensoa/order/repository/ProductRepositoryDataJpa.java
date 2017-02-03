package com.ensoa.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ensoa.order.entity.ProductEntity;

public interface ProductRepositoryDataJpa extends JpaRepository<ProductEntity, Long> {

}
