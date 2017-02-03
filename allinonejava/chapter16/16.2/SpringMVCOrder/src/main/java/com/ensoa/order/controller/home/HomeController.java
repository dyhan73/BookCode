package com.ensoa.order.controller.home;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/error.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String error(Model model) {
		return "error";
	}
	
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public void login() {
		logger.info("로그인...");
	}
	
	@RequestMapping(value = "/accessdenied.do", method = RequestMethod.GET)
	public void accessdenied() {
		logger.info("접근 거부됨...");
	}
	
	@RequestMapping("/admin.do")
	public void admin() {
		logger.info("관리자용....");
	}	
}
