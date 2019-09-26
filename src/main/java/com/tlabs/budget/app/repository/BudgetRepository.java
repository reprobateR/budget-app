package com.tlabs.budget.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tlabs.budget.app.model.Record;

public interface BudgetRepository extends JpaRepository<Record,Long>{
	
	@Query("SELECT r FROM Record r WHERE r.month = ?1")
	List<Record> findByMonth(String month);

}
