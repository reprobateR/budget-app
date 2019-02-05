package com.tlabs.budget.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tlabs.budget.app.exception.BudgetAppException;
import com.tlabs.budget.app.exception.BudgetAppResponse;
import com.tlabs.budget.app.model.BudgetSheet;
import com.tlabs.budget.app.model.Category;
import com.tlabs.budget.app.model.Charts;
import com.tlabs.budget.app.model.Item;
import com.tlabs.budget.app.service.BudgetAppService;

@RestController
public class BudgetAppController {

	private static final Logger logger = LoggerFactory.getLogger(BudgetAppController.class);
	
	@Autowired
	private BudgetAppService budgetAppService;
	
	@PostMapping("/save")
	public BudgetAppResponse getTransactions(@RequestBody BudgetSheet budgetSheet) throws BudgetAppException{
		budgetAppService.saveTransactions(budgetSheet.getData());
		budgetAppService.saveOrUpdateBudget(budgetSheet.getBudget());
		BudgetAppResponse budgetAppResponse = createSuccessResponse();
		return budgetAppResponse;
    }

	@GetMapping("/category")
	public ResponseEntity<List<Category>> getCategory(){
		return ResponseEntity.ok().body(budgetAppService.loadCategoryCache());
	}
	
	@GetMapping("/indicators")
	public ResponseEntity<List<Charts>> getGraphIndicatorResponse(){
		
		logger.info("Creation of Graph Indicator Response JSON Begins");
		
		List<Item> expenseList = budgetAppService.getTransactions();
		List<Charts> chartResponse = budgetAppService.createGraphResponse(expenseList);
		
		return ResponseEntity.ok().body(chartResponse);
			
	}
	
	private BudgetAppResponse createSuccessResponse() {
		BudgetAppResponse budgetAppResponse = new BudgetAppResponse();
		budgetAppResponse.setHttpStatus(HttpStatus.OK);
		budgetAppResponse.setMessage("Budget and transactions are saved!");
		budgetAppResponse.setTimestamp(System.currentTimeMillis());
		return budgetAppResponse;
	}

}
