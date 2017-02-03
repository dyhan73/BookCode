package com.ensoa.order.flow;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageContext;
import org.springframework.binding.validation.ValidationContext;
import org.springframework.stereotype.Component;
import org.springframework.webflow.execution.Event;

import com.ensoa.order.domain.Customer;
import com.ensoa.order.domain.OrderItem;
import com.ensoa.order.domain.Product;
import com.ensoa.order.model.CustomerModel;
import com.ensoa.order.model.OrderItemModel;
import com.ensoa.order.model.OrderModel;
import com.ensoa.order.model.ProductModel;
import com.ensoa.order.model.SelectedItem;
import com.ensoa.order.service.CustomerService;
import com.ensoa.order.service.OrderService;
import com.ensoa.order.service.ProductService;

@Component
public class OrderFlowAction {
	private static final Logger logger = LoggerFactory.getLogger(OrderFlowAction.class);
	@Autowired
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	@Autowired
	private OrderService orderService;
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	@Autowired
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}	
	
	public enum Result  {success, failure}
	
	public CustomerModel getCustomer(String name) throws CustomerNotFoundException {
		if("김일".equals(name))
			throw new CustomerNotFoundException("김일 고객이 없습니다.");
		Customer customer =  customerService.getCustomerByName(name);
		CustomerModel model = new CustomerModel();
		model.buildModel(customer);
		return model;
	}
	
	public boolean checkBlackList(String name) {
		if("홍길동".equals(name))
			return true;
		else
			return false;
	}
	/*
	public String addCustomer(CustomerModel customer) {
		logger.info(customer.getName() + " : " + customer.getAddress());
		try {
			customerService.saveCustomer(customer.buildDomain());
			return "success";
		}
		catch(Exception e) {
			return "failure";
		}
	}
	*/
	public Result addCustomer(CustomerModel customer) {
		logger.info(customer.getName() + " : " + customer.getAddress());
		try {
			customerService.saveCustomer(customer.buildDomain());
			return Result.success;
		}
		catch(Exception e) {
			return Result.failure;
		}
	}
	
	public List<ProductModel> getProducts() {
		List<Product> products =  productService.getProducts();
		List<ProductModel> productModels = convert(products);
		return productModels;
	}
	@RolesAllowed("ROLE_USER")
	public void purchaseOrder(OrderModel order) {
		orderService.purchaseOrder(order.buildDomain());
	}
	
	public OrderItemModel createOrderItem(SelectedItem item) {
		logger.info(item.getProduct() + " : " + item.getAmount() );
		OrderItemModel orderItem = new OrderItemModel();
		Product product = productService.getProduct(item.getProduct());
		ProductModel productModel = new ProductModel();
		productModel.buildModel(product);
		orderItem.setProduct( productModel);
		orderItem.setAmount(item.getAmount());
		return orderItem;
	}
	
	private List<ProductModel> convert(List<Product> products ) {
		List<ProductModel> productModels = new ArrayList<ProductModel>();
		for(Product product : products) {
			ProductModel productModel = new ProductModel();
			productModel.buildModel(product);
			productModels.add(productModel);
		}
		return productModels;
	}
}
