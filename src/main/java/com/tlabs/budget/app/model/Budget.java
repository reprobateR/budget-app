package com.tlabs.budget.app.model;

public class Budget {

	private int budgetValue;

	private String expensePercentage;

	private int incomeSum;

	private int expenseSum;

	private String savingPercentage;

	public String getSavingPercentage() {
		return savingPercentage;
	}

	public void setSavingPercentage(String savingPercentage) {
		this.savingPercentage = savingPercentage;
	}

	public int getBudgetValue() {
		return budgetValue;
	}

	public void setBudgetValue(int budgetValue) {
		this.budgetValue = budgetValue;
	}

	public String getExpensePercentage() {
		return expensePercentage;
	}

	public void setExpensePercentage(String expensePercentage) {
		this.expensePercentage = expensePercentage;
	}

	public int getIncomeSum() {
		return incomeSum;
	}

	public void setIncomeSum(int incomeSum) {
		this.incomeSum = incomeSum;
	}

	public int getExpenseSum() {
		return expenseSum;
	}

	public void setExpenseSum(int expenseSum) {
		this.expenseSum = expenseSum;
	}

	@Override
	public String toString() {
		return "Budget [budgetValue=" + budgetValue + ", expensePercentage="
				+ expensePercentage + ", incomeSum=" + incomeSum
				+ ", expenseSum=" + expenseSum + "]";
	}

	public Budget() {

	}

}
