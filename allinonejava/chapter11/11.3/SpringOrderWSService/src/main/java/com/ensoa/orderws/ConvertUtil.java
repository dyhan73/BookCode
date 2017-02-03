package com.ensoa.orderws;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class ConvertUtil {
	public static com.ensoa.order.domain.Customer fromOxm(com.ensoa.order.service.schema.Customer oxm) {
		com.ensoa.order.domain.Customer domain = new com.ensoa.order.domain.Customer();
		domain.setId(oxm.getId());
		domain.setName(oxm.getName());
		domain.setAddress(oxm.getAddress());
		domain.setEmail(oxm.getEmail());
		return domain;
	}
	public static com.ensoa.order.service.schema.Customer fromDomain(com.ensoa.order.domain.Customer domain) {
		com.ensoa.order.service.schema.Customer oxm = new com.ensoa.order.service.schema.Customer();
		oxm.setId(domain.getId());
		oxm.setName(domain.getName());
		oxm.setAddress(domain.getAddress());
		oxm.setEmail(domain.getEmail());
		return oxm;
	}
	public static com.ensoa.order.domain.Product fromOxm(com.ensoa.order.service.schema.Product oxm) {
		com.ensoa.order.domain.Product domain = new com.ensoa.order.domain.Product();
		domain.setId(oxm.getId());
		domain.setName(oxm.getName());
		domain.setPrice(oxm.getPrice());
		domain.setDescription(oxm.getDescription());
		return domain;
	}
	public static com.ensoa.order.service.schema.Product fromDomain(com.ensoa.order.domain.Product domain) {
		com.ensoa.order.service.schema.Product oxm = new com.ensoa.order.service.schema.Product();
		oxm.setId(domain.getId());
		oxm.setName(domain.getName());
		oxm.setPrice(domain.getPrice());
		oxm.setDescription(domain.getDescription());
		return oxm;
	}
	public static com.ensoa.order.domain.OrderItem fromOxm(com.ensoa.order.service.schema.OrderItem oxm) {
		com.ensoa.order.domain.OrderItem domain = new com.ensoa.order.domain.OrderItem();
		domain.setId(oxm.getId());
		domain.setProduct(fromOxm(oxm.getProduct()));
		domain.setAmount(oxm.getAmount());
		return domain;
	}
	public static com.ensoa.order.service.schema.OrderItem fromDomain(com.ensoa.order.domain.OrderItem domain) {
		com.ensoa.order.service.schema.OrderItem oxm = new com.ensoa.order.service.schema.OrderItem();
		oxm.setId(domain.getId());
		oxm.setProduct(fromDomain(domain.getProduct()));
		oxm.setAmount(domain.getAmount());
		return oxm;
	}
	public static com.ensoa.order.domain.Order fromOxm(com.ensoa.order.service.schema.Order oxm) {
		com.ensoa.order.domain.Order domain = new com.ensoa.order.domain.Order();
		domain.setId(oxm.getId());
		List<com.ensoa.order.domain.OrderItem> orderItems = new ArrayList<com.ensoa.order.domain.OrderItem>();	
		for(com.ensoa.order.service.schema.OrderItem item : oxm.getItems()) {
			com.ensoa.order.domain.OrderItem oi = fromOxm(item);
			orderItems.add(oi);
		}
		domain.setItems(orderItems);
		/* SopaUI 테스트를 하려면 5 문장에 주석을 단 */ 
		int year = oxm.getOrderDate().getYear();
		int month = oxm.getOrderDate().getMonth();
		int day = oxm.getOrderDate().getDay();
		GregorianCalendar time = new GregorianCalendar(year, month,  day);	
		domain.setOrderDate(time.getTime());
//		domain.setOrderDate(new Date());
		
		return domain;
	}
	public static com.ensoa.order.service.schema.Order fromDomain( com.ensoa.order.domain.Order domain) {
		try {	
			com.ensoa.order.service.schema.Order oxm = new com.ensoa.order.service.schema.Order();
			oxm.setId(domain.getId());
			for(com.ensoa.order.domain.OrderItem item : domain.getItems()) {
				com.ensoa.order.service.schema.OrderItem oi = fromDomain(item);
				oxm.getItems().add(oi);
			}
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(domain.getOrderDate());
			XMLGregorianCalendar date;
			date = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
			oxm.setOrderDate(date);
			return oxm;
		} catch (DatatypeConfigurationException e) {
			return null;
		}
	}
}
