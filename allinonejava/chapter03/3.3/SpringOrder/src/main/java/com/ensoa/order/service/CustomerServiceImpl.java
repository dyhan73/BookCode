package com.ensoa.order.service;

import java.util.ArrayList;
import java.util.List;

import com.ensoa.order.domain.Customer;

public class CustomerServiceImpl  implements CustomerService {

	@Override
	public Customer getCustomer(long id) {
		Customer customer = new Customer();
		customer.setId(id);
		customer.setName("전병선");
		customer.setAddress("서울시");
		customer.setEmail("bsjun@ensoa.co.kr");
		return customer;
	}

	@Override
	public List<Customer> getCustomers() {
		List<Customer> customers = new ArrayList<Customer>();
		for(int i = 0; i < 10; ++i) {
			Customer customer = new Customer();
			customer.setId(i);
			customer.setName("이름"+i);
			customer.setAddress("주소"+i);
			customer.setEmail("kim"+i+"@gmail.com");
			customers.add(customer);
		}
		return customers;
	}

	@Override
	public List<Customer> getCustomersByPage(int index, int size) {
		List<Customer> customers = new ArrayList<Customer>();
		for(int i = index; i < index+size; ++i) {
			Customer customer = new Customer();
			customer.setId(i);
			customer.setName("이름"+i);
			customer.setAddress("주소"+i);
			customer.setEmail("kim"+i+"@gmail.com");
			customers.add(customer);
		}
		return customers;
	}

	@Override
	public void saveCustomer(Customer customer) {
		System.out.println(customer + "가 저장되었습니다.");	
	}

	@Override
	public void updateCustomer(Customer customer) {
		System.out.println(customer + "가 변경되었습니다.");		
	}

	@Override
	public void deleteCustomer(long id) {
		Customer customer = getCustomer(id);
		System.out.println(customer + "가 삭제되었습니다.");
	}

	public void init() {
		System.out.println("Initiate instance...");
	}

	public void cleanUp() {
		System.out.println("Destroy instance...");
	}

	private CustomerServiceImpl() {

	}

	private static class FactoryHolder{
		static CustomerService instance = new CustomerServiceImpl();
	}

	public static CustomerService getInstance() {
		return FactoryHolder.instance;
	}

}
