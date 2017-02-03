package com.ensoa.order.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ensoa.order.domain.OrderItem;

@Entity
@Table(name="ORDER_ITEM")
public class OrderItemEntity {
	@Id 
	@GeneratedValue
	@Column(name="ORDER_ITEM_ID")
	private long id;
	private int amount;
	@ManyToOne
	@JoinColumn(name="PRODUCT_ID")
	private ProductEntity product;
//	private long orderId;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public ProductEntity getProduct() {
		return product;
	}
	public void setProduct(ProductEntity product) {
		this.product = product;
	}
	/*
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	*/
	public String toString() {
		return "id : " + id + ", amount : " + amount + ", product : " + product;
	}
	public OrderItem buildDomain() {
		OrderItem item = new OrderItem();
		item.setId(id);
		item.setAmount(amount);
		item.setProduct(product.buildDomain());
		return item;
	}
	public void buildEntity(OrderItem item) {
		id =  item.getId();
		amount = item.getAmount();
		product = new ProductEntity();
		product.buildEntity(item.getProduct());
	}
}
