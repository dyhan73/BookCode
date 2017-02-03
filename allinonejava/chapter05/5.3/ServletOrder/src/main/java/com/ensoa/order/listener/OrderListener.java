package com.ensoa.order.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class OrderListener implements ServletContextListener {
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("서블릿 컨텍스트가 생성되었습니다.");
	}
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("서블릿 컨텍스트가 소멸되었습니다.");
	}
}
