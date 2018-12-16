package com.tlabs.budget.app.model;

public class Legend {
		
	private boolean display;
	private String position;
	private Labels labels;
		
	public boolean isDisplay() {
		return display;
	}
	public void setDisplay(boolean display) {
		this.display = display;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Labels getLabels() {
		return labels;
	}
	public void setLabels(Labels labels) {
		this.labels = labels;
	}
	
	@Override
	public String toString() {
		return "Legend [display=" + display + ", position=" + position + ", labels=" + labels + "]";
	}
	public Legend() {

	}

}
