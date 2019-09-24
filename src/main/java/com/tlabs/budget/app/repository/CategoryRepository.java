package com.tlabs.budget.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.tlabs.budget.app.model.Category;

public interface CategoryRepository extends CrudRepository<Category,Long>{

}
