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
import com.tlabs.budget.app.model.Category;
import com.tlabs.budget.app.model.Charts;
import com.tlabs.budget.app.model.Data;
import com.tlabs.budget.app.model.Item;
import com.tlabs.budget.app.processor.DataProcessor;
import com.tlabs.budget.app.processor.GraphResponse;
import com.tlabs.budget.app.repository.BudgetAppRepository;
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
       
		// TODO: Create a distinct list of expenses, with expense values added.And the percentages calculated.
		
        String text = "Expense Values Of Current Month";
        Charts barChart = graphResponse.createChartData("bar", expenseList, "Expense Values", text);
	
		return null;
	}

}
