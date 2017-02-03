package com.ensoa.order.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ensoa.order.domain.Customer;
import com.ensoa.order.entity.CustomerEntity;
import com.ensoa.order.entity.PageRequest;
import com.ensoa.order.entity.Pageable;
import com.ensoa.order.repository.CustomerRepository;

// @Component("customerService")
// @Service("customerService")
public class CustomerServiceImpl  implements CustomerService {
	@Autowired
//	@Inject
	private CustomerRepository repository;
	@Override
	public Customer getCustomer(long id) {
		CustomerEntity entity =  repository.findOne(id);
		return entity.buildDomain();
	}

	@Override
	public Customer getCustomerByName(String name) {
		CustomerEntity entity =  repository.findOneByName(name);
		return entity.buildDomain();

	}
	
	@Override
	public List<Customer> getCustomers() {
		List<Customer> customers = new ArrayList<Customer>();
		List<CustomerEntity> entities = repository.findAll();
		for(CustomerEntity entity : entities) {
			Customer customer = entity.buildDomain();
			customers.add(customer);
		}
		return customers;
	}
	
	@Override
	public List<Customer> getCustomersByName(String name) {
		List<Customer> customers = new ArrayList<Customer>();
		List<CustomerEntity> entities = repository.findByName(name);
		for(CustomerEntity entity : entities) {
			Customer customer = entity.buildDomain();
			customers.add(customer);
		}
		return customers;
	}
	
	@Override
	public List<Customer> getCustomersByPage(int index, int size) {
		Pageable page = new PageRequest(index, size);
		List<Customer> customers = new ArrayList<Customer>();
		List<CustomerEntity> entities = repository.findAll(page);
		for(CustomerEntity entity : entities) {
			Customer customer = entity.buildDomain();
			customers.add(customer);
		}
		return customers;
	}

	@Override
	public void saveCustomer(Customer customer) {
		CustomerEntity entity = new CustomerEntity();
		entity.buildEntity(customer);
		repository.save(entity);
	}

	@Override
	public void updateCustomer(Customer customer) {
		CustomerEntity entity = new CustomerEntity();
		entity.buildEntity(customer);
		repository.save(entity);
	}

	@Override
	public void deleteCustomer(long id) {
		repository.delete(id);
	}
}
