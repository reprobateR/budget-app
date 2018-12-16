package com.tlabs.budget.app.model;

public class Options {
	
	private Title title;
	private Legend legend;
	private Layout layout;
	private Tooltips tooltips;
	
	public Title getTitle() {
		return title;
	}
	public void setTitle(Title title) {
		this.title = title;
	}
	public Legend getLegend() {
		return legend;
	}
	public void setLegend(Legend legend) {
		this.legend = legend;
	}
	public Layout getLayout() {
		return layout;
	}
	public void setLayout(Layout layout) {
		this.layout = layout;
	}
	public Tooltips getTooltips() {
		return tooltips;
	}
	public void setTooltips(Tooltips tooltips) {
		this.tooltips = tooltips;
	}
	
	@Override
	public String toString() {
		return "Options [title=" + title + ", legend=" + legend + ", layout=" + layout + ", tooltips=" + tooltips + "]";
	}
	public Options() {
	
	}
	
}
