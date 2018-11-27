package com.tlabs.budget.app.repository.impl;

import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tlabs.budget.app.model.Budget;
import com.tlabs.budget.app.processor.DataProcessor;
import com.tlabs.budget.app.repository.BudgetValueRepository;

@Repository
public class BudgetSheetRepository implements Supplier<Budget>{
	
	private static final Logger logger = LoggerFactory.getLogger(BudgetSheetRepository.class);
	
	@Autowired
	private BudgetValueRepository budgetValueRepository;
	
	@Autowired
	private DataProcessor dataProcessor;
	
		
	public Budget getExistingOrCreateBudget(Budget currentBudget){
		
		logger.info("Month Available " + dataProcessor.getMonth());
		
		Budget budget = budgetValueRepository.findByMonth(dataProcessor.getMonth());
		
		if(budget == null){
			budgetValueRepository.save(currentBudget);
		} else if (budget != null) {
			
			updateBudget(budget, currentBudget);
			
		}
		
		
		logger.info("Budget retrevied " + budget);
			
		return currentBudget;
		
	}
	
	
	private void updateBudget(Budget budget, Budget currentBudget) {
		
		
		
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


	@Override
	public Budget get() {


		return null;
	}
	
}
