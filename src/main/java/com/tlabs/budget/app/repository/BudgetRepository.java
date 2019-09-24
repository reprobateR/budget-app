package com.tlabs.budget.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tlabs.budget.app.model.Record;

public interface BudgetRepository extends JpaRepository<Record,Long>{

}
