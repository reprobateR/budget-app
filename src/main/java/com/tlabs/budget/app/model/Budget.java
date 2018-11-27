package com.tlabs.budget.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "budget")
public class Budget {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;

	@Column(name="amount_remaining")
	private int budgetValue;

	@Column(name ="expense_percentage")
	private String expensePercentage;
    
	@Column(name ="income_sum")
	private int incomeSum;
	
	@Column(name ="expense_sum")
	private int expenseSum;

	@Column(name ="saving_percentage")
	private String savingPercentage;
	
	@Column(name = "month")
	private String month;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

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
		return "Budget [budgetValue=" + budgetValue + ", expensePercentage=" + expensePercentage + ", incomeSum="
				+ incomeSum + ", expenseSum=" + expenseSum + ", savingPercentage=" + savingPercentage + ", month="
				+ month + "]";
	}

	public Budget() {

	}

}
