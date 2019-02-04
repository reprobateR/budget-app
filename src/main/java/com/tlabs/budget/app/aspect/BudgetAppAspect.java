package com.tlabs.budget.app.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import com.tlabs.budget.app.controller.BudgetAppController;
import com.tlabs.budget.app.model.BudgetSheet;

@Aspect
@Configuration
public class BudgetAppAspect {

	private static final Logger logger = LoggerFactory.getLogger(BudgetAppController.class);

	private long startTime = 0L;
	private long endTime = 0L;
	
	@Pointcut("execution(* com.tlabs.budget.app.controller.BudgetAppController.getTransactions(..))")
	public void getTransactionJoin(){};
	
	@Before("getTransactionJoin()")
	public void loggingBeforeGetTransactions(JoinPoint joinPoint) {
		startTime = System.currentTimeMillis();
		logger.info("\n\n\n");
		logger.info("Started Async Thread::=  " + Thread.currentThread().getName());
		logger.info("Request Body::= " + (BudgetSheet) joinPoint.getArgs()[0]);
		logger.info("Begin execution of::= " + joinPoint.getSignature().getName());
	}

	@AfterReturning("getTransactionJoin()")
	public void loggingAfterGetTransactions(JoinPoint joinPoint, Object result) {
		endTime = System.currentTimeMillis();
		logger.info("Response Sent:= " + result);
		logger.info("Total time taken by thread::= " + Thread.currentThread().getName() + ": "
				+ (endTime - startTime / 1000));
		logger.info("Ending Async Thread::= " + Thread.currentThread().getName());
	}
	
	@AfterThrowing("getTransactionJoin()")
	public void loggingAfterGetTransactionException(JoinPoint joinPoint, Object result) {
		endTime = System.currentTimeMillis();
		logger.info("\nFailure Response Sent:= " + result);
		logger.info("Total time taken by thread::= " + Thread.currentThread().getName() + ": "
				+ (endTime - startTime / 1000));
		logger.info("Ending Async Thread::= " + Thread.currentThread().getName());
	}
	
	
	
}
