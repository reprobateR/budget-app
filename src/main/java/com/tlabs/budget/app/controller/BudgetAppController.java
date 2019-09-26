package com.tlabs.budget.app.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tlabs.budget.app.model.Category;
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
       
       List<String> expenseCategories = budgetService.getAllCategories().stream().
                    map(c -> c.getCategoryName()).collect(Collectors.toList());
       tnxProcessor.setExpenseCategories(expenseCategories);
       
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
	
	@GetMapping("/category")
	public ResponseEntity<Set<Category>> getCategories(){
		return ResponseEntity.ok().body(budgetService.getAllCategories());
	}
	
	@GetMapping("/transaction/{month}")
	public ResponseEntity<List<Record>> getTransactionsByMonth(@PathVariable String month){
		tnxProcessor.validateMonth(month);
		return ResponseEntity.ok().body(budgetService.getAllRecordsByMonth(month));
	}
}
