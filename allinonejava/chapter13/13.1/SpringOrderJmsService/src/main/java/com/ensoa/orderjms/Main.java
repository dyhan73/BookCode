package com.ensoa.orderjms;

import java.io.IOException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsMessagingTemplate;


public class Main {

	public static void main(String[] args) throws IOException {
		new ClassPathXmlApplicationContext("classpath:beans.xml");
//		new AnnotationConfigApplicationContext(AppConfig.class);
		System.out.println("서비스가 실행 중입니다.....");	
		System.in.read();
	}

}
