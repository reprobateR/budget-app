package com.tlabs.budget.app.processor;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Component;

import com.tlabs.budget.app.model.Data;
import com.tlabs.budget.app.model.Item;

@Component
public class DataProcessor {

	public Data createTransactionData(Data data) {

		for (Item item : data.getExpenses()) {

			StringBuilder builder = new StringBuilder();
			Calendar cal = Calendar.getInstance();
			Date currentDate = cal.getTime();

			builder.append("E_")
					.append(String.valueOf(currentDate.getTime()))
					.append("_").append(item.getId());
			item.setTransactionId(builder.toString());

			item.setMonth(Month.of(currentDate.getMonth() + 1));

		}
		return data;

	}

}
