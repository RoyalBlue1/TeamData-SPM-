package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Map;

import javax.swing.text.AttributeSet.CharacterAttribute;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;
import org.junit.Test;

import analysis.Analysis;

public class analysisTest {

	@Test
	public void test() throws Exception {
		
		Analysis analysis;
		
		InputStream csv = new FileInputStream(getClass().getResource("/testCSV.csv").getFile());
		
		analysis = new Analysis(csv);
		
		Map<String, Integer> map = analysis.getCustomersDaytime();
		//Insert data into Chart
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		dataset.addValue(map.get("<10 Uhr"), "", "<10 Uhr");
		dataset.addValue(map.get("10-12 Uhr"), "", "10-12 Uhr");
		dataset.addValue(map.get("12-14 Uhr"), "", "12-14 Uhr");
		dataset.addValue(map.get("14-17 Uhr"), "", "14-17 Uhr");
		dataset.addValue(map.get(">17 Uhr"), "", ">17 Uhr");
		
		JFreeChart chart = ChartFactory.createBarChart("", "Uhrzeit", "Kunden", dataset);
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
		
		ChartUtilities.saveChartAsJPEG(new File("test.jpeg"), chart, 800, 500);
		
		//test top 5 items
		assertEquals("Kaffee / Tee", analysis.getTopItems().get(0));
		assertEquals("Backwaren", analysis.getTopItems().get(1));
		assertEquals("Alkoholfreie Getränke", analysis.getTopItems().get(2));
		assertEquals("Tiefkühlware", analysis.getTopItems().get(3));
		assertEquals("Konserven", analysis.getTopItems().get(4));
		
	}

}
