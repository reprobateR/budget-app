package com.tlabs.budget.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlabs.budget.app.model.Category;
import com.tlabs.budget.app.model.Record;
import com.tlabs.budget.app.repository.BudgetRepository;
import com.tlabs.budget.app.repository.CategoryRepository;

@Service
@Transactional
public class BudgetServiceImpl implements BudgetService {
	
	@Autowired
	private BudgetRepository budgetRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public void saveAllTransactions(List<Record> recordsList) {
		recordsList.forEach(System.out::println);
		recordsList.forEach(budgetRepository::save);
	}

	@Override
	public void saveCategory(String categoryName) {
		Category category = new Category();
		category.setCategoryName(categoryName);
		categoryRepository.save(category);
	}

}
