package com.ensoa.order.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.ensoa.order.domain.Customer;

@Service("customerService")
public class CustomerRSServiceImpl implements CustomerService {
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public Customer getCustomer(long id) {
		String uri = "http://localhost:8080/orderrest/customer/Customer/{id}";
		return restTemplate.getForObject(uri, Customer.class, id); 
	}
	

	@Override
	public Customer getCustomerByName(String name) {
		String uri = "http://localhost:8080/orderrest/customer/Customer?name=";
		return restTemplate.getForObject(uri+name, Customer.class); 
	}

	@Override
	public List<Customer> getCustomers() {
		String uri = "http://localhost:8080/orderrest/customer/Customers";
		Customer[] list = restTemplate.getForObject(uri, Customer[].class);
		return Arrays.asList(list);
	} 
	
	@Override
	public List<Customer> getCustomersByName(String name) {
		String uri = "http://localhost:8080/orderrest/customer/Customers/{name}";
		Customer[] list = restTemplate.getForObject(uri, Customer[].class, name);
		return Arrays.asList(list);
	}

	@Override
	public List<Customer> getCustomersByPage(int index, int size) {
		List<Customer> customers = new ArrayList<Customer>();
		return customers;
	}

	@Override
	public void saveCustomer(Customer customer) {
		String uri = "http://localhost:8080/orderrest/customer/Customer";
		restTemplate.postForObject(uri, customer, Customer.class); 
	}

	@Override
	public void updateCustomer(Customer customer) {
		String uri = "http://localhost:8080/orderrest/customer/Customer";
		restTemplate.put(uri, customer); 
	}

	@Override
	public void deleteCustomer(long id) {
		String uri = "http://localhost:8080/orderrest/customer/Customer/{id}";
		restTemplate.delete(uri, id); 
	}

}
