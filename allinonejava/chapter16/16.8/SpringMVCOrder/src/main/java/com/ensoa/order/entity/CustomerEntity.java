package com.ensoa.order.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ensoa.order.domain.Customer;

@NamedQueries({
	@NamedQuery(
		name = "getCustomersByName",
		query = "select c from CustomerEntity c where c.name like :name"
	)
})
@Entity
@Table(name = "CUSTOMER")
public class CustomerEntity {
	@Id
	@GeneratedValue
	@Column(name="CUSTOMER_ID")
	private long id;
	private String name;
	private String address;
	private String email;
	@OneToMany(mappedBy = "customer")
	private Set<OrderEntity> orders;
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

	public Set<OrderEntity> getOrders() {
		return orders;
	}

	public void setOrders(Set<OrderEntity> orders) {
		this.orders = orders;
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
	
	public void buildEntity(Customer customer) {
		id = customer.getId();
		name = customer.getName();
		address = customer.getAddress();
		email = customer.getEmail();
	}

}
