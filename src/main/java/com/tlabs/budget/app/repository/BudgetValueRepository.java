package com.tlabs.budget.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tlabs.budget.app.model.Budget;

@Repository
public interface BudgetValueRepository extends JpaRepository<Budget,Long> {
	
	Budget findByMonth(String month);
	
}
