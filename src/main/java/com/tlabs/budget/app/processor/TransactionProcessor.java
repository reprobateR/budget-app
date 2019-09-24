package com.tlabs.budget.app.processor;

import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Component;

import com.tlabs.budget.app.exception.RequestException;
import com.tlabs.budget.app.model.Record;
import com.tlabs.budget.app.model.Transaction;

@Component
public class TransactionProcessor {

	private static List expenseCategories = Arrays.asList("Rent", "CreditCardBill");

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
		
		//TODO Month Logic Is Wrong
		Calendar calendar = Calendar.getInstance();
		record.setTimestamp(calendar.getTime());
		record.setMonth(Month.of(calendar.get(Calendar.MONTH)));
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
