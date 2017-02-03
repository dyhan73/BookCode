package com.ensoa.order.muleservice;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;

import com.ensoa.order.domain.Customer;
import com.sun.jersey.spi.container.ContainerResponse;

public class JerseyResponseTransformer extends AbstractMessageTransformer {

	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding)
			throws TransformerException {
	    ContainerResponse cr = (ContainerResponse) message.getInvocationProperty("jersey_response");
//	    RestCustomer customer = (RestCustomer) cr.getResponse().getEntity();
	    Customer customer = (Customer) cr.getResponse().getEntity();
	    message.setPayload(customer); 
	    return message;
	}
}
