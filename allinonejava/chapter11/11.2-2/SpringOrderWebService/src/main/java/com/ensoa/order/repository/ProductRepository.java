package com.ensoa.order.repository;

import java.util.ArrayList;
import java.util.List;

import com.ensoa.order.entity.PageRequest;
import com.ensoa.order.entity.Pageable;
import com.ensoa.order.entity.ProductEntity;

public interface ProductRepository {
	ProductEntity findOne(long id);
	List<ProductEntity> findAll();
	List<ProductEntity> findAll(Pageable page);
	void save(ProductEntity product);
	void delete(long id);
}
