package com.tlabs.budget.app.repository.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import com.tlabs.budget.app.model.Data;
import com.tlabs.budget.app.processor.DataProcessor;
import com.tlabs.budget.app.repository.BudgetAppRepository;

@Repository
public class BudgetAppRepositoryImpl implements BudgetAppRepository {

	private static final Logger logger = LoggerFactory.getLogger(BudgetAppRepositoryImpl.class);
	
	@Autowired
	private DataProcessor dataProcessor;

	@Override
	@Async
	public void saveTransactions(Data data) {

		logger.info("Save transaction task thread " + Thread.currentThread().getName());
		
		dataProcessor.createTransactionData(data);
		
		

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		logger.info("Complete");
	}

	@Override
	@Async
	public void getMonthlyBudgetSheet() {

		logger.info("getMonthlyBudgetSheet task thread " + Thread.currentThread().getName());

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		logger.info("Complete");
	}

	@Override
	public void saveOrUpdateBudgetSheet() {

		logger.info("saveOrUpdateBudgetSheet task thread " + Thread.currentThread().getName());

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		logger.info("Complete saveOrUpdateBudgetSheet " + Thread.currentThread().getName());
	}

}
