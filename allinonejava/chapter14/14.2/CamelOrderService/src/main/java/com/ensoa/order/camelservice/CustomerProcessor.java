package com.ensoa.order.camelservice;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.ensoa.order.domain.Customer;

public class CustomerProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		Customer customer = (Customer)exchange.getIn().getBody();
		System.out.println(customer);

	}

}
