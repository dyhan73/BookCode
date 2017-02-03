package com.ensoa.order.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ensoa.order.domain.Customer;
import com.ensoa.order.entity.CustomerEntity;
import com.ensoa.order.entity.PageRequest;
import com.ensoa.order.entity.Pageable;
import com.ensoa.order.repository.CustomerRepository;
import com.ensoa.order.repository.OrderRepository;

//@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
	//@Resource(name = "customerRepository")
	private CustomerRepository customerRepositiory;
	public void setCustomerRepositiory(CustomerRepository customerRepositiory) {
		this.customerRepositiory = customerRepositiory;
	}
	@Override
	public Customer getCustomer(long id) {
		CustomerEntity entity =  customerRepositiory.findOne(id);
		return entity.buildDomain();
	}
	

	@Override
	public Customer getCustomerByName(String name) {
		CustomerEntity entity =  customerRepositiory.findOneByName(name);
		return entity.buildDomain();

	}

	@Override
	public List<Customer> getCustomers() {
		List<Customer> customers = new ArrayList<Customer>();
		List<CustomerEntity> entities = customerRepositiory.findAll();
		for(CustomerEntity entity : entities) {
			Customer customer = entity.buildDomain();
			customers.add(customer);
		}
		return customers;
	}
	
	@Override
	public List<Customer> getCustomersByName(String name) {
		List<Customer> customers = new ArrayList<Customer>();
		List<CustomerEntity> entities = customerRepositiory.findByName(name);
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
		List<CustomerEntity> entities = customerRepositiory.findAll(page);
		for(CustomerEntity entity : entities) {
			Customer customer = entity.buildDomain();
			customers.add(customer);
		}
		return customers;
	}

	@Override
	public void saveCustomer(Customer customer) throws Exception {
		CustomerEntity entity = new CustomerEntity();
		entity.buildEntity(customer);
		customerRepositiory.save(entity);
	}

	@Override
	public void updateCustomer(Customer customer) {
		CustomerEntity entity = new CustomerEntity();
		entity.buildEntity(customer);
		customerRepositiory.save(entity);
	}

	@Override
	public void deleteCustomer(long id) {
		customerRepositiory.delete(id);
	}
}
