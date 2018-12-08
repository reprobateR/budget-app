package com.tlabs.budget.app.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class BudgetAppAspect {
	
	@Before("execution(* com.tlabs.budget.app.controller.BudgetAppController.getTransactions(..))")
	public void loggingBeforeMethod(){		
		System.out.println("Testing Logging beore the method get transactions and add logging everuything");
	}

}
