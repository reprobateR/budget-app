package com.tlabs.budget.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tlabs.budget.app.model.Item;

@Repository
public interface BudgetAppRepository extends JpaRepository<Item, Long> {

	@Query(value = "SELECT * FROM TRANSACTIONS WHERE TYPE=?1 AND MONTH=?2", nativeQuery = true)
	List<Item> findExpenseListByMonth(String type, String month);

}
