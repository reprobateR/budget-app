package com.tlabs.budget.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "expense_categories")
public class Category {
	
	@Id
	@Column(name = "id")
	private int id;
	
	@Column(name = "description")
	private String category;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public Category(int id, String category) {
		this.id = id;
		this.category = category;
	}
	
	public Category() {

	}
	
	@Override
	public String toString() {
		return "Category [id=" + id + ", category=" + category + "]";
	}

}
