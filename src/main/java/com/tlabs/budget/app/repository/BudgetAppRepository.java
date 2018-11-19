package com.tlabs.budget.app.repository;

import com.tlabs.budget.app.model.Data;

public interface BudgetAppRepository {

	public void saveTransactions(Data data);

	public void getMonthlyBudgetSheet();

	public void saveOrUpdateBudgetSheet();

}
