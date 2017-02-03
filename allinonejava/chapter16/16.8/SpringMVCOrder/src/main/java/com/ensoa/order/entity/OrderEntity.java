package com.ensoa.order.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ensoa.order.domain.Order;
import com.ensoa.order.domain.OrderItem;

@Entity
@Table(name="ORDERS")
public class OrderEntity {
	@Id 
	@GeneratedValue
	@Column(name="ORDER_ID")
	private long id;
	@Column(name="ORDER_DATE")
	private Date orderDate;
	@ManyToOne(cascade={CascadeType.ALL } )
	@JoinColumn(name="CUSTOMER_ID")
	private CustomerEntity customer;
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	@JoinColumn(name = "ORDER_ID")
	private Set<OrderItemEntity> items;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public CustomerEntity getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}
	public Set<OrderItemEntity> getItems() {
		return items;
	}
	public void setItems(Set<OrderItemEntity> items) {
		this.items = items;
	}
	public String toString() {
		String result =  "id : " + id + ", orderDate : " + orderDate + "\n";
		for(OrderItemEntity item : items)
			result  += item.toString() + "\n";
		return result;
	}
	public Order buildDomain() {
		Order order = new Order();
		order.setId(id);
		order.setOrderDate(orderDate);
		order.setCustomer(customer.buildDomain());
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		for(OrderItemEntity item : items)
			orderItems.add(item.buildDomain());
		order.setItems(orderItems);
		return order;
	}
	public void buildEntity(Order order) {
		id =  order.getId();
		orderDate = order.getOrderDate();
		customer = new CustomerEntity();
		customer.buildEntity(order.getCustomer());
		items = new HashSet<OrderItemEntity>();
		for(OrderItem orderItem : order.getItems()) {
			OrderItemEntity item = new OrderItemEntity();
			item.buildEntity(orderItem);
			items.add(item);
		}
	}
}
