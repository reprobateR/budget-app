package com.tlabs.budget.app.repository.impl;

import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tlabs.budget.app.model.Budget;
import com.tlabs.budget.app.processor.DataProcessor;
import com.tlabs.budget.app.repository.BudgetValueRepository;

@Service
public class BudgetSheetService implements Function<Budget, Budget> {

	private static final Logger logger = LoggerFactory.getLogger(BudgetSheetService.class);

	@Autowired
	private BudgetValueRepository budgetValueRepository;

	@Autowired
	private DataProcessor dataProcessor;

	@Override
	public Budget apply(Budget newBudget) {

		String month = dataProcessor.getMonth();

		logger.info("Month Available " + month);

		Budget existingBudget = budgetValueRepository.findByMonth(dataProcessor.getMonth());

		if (existingBudget == null) {
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

		Integer existingIncomeSum = existingBudget.getIncomeSum();
		Integer existingExpenseSum = existingBudget.getExpenseSum();
		Integer newIncomeSum = newBudget.getIncomeSum();
		Integer newExpenseSum = newBudget.getExpenseSum();

		Integer finalIncomeSum = 0;
		Integer finalExpenseSum = 0;

		if (newIncomeSum != 0) {
			newBudget.setIncomeSum(existingIncomeSum + newIncomeSum);
			finalIncomeSum = newBudget.getIncomeSum();
		} else {
			finalIncomeSum = existingIncomeSum;
		}
		if (newBudget.getExpenseSum() != 0) {
			newBudget.setExpenseSum(newExpenseSum + existingExpenseSum);
			finalExpenseSum = newBudget.getExpenseSum();
		} else {
			finalExpenseSum = existingExpenseSum;
		}

		String expPerStringToUpate = 
				dataProcessor.calculateTotalPercentage(finalExpenseSum, finalIncomeSum);
		newBudget.setIncomeSum(finalIncomeSum);
		newBudget.setExpenseSum(finalExpenseSum);
		newBudget.setExpensePercentage(expPerStringToUpate);
		newBudget.setBudgetValue(finalIncomeSum - finalExpenseSum);
		newBudget.setMonth(existingBudget.getMonth());
	}

}
