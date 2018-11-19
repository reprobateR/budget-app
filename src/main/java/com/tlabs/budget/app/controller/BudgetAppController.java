package com.tlabs.budget.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tlabs.budget.app.model.BudgetSheet;
import com.tlabs.budget.app.repository.BudgetAppRepository;

@RestController
@RequestMapping("/budget")
public class BudgetAppController {

	private static final Logger logger = LoggerFactory.getLogger(BudgetAppController.class);

	@Autowired
	private BudgetAppRepository budgetAppRepository;

	@PostMapping("/save")
	public ResponseEntity<String> getTransactions(@RequestBody BudgetSheet budgetSheet){

		logger.info("Budget Transaction Received " + budgetSheet);

		long startTime = System.currentTimeMillis();

		logger.info("Start Time " + startTime/1000);

		logger.info("Controller Main Thread " + Thread.currentThread().getName());

		budgetAppRepository.saveTransactions(budgetSheet.getData());

		budgetAppRepository.getMonthlyBudgetSheet();

		budgetAppRepository.saveOrUpdateBudgetSheet();

		long endTime = System.currentTimeMillis();

		logger.info("End Time " + endTime/1000);

		logger.info("Total End Time " + (endTime - startTime)/1000);

		logger.info("End controller main thread " + Thread.currentThread().getName());

		return ResponseEntity.ok().body("Success!!");
    }

}
