package com.tlabs.budget.app.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlabs.budget.app.exception.BudgetAppException;
import com.tlabs.budget.app.model.Data;
import com.tlabs.budget.app.processor.DataProcessor;
import com.tlabs.budget.app.repository.BudgetAppRepository;
import com.tlabs.budget.app.service.BudgetAppService;

@Service
public class BudgetAppServiceImpl implements BudgetAppService {

	private static final Logger logger = LoggerFactory.getLogger(BudgetAppService.class);
	
	@Autowired
	private DataProcessor dataProcessor;
	
	@Autowired
	private BudgetAppRepository budgetAppRepository;

	@Override
	@Async
	@Transactional
	public void saveTransactions(Data data) throws BudgetAppException {
		
		/*logger.info("Save transaction task thread " + Thread.currentThread().getName());
		
		Data updatedData = dataProcessor.createTransactionData(data);
		
		logger.info("updated data before insert" + updatedData);
		
		budgetAppRepository.saveAll(updatedData.getExpenses());
		budgetAppRepository.saveAll(updatedData.getIncomes());
	
		logger.info("Complete  - saveTransactions ");*/
	}

}
