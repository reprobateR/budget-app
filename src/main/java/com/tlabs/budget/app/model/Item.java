package com.tlabs.budget.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "transactions")
public class Item {

	@Id
	@Column(name = "transaction_id")
	private String transactionId;

	@Column(name = "id")
	private int id;

	@Column(name = "type")
	private String type;

	@Column(name = "description")
	private String description;
	
	@Column(name = "item_percentage")
	@JsonProperty("item__percentage")
	private String itemPercentage;

	@Column(name = "value")
	private int value;

	@Column(name = "month")
	private String month;

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
		return "Item [transactionId=" + transactionId + ", id=" + id + ", type=" + type + ", description=" + description
				+ ", itemPercentage=" + itemPercentage + ", value=" + value + ", month=" + month + "]";
	}

	public Item() {

	}

	public String getItemPercentage() {
		return itemPercentage;
	}

	public void setItemPercentage(String itemPercentage) {
		this.itemPercentage = itemPercentage;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

}
