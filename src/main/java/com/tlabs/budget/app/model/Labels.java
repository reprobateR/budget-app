package com.tlabs.budget.app.model;

public class Labels {
	
	private String fontColor;

	public String getFontColor() {
		return fontColor;
	}

	public void setFontColor(String fontColor) {
		this.fontColor = fontColor;
	}
	
	public Labels build(String fontColor){
		this.fontColor = fontColor;
		return this;
	}

}
