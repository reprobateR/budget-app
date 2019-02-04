package com.tlabs.budget.app.processor;

import java.text.DecimalFormat;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.sun.istack.internal.logging.Logger;
import com.tlabs.budget.app.model.Data;
import com.tlabs.budget.app.model.Item;

@Component
public class DataProcessor {

	private static final Logger logger = Logger.getLogger(DataProcessor.class);

	private Calendar cal = null;
	private String month = "";
	
	@PostConstruct
	public void init() {
		cal = Calendar.getInstance();
		month = Month.of(cal.get(Calendar.MONTH) + 1).getDisplayName(TextStyle.FULL, Locale.getDefault());
		logger.info("Post Construct calendar and month:= " + month);
	}

	public Data createTransactionData(Data data) {

		if (data.getExpenses().size() > 0) {
			createTypeIndicatorAndStandardize("E_", data.getExpenses());
			System.out.println("expense data " + data.getExpenses());
		}

		if (data.getIncomes().size() > 0) {
			createTypeIndicatorAndStandardize("I_", data.getIncomes());
			System.out.println("income data " + data.getIncomes());
		}

		return data;
	}
	
	public List<Item> distinctExpenseCategorization(List<Item> expenseList, Integer totalIncomeSum) {

		Map<String, Integer> expenseValueMap = new HashMap<>();
		expenseList.forEach(item -> {
			addValuesBasedOnExpenseType(expenseValueMap, item);
		});

		List<Item> finalExpenseValueList = expenseValueMap.entrySet().stream().map(e -> {
			return setItemPercentageValue(totalIncomeSum, e);
		}).collect(Collectors.toList());

		return finalExpenseValueList;
	}

	private void addValuesBasedOnExpenseType(Map<String, Integer> expenseValueMap, Item item) {
		if (expenseValueMap.containsKey(item.getDescription())) {
			expenseValueMap.put(item.getDescription(),
					expenseValueMap.get(item.getDescription()) + item.getValue());
		} else {
			expenseValueMap.put(item.getDescription(), item.getValue());
		}
	}

	private Item setItemPercentageValue(Integer totalIncomeSum, Entry<String, Integer> e) {
		Item item  = new Item();
		item.setDescription(e.getKey());
		item.setValue(e.getValue());
		logger.info("Calculated Percentage:= " + calculateTotalPercentage(e.getValue(), totalIncomeSum));
		item.setItemPercentage(calculateTotalPercentage(e.getValue(), totalIncomeSum));
		return item;
	}

	public String calculateTotalPercentage(Integer expenseValue, Integer totalIncomeSum) {
		Double newExpensePercentage = Double
				.valueOf((Double.valueOf(expenseValue) / Double.valueOf(totalIncomeSum)) * 100);
		DecimalFormat df = new DecimalFormat("0.00");
		String expPerStringToUpate = df.format(newExpensePercentage);
		return expPerStringToUpate;
	}

	private void createTypeIndicatorAndStandardize(String typeInd, List<Item> itemsList) {

		for (Item item : itemsList) {
			StringBuilder builder = new StringBuilder();
			builder.append(typeInd).append(String.valueOf(cal.getTimeInMillis())).append("_").append(item.getId());
			item.setTransactionId(builder.toString());
			item.setMonth(month);
			item.setType(item.getType().toLowerCase());
		}

	}
	
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

}
