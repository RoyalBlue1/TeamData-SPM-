package page;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;

import analysis.Analysis;

/**
 * Servlet implementation class BarChartDaytime
 */
@WebServlet("/BarChartDaytime")
public class BarChartDaytime extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BarChartDaytime() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("analysis") != null) {
			
			response.setContentType("image/png");
			
			OutputStream os = response.getOutputStream();
			Map<String, Integer> map = ((Analysis) request.getSession().getAttribute("analysis")).getCustomersDaytime();
			JFreeChart chart = getChart(map);			
			
			ChartUtilities.writeChartAsPNG(os, chart, 800, 500);
			
			os.close();
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private JFreeChart getChart(Map<String, Integer> map) {		
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
		
		return chart;
	}

}
