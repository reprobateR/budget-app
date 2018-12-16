package com.tlabs.budget.app.model;

public class Title {
	
	private boolean display;
	private String text;
	private int fontsize;
	
	public boolean isDisplay() {
		return display;
	}
	public void setDisplay(boolean display) {
		this.display = display;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getFontsize() {
		return fontsize;
	}
	public void setFontsize(int fontsize) {
		this.fontsize = fontsize;
	}
	
	@Override
	public String toString() {
		return "Title [display=" + display + ", text=" + text + ", fontsize=" + fontsize + "]";
	}
	public Title() {
		
	}

}
