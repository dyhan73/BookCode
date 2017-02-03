package com.ensoa.order.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.ensoa.order.domain.Customer;
import com.ensoa.order.entity.CustomerEntity;

public class CustomerRepositoryImpl implements CustomerRepository {
	@Override
	public CustomerEntity findOne(long id) {
		CustomerEntity customer = new CustomerEntity();
		customer.setId(id);
		customer.setName("전병선");
		customer.setAddress("서울시");
		customer.setEmail("bsjun@ensoa.co.kr");
		return customer;
	}

	@Override
	public CustomerEntity findOneByName(String name) {
		CustomerEntity customer = new CustomerEntity();
		customer.setId(0);
		customer.setName(name);
		customer.setAddress("서울시");
		customer.setEmail("bsjun@ensoa.co.kr");
		return customer;
	}
	
	@Override
	public List<CustomerEntity> findAll() {
		List<CustomerEntity> customers = new ArrayList<CustomerEntity>();
		for(int i = 0; i < 10; ++i) {
			CustomerEntity customer = new CustomerEntity();
			customer.setId(i);
			customer.setName("이름"+i);
			customer.setAddress("주소"+i);
			customer.setEmail("kim"+i+"@gmail.com");
			customers.add(customer);
		}
		return customers;
	}
	
	@Override
	public List<CustomerEntity> findByName(String name) {
		if(name == null)
			return null;
		List<CustomerEntity> customers = new ArrayList<CustomerEntity>();
		for(int i = 0; i < 10; ++i) {
			CustomerEntity customer = new CustomerEntity();
			customer.setId(i);
			customer.setName(name+i);
			customer.setAddress("주소"+i);
			customer.setEmail("kim"+i+"@gmail.com");
			customers.add(customer);
		}
		return customers;
	}
	@Override
	public void save(CustomerEntity customer) {
		System.out.println(customer + "가 저장되었습니다.");
	}

	@Override
	public void delete(long id) {
		CustomerEntity customer = findOne(id);
		System.out.println(customer + "가 삭제되었습니다.");
	}

}
