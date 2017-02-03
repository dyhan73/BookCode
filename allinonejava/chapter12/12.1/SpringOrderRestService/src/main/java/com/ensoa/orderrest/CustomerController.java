package com.ensoa.orderrest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ensoa.order.domain.Customer;
import com.ensoa.order.service.CustomerService;


@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value="/Customer/{id}", method=RequestMethod.GET)
	public Customer getCustomer(@PathVariable("id") long id) {
		return customerService.getCustomer(id);
	}
	
	@RequestMapping(value="/Customer", method=RequestMethod.GET, produces="application/json")
	public Customer getCustomerByName(@RequestParam("name") String name) {
		return customerService.getCustomerByName(name);
	}

	@RequestMapping(value="/Customers", method=RequestMethod.GET)
	public List<Customer> getCustomers() {
		return customerService.getCustomers();
	}
	
	@RequestMapping(value="/Customers/{name}", method=RequestMethod.GET)
	public List<Customer> getCustomersByName(@PathVariable("name") String name) {
		return customerService.getCustomersByName(name);
	}
	
	@RequestMapping(value="/Customer", method=RequestMethod.POST, consumes="application/json")
	//@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody Customer customer) {
		try {
			customerService.saveCustomer(customer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/Customer", method=RequestMethod.PUT)
	//@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@RequestBody Customer customer) {
		customerService.updateCustomer(customer);
	}
	
	@RequestMapping(value="/Customer/{id}", method=RequestMethod.DELETE)
	//@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") int id) {
		customerService.deleteCustomer(id);
	}
}
