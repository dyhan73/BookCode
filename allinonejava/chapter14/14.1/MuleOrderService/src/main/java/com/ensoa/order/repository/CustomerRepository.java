package com.ensoa.order.repository;

import java.util.List;

import com.ensoa.order.entity.CustomerEntity;

public interface CustomerRepository {
	CustomerEntity findOne(long id);
	CustomerEntity findOneByName(String name);
	List<CustomerEntity> findAll();
	List<CustomerEntity> findByName(String name);
	void save(CustomerEntity customer);
	void delete(long id);
}
