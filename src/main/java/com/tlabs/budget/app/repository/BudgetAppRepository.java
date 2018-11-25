package com.tlabs.budget.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tlabs.budget.app.model.Item;

@Repository
public interface BudgetAppRepository extends JpaRepository<Item, Long>{

}
