package com.tlabs.budget.app.model;

public class Transaction {

	private String type;
	private String name;
	private String value;
	
	public Transaction() {
		
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Transaction [type=" + type + ", name=" + name + ", value=" + value + "]";
	}

	
}
