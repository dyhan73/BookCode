package com.ensoa.order.model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.ensoa.order.domain.Customer;

public class CustomerModel {
	private long id;
	@Size(min=2, max=10, message="이름은 2자 이상 10자까지 입니다.")
	@Pattern(regexp="^[A-Za-z0-9가-힣]+$",  
		    message="공백문자 없이 숫자와 문자만 입력하세요.")
	private String name;
	@Size(max=60, message="주소는 60자까지 입력할 수 있습니다.")
	private String address;
	@NotBlank(message="이메일을 입력해주십시오.")
	@Email(message="정확한 이메일 주소를 입력해주십시오.")
	private String email;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String toString() {
		return "id : " + id + ", name : " + name + ", address : " + address + ", email : " + email;
	}
	public Customer buildDomain() {
		Customer customer = new Customer();
		customer.setId(id);
		customer.setName(name);
		customer.setAddress(address);
		customer.setEmail(email);
		return customer;
	}
	
	public void buildModel(Customer customer) {
		id = customer.getId();
		name = customer.getName();
		address = customer.getAddress();
		email = customer.getEmail();
	}
	@Password
	private String password;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}