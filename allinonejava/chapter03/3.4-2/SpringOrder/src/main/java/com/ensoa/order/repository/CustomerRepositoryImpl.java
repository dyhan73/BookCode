package com.ensoa.order.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.ensoa.order.entity.CustomerEntity;
import com.ensoa.order.entity.Pageable;

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
	public List<CustomerEntity> findAll(Pageable page) {
		List<CustomerEntity> customers = new ArrayList<CustomerEntity>();
		int index = page.getIndex();
		int size = page.getSize();
		for(int i = index; i < index+size; ++i) {
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
	public void save(CustomerEntity customer) {
		System.out.println(customer + "가 저장되었습니다.");
		displayProperties();
	}
	@Override
	public void delete(long id) {
		CustomerEntity customer = findOne(id);
		System.out.println(customer + "가 삭제되었습니다.");
	}
	private String driverClassName;
	private String url;
	private String username;
	private String password;
	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password =  password;
	}
	private Properties properties;
	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	private Map<String, String> map;
	public void setMap(Map<String, String> map) {
		this.map = map;
	}
	private List<String> list;
	public void setList(List<String> list) {
		this.list = list;
	}
	private void displayProperties() {
		System.out.println(buildConnectionString(driverClassName, url, username, password));
		System.out.println(buildConnectionString(
				properties.getProperty("driverClassName"),
				properties.getProperty("url"),
				properties.getProperty("username"),
				properties.getProperty("password")));
		System.out.println(buildConnectionString(
				map.get("driverClassName"),
				map.get("url"),
				map.get("username"),
				map.get("password")));
		for(String str : list){
			System.out.println(str);
		}
	}
	private String buildConnectionString(String driverClassName, String url, String username, String password) {
		return "driverClassName : " + driverClassName + ", url : " + url + ", username : " + username + ", password : " + password;
	}
}