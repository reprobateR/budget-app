package com.tlabs.budget.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tlabs.budget.app.model.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category,Long>{

}
