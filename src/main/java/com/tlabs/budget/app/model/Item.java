package com.tlabs.budget.app.model;

import java.time.Month;

public class Item {

	private String transactionId;

	private int id;

	private String type;

	private String description;

	private int value;

	private Month month;

	public Month getMonth() {
		return month;
	}

	public void setMonth(Month month) {
		this.month = month;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	@Override
	public String toString() {
		return "Item [type=" + type + ", description=" + description
				+ ", value=" + value + ", id=" + id + "]";
	}

	public Item() {

	}

}
