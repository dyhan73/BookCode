package com.ensoa.order.camelservice;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ensoa.order.domain.Customer;
import com.ensoa.order.service.CustomerService;

@Path("/customers")
@Produces("application/json")
public class CustomerRestService {
	@GET
	public Response getCustomers() {
		return null;
	}
	@POST
	@Consumes("application/json")
	public  Response saveCustomer(Customer customer) {
		return null;
	}
}
