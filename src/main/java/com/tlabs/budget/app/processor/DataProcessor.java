package com.tlabs.budget.app.processor;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.sun.istack.internal.logging.Logger;
import com.tlabs.budget.app.model.Data;
import com.tlabs.budget.app.model.Item;

@Component
public class DataProcessor {
	
	private static final Logger logger = Logger.getLogger(DataProcessor.class);
	
	private Calendar cal= null;
	private String month = "";
	
	@PostConstruct
	public void init(){
		cal = Calendar.getInstance();
		month = Month.of(cal.get(Calendar.MONTH) + 1).getDisplayName(TextStyle.FULL, Locale.getDefault());
		logger.info("Post Construct calendar and month " + month);
	}

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
			builder.append(typeInd).append(String.valueOf(cal.getTimeInMillis())).append("_").append(item.getId());
			item.setTransactionId(builder.toString());
			item.setMonth(month);
		}

	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
	
}
