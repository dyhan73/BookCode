package com.ensoa.order.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.ensoa.order.domain.Customer;
import com.ensoa.order.formatter.DateFormat;

public class CustomerModel implements Serializable {
	private long id;
	@NotNull
	@Size(min=2, max=10, message="{validate.customer.name}")
	@Pattern(regexp="^[A-Za-z0-9가-힣]+$",  
		    message="{validate.customer.name.pattern}")
	private String name;
	@Size(max=60, message="{validate.customer.address}")
	private String address;
	/*
	@Pattern(regexp="[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}",  
		    message="{validate.customer.email.correct}")
	*/
	@NotBlank(message="{validate.customer.email}")
	@Email(message="{validate.customer.email.correct}")
	private String email;
	/*
	//@DateTimeFormat(pattern="yyyy-MM-dd")	
	@DateFormat(format="yyyy-MM-dd")
	private Date date;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	*/
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Valid
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Valid
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Valid
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
}
