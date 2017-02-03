package com.ensoa.order.controller.customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;

import com.ensoa.order.domain.Customer;
import com.ensoa.order.model.CustomerCondition;
import com.ensoa.order.model.CustomerModel;
import com.ensoa.order.service.CustomerService;

@Secured("ROLE_USER")
@Controller
@RequestMapping(value="/")
public class CustomerController {
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	@Autowired
	private CustomerService customerService;
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private LocaleResolver localeResolver;
	
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
	public String edit(Model model, 
			HttpServletRequest request, 
			HttpServletResponse response) {
//			Locale locale = new Locale(request.getParameter("lang"));
//			localeResolver.setLocale(request,  response,  locale);
			CustomerModel customer = new CustomerModel();
			model.addAttribute("customer", customer);
			return "edit";
	}
	
	@RequestMapping(value="/edit.do", method=RequestMethod.POST)
	public String add(@Valid @ModelAttribute("customer") CustomerModel model, BindingResult bindingResult, Locale locale) {
		if(bindingResult.hasErrors()) {
			return "edit";
		}
		try {
			String message = messageSource.getMessage("customer.enroll",  
					new Object[] { 
					model.getName(), model.getAddress(), model.getEmail()
					}, locale);
			logger.info(message);
			customerService.saveCustomer(model.buildDomain());
		}
		catch(Exception e) {
			return "forward:/error.do";
		}
		return "result";
	}
	
	@Secured("ROLE_MANAGER")
	@RequestMapping(value="/list.do", method=RequestMethod.GET)
	@ModelAttribute("customers")
	public List<CustomerModel> list() {
		List<Customer> customers = customerService.getCustomers();
		List<CustomerModel> customerModels = convert(customers);
		return customerModels;
	}
	
	@Secured("ROLE_MANAGER")
	@RequestMapping(value = "/error.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String error(Model model) {
		return "error";
	}
	
	@Secured({"ROLE_USER", "ROLE_MANAGER"})
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