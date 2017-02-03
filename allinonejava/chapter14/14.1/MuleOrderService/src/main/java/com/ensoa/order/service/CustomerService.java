package com.ensoa.order.service;

import java.util.List;

import com.ensoa.order.domain.Customer;

public interface CustomerService {
	Customer getCustomer(long id);
	Customer getCustomerByName(String name);
	List<Customer> getCustomers();
	List<Customer> getCustomersByName(String name);
	void saveCustomer(Customer customer);
	void updateCustomer(Customer customer);
	void deleteCustomer(long id);
}

