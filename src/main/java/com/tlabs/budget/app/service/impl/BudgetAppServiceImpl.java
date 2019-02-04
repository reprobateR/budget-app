package com.tlabs.budget.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlabs.budget.app.exception.BudgetAppException;
import com.tlabs.budget.app.model.Budget;
import com.tlabs.budget.app.model.Category;
import com.tlabs.budget.app.model.Charts;
import com.tlabs.budget.app.model.Data;
import com.tlabs.budget.app.model.Item;
import com.tlabs.budget.app.processor.DataProcessor;
import com.tlabs.budget.app.processor.GraphResponse;
import com.tlabs.budget.app.repository.BudgetAppRepository;
import com.tlabs.budget.app.repository.BudgetValueRepository;
import com.tlabs.budget.app.repository.CategoryRepository;
import com.tlabs.budget.app.service.BudgetAppService;

@Service
public class BudgetAppServiceImpl implements BudgetAppService {

	private static final Logger logger = LoggerFactory.getLogger(BudgetAppService.class);

	@Autowired
	private DataProcessor dataProcessor;

	@Autowired
	private BudgetAppRepository budgetAppRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private GraphResponse graphResponse;
	
	@Autowired
	private BudgetValueRepository budgetValueRepository;

	@Override
	@Async
	@Transactional
	public void saveTransactions(Data data) throws BudgetAppException {

		logger.info("Save transaction task thread " + Thread.currentThread().getName());

		Data updatedData = dataProcessor.createTransactionData(data);

		logger.info("updated data before insert" + updatedData);

		budgetAppRepository.saveAll(updatedData.getExpenses());
		budgetAppRepository.saveAll(updatedData.getIncomes());

		logger.info("Complete  - saveTransactions ");

	}
	
	@Override
	@Async
	@Transactional
	public void saveOrUpdateBudget(Budget newBudget) {

		String month = dataProcessor.getMonth();

		logger.info("Month Available " + month);

		Budget existingBudget = budgetValueRepository.findByMonth(dataProcessor.getMonth());

		if (existingBudget == null) {
			newBudget.setMonth(month);
			budgetValueRepository.save(newBudget);
		} else if (existingBudget != null) {
			logger.info("Budget retrieved " + existingBudget);
			updateBudget(existingBudget, newBudget);
			newBudget.setId(existingBudget.getId());
			budgetValueRepository.save(newBudget);
		}
	
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
	
    @Override
    @Cacheable("categoryList")
	public List<Category> loadCategoryCache() {
    	
    	logger.info("Load Expense Description Category");
    	
    	List<Category> categoryList = new ArrayList<>();
		
    	categoryRepository.findAll().forEach(cat -> categoryList.add(cat));
    	categoryList.forEach(System.out::println);
		
    	return categoryList;
	}


	@Override
	public List<Item> getTransactions() {
		
		String currentMonth = dataProcessor.getMonth();
		
		logger.info("Retrieve Expense List for current month " + currentMonth);
		
		List<Item> expenseList = budgetAppRepository.findExpenseListByMonth("expense", currentMonth);
		
		logger.info("Printing Expense List Retreived: ");
		expenseList.forEach(System.out::println);
		
		return expenseList;
	}


	@Override
	public List<Charts> createGraphResponse(List<Item> expenseList) {
		
		logger.info("Create Graph Response Begins");
		
		List<Charts> chartsList = new ArrayList<>();
		
		Budget budget = budgetValueRepository.findByMonth(dataProcessor.getMonth());
		Integer totalIncomeSum = budget.getIncomeSum();
   
		// TODO: Create a distinct list of expenses, with expense values added.And the percentages calculated.
		List<Item> chartItemValues = dataProcessor.distinctExpenseCategorization(expenseList,totalIncomeSum);
		
        String barText = "Expense Values Of Current Month";
        Charts barChart = graphResponse.createChartData("bar", chartItemValues, "Expense Values", barText);
        
        String pieText = "Expense Percentages Of Current Month";
        Charts pieChart = graphResponse.createChartData("pie", chartItemValues, "Expense Values", pieText);
        
        chartsList.add(barChart);
        chartsList.add(pieChart);
	
		return chartsList;
	}


}
