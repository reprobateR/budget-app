package com.tlabs.budget.app.repository.impl;

import java.text.DecimalFormat;
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
	
		
	public Budget getExistingOrCreateBudget(Budget newBudget){
		
		String month = dataProcessor.getMonth();
		
		logger.info("Month Available " + month);
		
		Budget existingBudget = budgetValueRepository.findByMonth(dataProcessor.getMonth());
	
		if(existingBudget == null){
			newBudget.setMonth(month);
			budgetValueRepository.save(newBudget);
		} else if (existingBudget != null) {
			logger.info("Budget retrevied " + existingBudget);
			updateBudget(existingBudget, newBudget);
			newBudget.setId(existingBudget.getId());
			budgetValueRepository.save(newBudget);
		}
		
		return newBudget;
	}
	
	
	private void updateBudget(Budget existingBudget, Budget newBudget) {
		
		System.out.println("Existing Budget " + existingBudget);
		
		if(newBudget.getIncomeSum() != 0){
			int existingIncome = existingBudget.getIncomeSum();
			System.out.println("Existing Sum " + existingIncome);
			newBudget.setIncomeSum(existingIncome + newBudget.getIncomeSum());
			System.out.println("New income sum " + newBudget.getIncomeSum());
		}
		
		if(newBudget.getExpenseSum() != 0){
			int existingExpense = existingBudget.getExpenseSum();
			System.out.println("Existing expense " + existingExpense);
			newBudget.setExpenseSum(newBudget.getExpenseSum() + existingExpense);
			System.out.println("New income sum " + newBudget.getIncomeSum());
		}
		
		Double newExpensePercentage = (Double.valueOf(newBudget.getExpenseSum()) / Double.valueOf(newBudget.getIncomeSum())) * 100;
		
		System.out.println("calculated Double " + newExpensePercentage);
		
		DecimalFormat df=new DecimalFormat("0.00");
		String expPerStringToUpate = df.format(newExpensePercentage);
		System.out.println("ormatted Double String " + expPerStringToUpate);
		
		newBudget.setExpensePercentage(expPerStringToUpate);
		newBudget.setBudgetValue(newBudget.getIncomeSum() - newBudget.getExpenseSum());
		newBudget.setMonth(existingBudget.getMonth());
		
		System.out.println("New inal budget to save " + newBudget);
	
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
