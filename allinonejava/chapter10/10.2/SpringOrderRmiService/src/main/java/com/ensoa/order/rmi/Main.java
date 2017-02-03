package com.ensoa.order.rmi;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
//		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:beans.xml");
		new AnnotationConfigApplicationContext(RmiServiceConfig.class);
		System.out.println("서비스가 실행 중입니다.....");	
	}

}
