package com.tlabs.budget.app.processor;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Component;

import com.tlabs.budget.app.model.Data;
import com.tlabs.budget.app.model.Item;

@Component
public class DataProcessor {

	public Data createTransactionData(Data data) {

		if (data.getExpenses().size() > 0) {
			createTransactionIndicator("E_", data.getExpenses());
			System.out.println("expense data " + data.getExpenses());
		}

		if (data.getIncomes().size() > 0) {
			createTransactionIndicator("I_", data.getIncomes());
			System.out.println("income data " + data.getIncomes());
		}

		return data;

	}

	private void createTransactionIndicator(String typeInd, List<Item> itemsList) {

		for (Item item : itemsList) {

			StringBuilder builder = new StringBuilder();
			Calendar cal = Calendar.getInstance();

			builder.append(typeInd).append(String.valueOf(cal.getTimeInMillis())).append("_").append(item.getId());
			item.setTransactionId(builder.toString());

			item.setMonth(Month.of(cal.get(Calendar.MONTH) + 1).getDisplayName(TextStyle.FULL, Locale.getDefault()));

		}

	}

}
