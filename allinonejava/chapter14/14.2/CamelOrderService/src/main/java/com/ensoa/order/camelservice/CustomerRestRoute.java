package com.ensoa.order.camelservice;

import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.cxf.common.message.CxfConstants;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;

import com.ensoa.order.domain.Customer;

public class CustomerRestRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		/**/
		from("cxfrs://bean://rsServer?bindingStyle=SimpleConsumer")
		.choice()
//			.when(header("operationName").isEqualTo("saveCustomer"))
			.when(simple("${header.operationName} == 'saveCustomer'"))
				.to(ExchangePattern.InOnly, "jms:queue:customer.queue")
			.otherwise()
				.to("bean:customerService?method=getCustomers")
			.end()
	//		.marshal().json(JsonLibrary.Jackson)
			.log("LOG : ${body}");
		from("jms:queue:customer.queue")
			.to("bean:customerService?method=saveCustomer")
			.log("LOG : ${body}");
		/* */
		/*
		from("cxfrs:bean:rsServer?bindingStyle=SimpleConsumer")
			.recipientList(simple("direct:${header.operationName}"));
		from("direct:getCustomers")
			.to("bean:customerService?method=getCustomers")
			.marshal().json(JsonLibrary.Jackson)
			.log("LOG : ${body}");
		from("direct:saveCustomer")
			.to("bean:customerService?method=saveCustomer")
			.marshal().json(JsonLibrary.Jackson)
			.log("LOG : ${body}");
		*/
		/* OK!!! It WORKS!!! with CameServlet in web.xml 
		restConfiguration().component("servlet").bindingMode(RestBindingMode.json)
           .dataFormatProperty("prettyPrint", "true");
		rest("/customers")
			.consumes("application/json").produces("application/json")
			.get("/").outType(Customer.class)
   				.to("bean:customerService?method=getCustomers")
   				.to("log:TEST?showAll=true")
   			.post().type(Customer.class)
   				.to("bean:customerService?method=saveCustomer")
   				.to("log:TEST?showAll=true");
       */
	}
}
