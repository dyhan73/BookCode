package com.ensoa.order.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ensoa.order.domain.Customer;
import com.ensoa.order.entity.CustomerEntity;
import com.ensoa.order.repository.CustomerRepositoryDataJpa;

@Service("customerService")
@Transactional
public class CustomerServiceDataJpa implements CustomerService {
	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceDataJpa.class);
	@Autowired
	private CustomerRepositoryDataJpa repository;

	@Override
	public Customer getCustomer(long id) {
		CustomerEntity entity =  repository.findOne(id);
		return entity.buildDomain();
	}
	
	@Override
	public Customer getCustomerByName(String name) {
		List<Customer> customers = getCustomersByName(name);
		if(customers.size() > 0)
			return customers.get(0);
		return null;
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
		Page<CustomerEntity> entities = repository.findAll(page);
		List<Customer> customers = new ArrayList<Customer>();
			for(CustomerEntity entity : entities.getContent()) {
			Customer customer = entity.buildDomain();
			customers.add(customer);
		}
		return customers;
	}

	@Override
	@Transactional(rollbackFor = NotFoundException.class)
	public void saveCustomer(Customer customer) {
		CustomerEntity entity = new CustomerEntity();
		entity.buildEntity(customer);
		repository.save(entity);
	}

	@Override
	@Transactional(rollbackFor = NotFoundException.class)
	public void updateCustomer(Customer customer) {
		CustomerEntity entity = repository.findOne(customer.getId());
		entity.setName(customer.getName());
		entity.setAddress(customer.getAddress());
		entity.setEmail(customer.getEmail());
	}

	@Override
	@Transactional(rollbackFor = NotFoundException.class)
	public void deleteCustomer(long id) {
		try {
			if(id == 0) {
				List<CustomerEntity> customers = repository.findAll();
				for(CustomerEntity customer : customers)
					repository.delete(customer.getId());
			}
			else
				repository.delete(id);
		} catch (RuntimeException e) {
			logger.info("삭제를 취소하고 롤백합니다!!");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}

}
