package com.ensoa.order.service;

import java.util.List;

import com.ensoa.order.domain.Customer;

public interface CustomerService {
	Customer getCustomer(long id);
	List<Customer> getCustomers();
	List<Customer> getCustomersByPage(int index, int size);
	void saveCustomer(Customer customer);
	void updateCustomer(Customer customer);
	void deleteCustomer(long id);
}
