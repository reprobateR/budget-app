package com.tlabs.budget.app.controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tlabs.budget.app.model.BudgetSheet;
import com.tlabs.budget.app.repository.impl.BudgetSheetRepository;
import com.tlabs.budget.app.service.BudgetAppService;

@RestController
@RequestMapping("/budget")
public class BudgetAppController {

	private static final Logger logger = LoggerFactory.getLogger(BudgetAppController.class);

	@Autowired
	private BudgetAppService budgetAppService;
	
	@Autowired
	private BudgetSheetRepository budgetSheetRepository;

	@PostMapping("/save")
	public ResponseEntity<String> getTransactions(@RequestBody BudgetSheet budgetSheet){

		long startTime = System.currentTimeMillis();
		try {
			
		logger.info("Budget Transaction Received " + budgetSheet);

		logger.info("Start Time " + startTime/1000);

		logger.info("Controller Main Thread " + Thread.currentThread().getName());

		budgetAppService.saveTransactions(budgetSheet.getData());
		
		CompletableFuture.supplyAsync(() -> budgetSheetRepository.getExistingOrCreateBudget(budgetSheet.getBudget()))
					.thenAccept(budget -> budgetSheetRepository.saveOrUpdateBudgetSheet(budget)).get();
		
		} catch (InterruptedException | ExecutionException e) {
			logger.error(e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();

			logger.info("End Time " + endTime/1000);

			logger.info("Total End Time " + (endTime - startTime)/1000);

			logger.info("End controller main thread " + Thread.currentThread().getName());
		}
		return ResponseEntity.ok().body("Success!!");
    }

}
