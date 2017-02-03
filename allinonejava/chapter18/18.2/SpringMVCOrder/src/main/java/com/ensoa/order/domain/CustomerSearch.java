package com.ensoa.order.domain;

public class CustomerSearch {
	private int custId;
	private String name;
	private String address;
	private String email;
	
	public void setCustId(int custId) {
		this.custId = custId;
	}
	public int getCustId() {
		return custId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress() {
		return address;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
}
