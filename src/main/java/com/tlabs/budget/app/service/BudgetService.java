package com.tlabs.budget.app.service;

import java.util.List;
import java.util.Set;

import com.tlabs.budget.app.model.Category;
import com.tlabs.budget.app.model.Record;

public interface BudgetService {
	
	public void saveAllTransactions(List<Record> recordList);
	
	public void saveCategory(String categoryName);
	
	public Set<Category> getAllCategories();
	
	public List<Record> getAllRecordsByMonth(String month);

}
