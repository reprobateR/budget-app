package com.tlabs.budget.app.service;

import java.util.List;

import com.tlabs.budget.app.model.Record;

public interface BudgetService {
	
	public void saveAllTransactions(List<Record> recordList);
	
	public void saveCategory(String categoryName);

}
