package com.tlabs.budget.app.model;

import java.time.Month;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="transactions")
public class Record {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="type")
	private String type;
	
	@Column(name="name")
	private String name;
	
	@Column(name="value")
	private Float value;
	
	@Column(name="timestamp")
	private Date timestamp;
	
	@Column(name="month")
	private Month month;
	
	public Record() {
		
	}

	public Record(int id, String type, String name, Float value, Date timestamp, Month month) {
		this.id = id;
		this.type = type;
		this.name = name;
		this.value = value;
		this.timestamp = timestamp;
		this.month = month;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Float getValue() {
		return value;
	}

	public void setValue(Float value) {
		this.value = value;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Month getMonth() {
		return month;
	}

	public void setMonth(Month month) {
		this.month = month;
	}

	@Override
	public String toString() {
		return "Record [id=" + id + ", type=" + type + ", name=" + name + ", value=" + value + ", timestamp="
				+ timestamp + ", month=" + month + "]";
	}
	

}
