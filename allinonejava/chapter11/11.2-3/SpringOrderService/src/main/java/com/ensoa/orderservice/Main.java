package com.ensoa.orderservice;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
//		new ClassPathXmlApplicationContext("classpath:beans.xml");
		new AnnotationConfigApplicationContext(WebServiceConfig.class);
		System.out.println("서비스가 실행 중입니다.....");	
	}

}
