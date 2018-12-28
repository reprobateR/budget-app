package com.tlabs.budget.app.processor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tlabs.budget.app.model.ChartData;
import com.tlabs.budget.app.model.Charts;
import com.tlabs.budget.app.model.Dataset;
import com.tlabs.budget.app.model.Item;
import com.tlabs.budget.app.model.Labels;
import com.tlabs.budget.app.model.Layout;
import com.tlabs.budget.app.model.Legend;
import com.tlabs.budget.app.model.Options;
import com.tlabs.budget.app.model.Padding;
import com.tlabs.budget.app.model.Title;
import com.tlabs.budget.app.model.Tooltips;

@Component
public class GraphResponse {

	@Value("${colors}")
	private String colors;

	@Value("${borderWidth}")
	private int borderWidth;

	@Value("${borderColor}")
	private String borderColor;

	@Value("${hoverBorderWidth}")
	private int hoverBorderWidth;

	@Value("${hoverBorderColor}")
	private String hoverBorderColor;

	public Charts createChartData(String type, List<Item> expenseList, String label, String text) {

		Charts chart = new Charts();
		ChartData chartData = new ChartData();
		Dataset dataset = new Dataset();
		
		chart.setType(type);
		
		List<String> labelList = new ArrayList<>();
		List<String> dataList = new ArrayList<>();
		List<String> availableColors = getBackGroundColors(colors);
		List<String> backGroundColors = new ArrayList<>();

    	int colorCounter = 0;
		for (Item item : expenseList) {

			labelList.add(item.getDescription());
			if (type.equals("bar")) {
				dataList.add(String.valueOf(item.getValue()));
			} else if (type.equals("pie")) {
				dataList.add(item.getItemPercentage());
			}
			if (colorCounter >= availableColors.size()) {
				colorCounter = 0;
			}
			backGroundColors.add(availableColors.get(colorCounter));
			colorCounter++;
		}

		chartData.setLabels(labelList);

		dataset.setLabel(label);
		dataset.setData(dataList);
		dataset.setBackgroundColor(backGroundColors);
		dataset.setBorderWidth(borderWidth);
		dataset.setBorderColor(borderColor);
		dataset.setHoverBorderWidth(hoverBorderWidth);
		dataset.setHoverBorderColor(hoverBorderColor);
		List<Dataset> datasetList = new ArrayList<>();
		datasetList.add(dataset);
		
		chartData.setDatasets(datasetList);
		chart.setChartData(chartData);
		
		Options options = new Options();
		
		Title title = new Title();
		title.setDisplay(true);
		title.setText(text);
		title.setFontsize(25);
		
		Legend legend = new Legend();
		legend.setDisplay(true);
		legend.setPosition("right");
		legend.setLabels(new Labels().build("#000"));
		
		Layout layout = new Layout();
		layout.setPadding(new Padding(50,0,0,0));
		
		Tooltips tooltips = new Tooltips(true);
		
		options.setTitle(title);
		options.setLegend(legend);
		options.setLayout(layout);
		options.setTooltips(tooltips);
		
		chart.setOptions(options);	
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			String initialChart = mapper.writeValueAsString(chart);

			System.out.println("Initial Chart:  " + initialChart);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return chart;
	}

	private List<String> getBackGroundColors(String colors2) {
		
		List<String> colorsList = new ArrayList<String>();
		StringBuilder builder = new StringBuilder(colors2);
		int indexCounter = 0;
		int initialIndex = 0;
		
		while(initialIndex < builder.length()) {
			if(initialIndex == 0) {
				indexCounter = builder.indexOf(")");
			}
			colorsList.add(builder.substring(initialIndex, indexCounter + 1));
			initialIndex = indexCounter + 2;
			indexCounter = builder.indexOf(")", initialIndex);
		}
		return colorsList;
	}
}
