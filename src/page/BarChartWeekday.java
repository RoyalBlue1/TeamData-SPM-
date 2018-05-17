package page;

import java.awt.Color;
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
 * Servlet implementation class BarChartWeekday
 */
@WebServlet("/BarChartWeekday")
public class BarChartWeekday extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BarChartWeekday() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("analysis") != null) {
		
		response.setContentType("image/png");
		
		OutputStream os = response.getOutputStream();
		Map<String, Integer> map = ((Analysis) request.getSession().getAttribute("analysis")).getCustomersWeekday();
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
		dataset.addValue(map.get("Montag"), "", "Montag");
		dataset.addValue(map.get("Dienstag"), "", "Dienstag");
		dataset.addValue(map.get("Mittwoch"), "", "Mittwoch");
		dataset.addValue(map.get("Donnerstag"), "", "Donnerstag");
		dataset.addValue(map.get("Freitag"), "", "Freitag");
		dataset.addValue(map.get("Samstag"), "", "Samstag");
		
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
		
		return chart;
	}

}
