package com.ensoa.order.controller.customer;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ensoa.order.domain.Customer;
import com.ensoa.order.model.CustomerCondition;
import com.ensoa.order.model.CustomerModel;
import com.ensoa.order.service.CustomerService;

@Controller
@RequestMapping(value="/")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value="/edit.do", method=RequestMethod.GET)
	/*
	public ModelAndView edit() {
		ModelAndView model = new ModelAndView();
		CustomerModel customer = new CustomerModel();
		model.addObject("customer", customer);
		model.setViewName("edit");
		return model;
	}
	*/
	public String edit(Model model) {
		CustomerModel customer = new CustomerModel();
		model.addAttribute("customer", customer);
		return "edit";
	}
	@RequestMapping(value="/edit.do", method=RequestMethod.POST)
	public String add(@Valid @ModelAttribute("customer") CustomerModel model, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "edit";
		}
		try {
			customerService.saveCustomer(model.buildDomain());
		}
		catch(Exception e) {
			return "forward:/error.do";
		}
		return "result";
	}
	@RequestMapping(value="/list.do", method=RequestMethod.GET)
	@ModelAttribute("customers")
	public List<CustomerModel> list() {
		List<Customer> customers = customerService.getCustomers();
		List<CustomerModel> customerModels = convert(customers);
		return customerModels;
	}
	@RequestMapping(value = "/error.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String error(Model model) {
		return "error";
	}
	@RequestMapping(value="/search.do", method=RequestMethod.GET)
	@ModelAttribute("customers")
	public List<CustomerModel> search(@ModelAttribute("customerCondition") 
		CustomerCondition customerCondition) {
		if(customerCondition.getName() == null)
			return null;
		List<Customer> customers = customerService.getCustomersByName(customerCondition.getName());
		List<CustomerModel> customerModels = convert(customers);
		return customerModels;
	}
	private List<CustomerModel> convert(List<Customer> customers ) {
		List<CustomerModel> customerModels = new ArrayList<CustomerModel>();
		for(Customer customer : customers) {
			CustomerModel customerModel = new CustomerModel();
			customerModel.buildModel(customer);
			customerModels.add(customerModel);
		}
		return customerModels;
	}
}