package com.tlabs.budget.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tlabs.budget.app.model.BudgetSheet;

@RestController
@RequestMapping("/budget")
public class BudgetAppController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BudgetAppController.class);

	@PostMapping("/save")
	public ResponseEntity<String> getTransactions(@RequestBody BudgetSheet budgetSheet){

		LOGGER.info(("save budget hit " + budgetSheet));

		return ResponseEntity.ok().body("Success!!");
    }

}
