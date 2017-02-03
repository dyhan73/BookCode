package com.ensoa.order.service;

import java.util.ArrayList;
import java.util.List;

import com.ensoa.order.domain.Customer;

public interface CustomerService {
	Customer getCustomer(long id);
	Customer getCustomerByName(String name);
	List<Customer> getCustomers();
	List<Customer> getCustomersByName(String name);
	List<Customer> getCustomersByPage(int index, int size);
	void saveCustomer(Customer customer) throws Exception;
	void updateCustomer(Customer customer);
	void deleteCustomer(long id);
}

