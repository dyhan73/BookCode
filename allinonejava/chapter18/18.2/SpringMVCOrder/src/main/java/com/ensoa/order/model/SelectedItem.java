package com.ensoa.order.model;

import java.io.Serializable;

public class SelectedItem  implements Serializable{
	private int product;
	public void setProduct(int product) {
		this.product = product;
	}
	public int getProduct() {
		return product;
	}
	private int amount;
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getAmount() {
		return amount;
	}
}
