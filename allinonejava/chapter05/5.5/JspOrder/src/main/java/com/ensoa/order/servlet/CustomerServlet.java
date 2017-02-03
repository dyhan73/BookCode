package com.ensoa.order.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensoa.order.domain.Customer;

@WebServlet(
		name="customerServlet",
		urlPatterns={"/customer"},
		loadOnStartup=1
	)
public class CustomerServlet extends HttpServlet {
	private String driverClassName = null;
	private String url = null;
	private String username = null;
	private String password = null;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println(config.getServletName() + " 서블릿이 시작되었습니다.");
	}
	@Override
	public void destroy() {
		System.out.println("서블릿이 종료되었습니다.");
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		List<Customer> customers = new ArrayList<Customer>();
		for(int i = 0; i < 10; ++i) {
			Customer customer = new Customer();
			customer.setName("이름"+i);
			customer.setAddress("주소" + i);
			customer.setEmail("email"+i+"@gmail.com");
			customers.add(customer);
		}
		request.setAttribute("customers", customers);
		Customer customer = new Customer();
		customer.setName("전병선");
		customer.setAddress("서울시");
		customer.setEmail("bsjun@enosa.co.kr");
		request.setAttribute("customer", customer);
		String url = "customer";
		url += request.getParameter("action");
		url += ".jsp";
		request.getRequestDispatcher(url) .forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String email = request.getParameter("email");
		Customer customer = new Customer();
		customer.setName(name);
		customer.setAddress(address);
		customer.setEmail(email);
		request.setAttribute("customer", customer);
		request.getRequestDispatcher("result.jsp") .forward(request, response);
	}
}
