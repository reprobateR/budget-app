package com.tlabs.budget.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BudgetLoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(BudgetLoginController.class);
	
	@GetMapping("/home")
	public String homePage() {
		
		logger.info("Navigation to budget home");
		
		return "home";
	}
	
	@GetMapping("/displayLoginPage")
	public String displayLoginPage() {
		
		logger.info("Spring Security Interceptor filter show budget login page");
		
		return "login-home";
	}
	
	@GetMapping("/output")
	public String displayIndicators() {
		
		logger.info("Navigation To Output Page");
		
		return "output";
	}

}
