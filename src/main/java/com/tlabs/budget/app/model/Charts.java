package com.tlabs.budget.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Charts {

	private String type;
	
	@JsonProperty("data")
	private ChartData chartData;
	
	private Options options;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ChartData getChartData() {
		return chartData;
	}

	public void setChartData(ChartData chartData) {
		this.chartData = chartData;
	}

	public Options getOptions() {
		return options;
	}

	public void setOptions(Options options) {
		this.options = options;
	}

	public Charts() {

	}

	@Override
	public String toString() {
		return "Charts [type=" + type + ", chartData=" + chartData + ", options=" + options + "]";
	}

}
