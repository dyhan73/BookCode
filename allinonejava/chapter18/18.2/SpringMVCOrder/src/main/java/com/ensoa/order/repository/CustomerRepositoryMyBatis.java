package com.ensoa.order.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ensoa.order.entity.CustomerEntity;
import com.ensoa.order.entity.Pageable;
import com.ensoa.order.entity.mapper.CustomerMapper;

@Repository("customerRepository")
@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
public class CustomerRepositoryMyBatis implements CustomerRepository {
	@Autowired
	private CustomerMapper mapper;
	
	@Override
	public CustomerEntity findOne(long id) {
		CustomerEntity customer =mapper.findById(id);
		return customer;
	}

	@Override
	public CustomerEntity findOneByName(String name) {
		List<CustomerEntity> customers = findByName(name);
		if(customers.size() > 0)
			return customers.get(0);
		return null;
	}
	
	@Override
	public List<CustomerEntity> findAll() {
		List<CustomerEntity> customers = mapper.findAll();
		return customers;
	}
	
	@Override
	public List<CustomerEntity> findByName(String name) {
		if(name == null)
			return null;
		List<CustomerEntity> customers = mapper.findByName(name);
		return customers;
	}

	@Override
	public List<CustomerEntity> findAll(Pageable page) {
		RowBounds rowBounds = new RowBounds(page.getIndex(), page.getSize());
		List<CustomerEntity> customers = mapper.findAll(rowBounds);
		return customers;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void save(CustomerEntity customer) {
		mapper.insert(customer);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void update(CustomerEntity customer) {
		mapper.update(customer);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(long id) {
		mapper.delete(id);
	}
}