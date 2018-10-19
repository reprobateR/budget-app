package com.tlabs.budget.app.model;

public class Item {

	private int id;

	private String type;

	private String description;

	private int value;

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

	@Override
	public String toString() {
		return "Item [type=" + type + ", description=" + description
				+ ", value=" + value + ", id=" + id + "]";
	}

	public Item() {

	}

}
