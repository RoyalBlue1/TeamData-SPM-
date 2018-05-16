package analysis;

import java.awt.Color;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;

import weka.core.Instances;
import weka.core.converters.CSVLoader;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.NumericCleaner;
import weka.filters.unsupervised.attribute.NumericToNominal;
import weka.filters.unsupervised.attribute.Remove;

public class Analysis implements Serializable{

	File weekDayChart;
	File daytimeChart;
	List<String> topItems;
	
	public Analysis() {
		weekDayChart = new File("");
		daytimeChart = new File("");
		topItems = new ArrayList<>();
	}
	
	public Analysis(String csv, File week, File daytime) throws Exception {
		
		Instances data;
		List<Item> items;
		topItems = new ArrayList<>();
		
		//load CSV-File into Weka
		CSVLoader loader = new CSVLoader();
		loader.setSource(new File(csv));
		data = loader.getDataSet();			
		data = numericToNominal(data);
		
		//get Top 5 items from data
		items = getTop5Items(data);
		for(Item i: items) {
			topItems.add(i.toString());
		}
		
		weekDayChart = week;
		daytimeChart = daytime;
		createCharts(data, weekDayChart, daytimeChart);
		
	}
	
	private Instances numericToNominal(Instances data) throws Exception {
		
		// 0 durch ? ersetzen, um für die Auswertung nur die Waren zu
		// berücksichtigen, die gekauft wurden
		NumericCleaner nc = new NumericCleaner();
		nc.setMinThreshold(1.0); // Schwellwert auf 1 setzen
		nc.setMinDefault(Double.NaN); // alles unter Schwellwert durch ? ersetzen
		nc.setInputFormat(data);
		data = Filter.useFilter(data, nc); // Filter anwenden.
		
		// Die Daten als nominale und nicht als numerische Daten setzen
		NumericToNominal num2nom = new NumericToNominal();
		num2nom.setAttributeIndices("first-last");
		num2nom.setInputFormat(data);
		return Filter.useFilter(data, num2nom);
		
	}
	
	public List<Item> getTop5Items(Instances data){
		
		ArrayList<Item> items = new ArrayList<>();
		
		//Get items out of Data
		for(int i = 7; i < 24; i++) {
			items.add(new Item(data.attribute(i).toString(), data.attributeStats(i).nominalCounts[0]));
		}
		
		Collections.sort(items);
		
		//Shrink list to top 5 items
		for(int i=items.size()-1; i>4; i--) {
			items.remove(i);
		}
		
		return items;
		
	}
	
	public void createCharts(Instances data, File week, File daytime) throws Exception {
		
		//extract weekday attribute from data
		Instances einkaufsTage = new Instances(data);
		Remove remove = new Remove();
		remove.setAttributeIndices("6");
		remove.setInvertSelection(true);
		remove.setInputFormat(einkaufsTage);
		einkaufsTage = Filter.useFilter(einkaufsTage, remove);
		
		Enumeration<Object> e = einkaufsTage.attribute(0).enumerateValues();
		Map<String, Integer> mapCustomerToDay = new HashMap<>();
		
		//Add Values to Map
		for(int i=0; i<einkaufsTage.attribute(0).numValues(); i++) {
			mapCustomerToDay.put(e.nextElement().toString(), einkaufsTage.attributeStats(0).nominalCounts[i]);
		}
		
		//Insert data into Chart
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(mapCustomerToDay.get("Montag"), "", "Montag");
		dataset.addValue(mapCustomerToDay.get("Dienstag"), "", "Dienstag");
		dataset.addValue(mapCustomerToDay.get("Mittwoch"), "", "Mittwoch");
		dataset.addValue(mapCustomerToDay.get("Donnerstag"), "", "Donnerstag");
		dataset.addValue(mapCustomerToDay.get("Freitag"), "", "Freitag");
		dataset.addValue(mapCustomerToDay.get("Samstag"), "", "Samstag");
		
		JFreeChart chart = ChartFactory.createBarChart("", "Wochentag", "Kunden", dataset);
		chart.removeLegend();
		
		//Change Style of Chart
		StandardChartTheme theme = (StandardChartTheme)org.jfree.chart.StandardChartTheme.createJFreeTheme();
		theme.setRangeGridlinePaint( Color.decode("#C0C0C0"));
		theme.setPlotBackgroundPaint( Color.white );
	    theme.setChartBackgroundPaint( Color.white );
	    theme.setBarPainter(new StandardBarPainter());
	    theme.apply(chart);
		CategoryPlot plot = chart.getCategoryPlot();
		plot.setOutlineVisible(false);
		plot.getRangeAxis().setAxisLineVisible(false);
		plot.getRangeAxis().setTickMarksVisible(false);
		plot.getRangeAxis().setTickLabelPaint(Color.decode("#666666"));
		plot.getRenderer().setSeriesPaint(0, Color.decode("#4572a7"));
		
		//Save Bar Chart
		ChartUtilities.saveChartAsJPEG(week, chart, 800, 500);
		
		//extract daytime attribute from data
		Instances einkaufsZeiten = new Instances(data);
		remove = new Remove();
		remove.setAttributeIndices("7");
		remove.setInvertSelection(true);
		remove.setInputFormat(einkaufsZeiten);
		einkaufsZeiten = Filter.useFilter(einkaufsZeiten, remove);
		
		e = einkaufsZeiten.attribute(0).enumerateValues();
		Map<String, Integer> mapCustomerToDaytime = new HashMap<>();
		
		//Add Values to Map
		for(int i=0; i<einkaufsZeiten.attribute(0).numValues(); i++) {
			mapCustomerToDaytime.put(e.nextElement().toString(), einkaufsZeiten.attributeStats(0).nominalCounts[i]);
		}
		
		//Insert data into Chart
		dataset = new DefaultCategoryDataset();
		
		dataset.addValue(mapCustomerToDaytime.get("<10 Uhr"), "", "<10 Uhr");
		dataset.addValue(mapCustomerToDaytime.get("10-12 Uhr"), "", "10-12 Uhr");
		dataset.addValue(mapCustomerToDaytime.get("12-14 Uhr"), "", "12-14 Uhr");
		dataset.addValue(mapCustomerToDaytime.get("14-17 Uhr"), "", "14-17 Uhr");
		dataset.addValue(mapCustomerToDaytime.get(">17 Uhr"), "", ">17 Uhr");
		
		chart = ChartFactory.createBarChart("", "Uhrzeit", "Kunden", dataset);
		chart.removeLegend();
		
		//Change Style of Chart
	    theme.apply(chart);
		plot = chart.getCategoryPlot();
		plot.setOutlineVisible(false);
		plot.getRangeAxis().setAxisLineVisible(false);
		plot.getRangeAxis().setTickMarksVisible(false);
		plot.getRangeAxis().setTickLabelPaint(Color.decode("#666666"));
		plot.getRenderer().setSeriesPaint(0, Color.decode("#4572a7"));
		
		//Save Bar Chart
		ChartUtilities.saveChartAsJPEG(daytime, chart, 800, 500);		
		
	}

	public File getWeekDayChart() {
		return weekDayChart;
	}

	public void setWeekDayChart(File weekDayChart) {
		this.weekDayChart = weekDayChart;
	}

	public File getDaytimeChart() {
		return daytimeChart;
	}

	public void setDaytimeChart(File daytimeChart) {
		this.daytimeChart = daytimeChart;
	}

	public List<String> getTopItems() {
		return topItems;
	}

	public void setTopItems(List<String> topItems) {
		this.topItems = topItems;
	}
	
	
	
}
