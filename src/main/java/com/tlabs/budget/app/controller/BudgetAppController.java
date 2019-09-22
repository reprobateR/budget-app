package com.tlabs.budget.app.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tlabs.budget.app.model.Transaction;

@RestController
public class BudgetAppController {
	
	@PostMapping("/save")
	public ResponseEntity<String> saveTransaction(@RequestBody List<Transaction> tnxList){
		
       System.out.println("Save Transaction " + tnxList);
		
	   return ResponseEntity.ok().body("Hit Transaction");
	}
}
