package com.tlabs.budget.app.service;

import java.util.List;

import com.tlabs.budget.app.exception.BudgetAppException;
import com.tlabs.budget.app.model.Category;
import com.tlabs.budget.app.model.Charts;
import com.tlabs.budget.app.model.Data;
import com.tlabs.budget.app.model.Item;

public interface BudgetAppService {

	public void saveTransactions(Data data) throws BudgetAppException;
	
	public List<Category> loadCategoryCache();
	
	public List<Item> getTransactions();
	
	public List<Charts> createGraphResponse(List<Item> expenseList);
	
}
