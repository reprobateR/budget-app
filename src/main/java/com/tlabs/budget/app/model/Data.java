package com.tlabs.budget.app.model;

import java.util.List;

public class Data {

	private List<Item> expenses;
	private List<Item> incomes;

	public List<Item> getExpenses() {
		return expenses;
	}
	public void setExpenses(List<Item> expenses) {
		this.expenses = expenses;
	}
	public List<Item> getIncomes() {
		return incomes;
	}
	public void setIncomes(List<Item> incomes) {
		this.incomes = incomes;
	}

	@Override
	public String toString() {
		return "Data [expenses=" + expenses + ", incomes=" + incomes + "]";
	}

	public Data() {

	}

}
