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
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.ensoa.order.domain.Customer;
import com.ensoa.order.entity.CustomerEntity;
import com.ensoa.order.entity.PageRequest;
import com.ensoa.order.entity.Pageable;
import com.ensoa.order.repository.CustomerRepository;

@Service("customerService")
public class CustomerServiceTxTemplate implements CustomerService {
	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceTxTemplate.class);
	@Resource(name = "customerRepository")
	private CustomerRepository repository;
	@Autowired
	private TransactionTemplate transactionTemplate;

	public CustomerServiceTxTemplate() {
	}
	
	public CustomerServiceTxTemplate(CustomerRepository repository) {
		this.repository = repository;
	}
	
	public void setCustomerRepository(CustomerRepository repository) {
		this.repository = repository;
	}

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
	public void deleteCustomer(final long id) {
		transactionTemplate.execute(new TransactionCallback<Void>() {
			@Override
			public Void doInTransaction(TransactionStatus txStatus) {
				try {
					if(id == 0) {
						List<CustomerEntity> customers = repository.findAll();
						for(CustomerEntity customer : customers)
							repository.delete(customer.getId());
					}
					else
						repository.delete(id);					
				}
				catch(Exception ex) {
					logger.info("삭제를 취소하고 롤백합니다!!");
					txStatus.setRollbackOnly();
				}
				return null;
			}			
		});
	}

}
