package com.tlabs.budget.app.model;

public class BudgetSheet {

	private Data data;

	private Budget budget;

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public Budget getBudget() {
		return budget;
	}

	public void setBudget(Budget budget) {
		this.budget = budget;
	}

	@Override
	public String toString() {
		return "BudgetSheet [data=" + data + ", budget=" + budget + "]";
	}

	public BudgetSheet() {

	}

}
