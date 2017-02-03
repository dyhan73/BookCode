package com.ensoa.orderws;

import java.util.List;

import javax.xml.bind.JAXBElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.Namespace;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.server.endpoint.annotation.XPathParam;
import org.springframework.ws.soap.addressing.server.annotation.Action;
import org.springframework.ws.soap.server.endpoint.annotation.SoapAction;

import com.ensoa.order.service.OrderService;
import com.ensoa.order.service.schema.*;

@Endpoint
public class OrderServiceEndpoint {
	private static final Logger logger = LoggerFactory.getLogger(OrderServiceEndpoint.class);
	private final String NAMESPACE_URI = "http://service.order.ensoa.com/schema";
	private ObjectFactory objectFactory = new ObjectFactory();
	@Autowired
	private OrderService orderService;

	@PayloadRoot(localPart="getOrdersRequest", namespace=NAMESPACE_URI)
	@ResponsePayload
	public GetOrdersResponse getOrders(@RequestPayload GetOrdersRequest request) {
		logger.info("getOrders가 호출됨");
		GetOrdersResponse response = objectFactory.createGetOrdersResponse();
		Customer customer = request.getCustomer();	
		List<com.ensoa.order.domain.Order> orders =  orderService.getOrders(ConvertUtil.fromOxm(customer));
		for(com.ensoa.order.domain.Order order : orders)
			response.getReturn().add(ConvertUtil.fromDomain(order));
		return response; 
	}
	@PayloadRoot(localPart="getOrderRequest", namespace=NAMESPACE_URI)
	@Namespace(prefix = "s", uri=NAMESPACE_URI)
	@ResponsePayload
	public GetOrderResponse getOrder(@XPathParam("/s:getOrderRequest/s:id") long id) {  
		logger.info("getOrder가 호출됨");
		GetOrderResponse response = objectFactory.createGetOrderResponse();
		com.ensoa.order.domain.Order order = orderService.getOrder(id);
		response.setReturn(ConvertUtil.fromDomain(order));
		return response;
	}
	@PayloadRoot(localPart="purchaseOrderRequest", namespace=NAMESPACE_URI)
//	@Action("http://service.order.ensoa.com/schema/PurchaseOrderRequest")
//	@SoapAction("http://service.order.ensoa.com/schema/PurchaseOrderRequest")
	public void purchaseOrder(@RequestPayload PurchaseOrderRequest request) {
		logger.info("purchaseOrder가 호출됨");
		com.ensoa.order.domain.Order order = ConvertUtil.fromOxm(request.getOrder());
		orderService.purchaseOrder(order);
	}
}
