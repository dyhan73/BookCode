package com.ensoa.order.muleservice;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.ensoa.order.domain.Customer;
import com.ensoa.order.service.CustomerService;

@Path("/customers")
public class CustomerRestService {
	private CustomerService service;
	public void setService(CustomerService service) {
		this.service = service;
	}
	@GET
	public List<Customer> getCustomers() {
		return service.getCustomers();
	}
	@POST
	public Customer saveCustomer(Customer customer) {
		return customer;	
	}
}
