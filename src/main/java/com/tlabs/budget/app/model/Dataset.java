package com.tlabs.budget.app.model;

import java.util.List;

public class Dataset {
	
	private String label;
    private List<Integer> data;
	private List<String> backgroundColor;
	private int borderWidth;
	private String borderColor;
	private int hoverBorderWidth;
	private String hoverBorderColor;
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public List<Integer> getData() {
		return data;
	}
	public void setData(List<Integer> data) {
		this.data = data;
	}
	public List<String> getBackgroundColor() {
		return backgroundColor;
	}
	public void setBackgroundColor(List<String> backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	public int getBorderWidth() {
		return borderWidth;
	}
	public void setBorderWidth(int borderWidth) {
		this.borderWidth = borderWidth;
	}
	public String getBorderColor() {
		return borderColor;
	}
	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}
	public int getHoverBorderWidth() {
		return hoverBorderWidth;
	}
	public void setHoverBorderWidth(int hoverBorderWidth) {
		this.hoverBorderWidth = hoverBorderWidth;
	}
	public String getHoverBorderColor() {
		return hoverBorderColor;
	}
	public void setHoverBorderColor(String hoverBorderColor) {
		this.hoverBorderColor = hoverBorderColor;
	}
	@Override
	public String toString() {
		return "Dataset [label=" + label + ", data=" + data + ", backgroundColor=" + backgroundColor + ", borderWidth="
				+ borderWidth + ", borderColor=" + borderColor + ", hoverBorderWidth=" + hoverBorderWidth
				+ ", hoverBorderColor=" + hoverBorderColor + "]";
	}
	public Dataset() {

	}

}
