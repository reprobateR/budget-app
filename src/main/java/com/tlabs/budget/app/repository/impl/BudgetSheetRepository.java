package com.tlabs.budget.app.repository.impl;

import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.tlabs.budget.app.model.Budget;

@Repository
public class BudgetSheetRepository implements Supplier<Budget>{
	
	private static final Logger logger = LoggerFactory.getLogger(BudgetSheetRepository.class);

	@Override
	public Budget get() {
		
		logger.info("Thread Name " + Thread.currentThread().getName());
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
     		e.printStackTrace();
		}

		return new Budget();
	}
	
	public void saveOrUpdateBudgetSheet(Budget budget) {

		logger.info("saveOrUpdateBudgetSheet task thread " + Thread.currentThread().getName());

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		logger.info("Complete saveOrUpdateBudgetSheet " + Thread.currentThread().getName());
	}
	
}
