package com.ensoa.order.muleservice;

import java.io.Serializable;

public class RestCustomer implements Serializable {
	private long id;
	private String name;
	private String basicAddress;
	private String detailAddress;
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
	public String getBasicAddress() {
		return basicAddress;
	}
	public void setBasicAddress(String address) {
		this.basicAddress = address;
	}
	public String getDetailAddress() {
		return detailAddress;
	}
	public void setDetailAddress(String address) {
		this.detailAddress = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String toString() {
		return "id : " + id + ", name : " + name + ", address : " + basicAddress + " " + detailAddress + ", email : " + email;
	}


}
