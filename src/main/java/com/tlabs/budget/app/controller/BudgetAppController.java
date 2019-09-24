package com.tlabs.budget.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tlabs.budget.app.model.Record;
import com.tlabs.budget.app.model.Transaction;
import com.tlabs.budget.app.processor.TransactionProcessor;
import com.tlabs.budget.app.service.BudgetService;

@RestController
public class BudgetAppController {
	
	@Autowired
	private TransactionProcessor tnxProcessor;
	
	@Autowired
	private BudgetService budgetService;
	
	@PostMapping("/save")
	public ResponseEntity<String> saveTransaction(@RequestBody List<Transaction> tnxList){
		
       System.out.println("Save Transaction " + tnxList);
       
       List<Record> recordList = tnxProcessor.validateAndMapTransactions(tnxList);
       
       budgetService.saveAllTransactions(recordList);
		
	   return ResponseEntity.ok().body("Hit Transaction");
	}
	
	@GetMapping("/category/{categoryName}")
	public ResponseEntity<String> addCategory(@PathVariable String categoryName){
		
		System.out.println("Category Name " + categoryName);
		budgetService.saveCategory(categoryName);
	
		return ResponseEntity.ok().body("Category " + categoryName);
	}
}
