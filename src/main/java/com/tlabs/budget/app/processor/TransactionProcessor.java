package com.tlabs.budget.app.processor;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.tlabs.budget.app.exception.RequestException;
import com.tlabs.budget.app.model.Record;
import com.tlabs.budget.app.model.Transaction;

@Component
public class TransactionProcessor {

	private List<String> expenseCategories;
	
	public void setExpenseCategories(List<String> expenseCategories) {
		this.expenseCategories = expenseCategories;
	}

	public List<Record> validateAndMapTransactions(List<Transaction> tnxList) {
		List<Record> recordList = new ArrayList<>();
		for (Transaction tnx : tnxList) {
			validateTransaction(tnx);
			Float tnxValue = formatNumberInput(tnx.getValue());
    		Record record = createRecord(tnx, tnxValue);
    		recordList.add(record);
		}
		return recordList;
	}

	private Record createRecord(Transaction tnx, Float tnxValue) {
		
		String type = tnx.getType(), tnxName = tnx.getName();
		
		Record record = new Record();
		record.setType(type);
		record.setName(tnxName);
		record.setValue(tnxValue);
		
		LocalDate localDate = LocalDate.now();
		record.setTimestamp(Calendar.getInstance().getTime());
		record.setMonth(localDate.getMonth().name());
		return record;
	}

	private void validateTransaction(Transaction tnx) {
		String type = tnx.getType();
		if (!type.equals("expense") && !type.equals("income")) {
			throw new RequestException("Invalid Transaction Type:: " + type);
		}
		if (type.equals("expense") && !expenseCategories.contains(tnx.getName())) {
			throw new RequestException("Invalid Category Name:: " + tnx.getName());
		}
	}
	
	public void validateMonth(String month) {
		Set<String> monthSet = new HashSet<>();
		for(Month monthVal: Month.values()) {
			monthSet.add(monthVal.name());
		}
		if(!monthSet.contains(month)) {
			throw new RequestException("Invalid Month Sent In Request");
		}
	}
	
	public Float formatNumberInput(String value) {
		Float outputValue = 0F;
		try {
			String output = value.trim().substring(2).replaceAll(",", "");
			outputValue = Float.parseFloat(output);
		} catch (NumberFormatException ex) {
			throw new RequestException("Invalid Transaction Value or Format " + value);
		}
		return outputValue;
	}

}
