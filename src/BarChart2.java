import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
	 * Responsible for creation of a bar chart to be implemented in 
	 * JSwing. ApplicationFrame is a subclass of javax.swing.JFrame.
	 * @author cbusc
	 *
	 */
public class BarChart2 extends ApplicationFrame 
{
	/**
	 * Creates a new BarChart instance
	 * 
	 * @param title (the frame title)
	 */
	public BarChart2(String title)
	{
		//Superclass constructor
		super(title);
		CategoryDataset dataset = createDataset();
		JFreeChart chart = createChart(dataset);
		//ChartPanel extends JPanel
		ChartPanel chartPanel = new ChartPanel(chart, false);
		chartPanel.setPreferredSize(new Dimension(500,270));
		setContentPane(chartPanel);
	}
	
	/**
	 * Returns a category dataset based on age categories (0-9, 10-19, etc.).
	 * 
	 * @return a dataset implementing the CategoryDataset interface
	 */
	private static CategoryDataset createDataset()
	{
		//Row keys
		String deceased = "Deceased";
		String recovered = "Recovered";
		
		//Column keys defined at hashmap creation in DataAnalysis: 0-9, 10-19, etc. 
		
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		//Iterate through HashMap: ageAsDecadeToNumReleased	
		Map<String, Double> ageAsDecadeToProbReleased = DataAnalysis.getAgeAsDecadeToProbReleased();
		for (String age : ageAsDecadeToProbReleased.keySet())
		{
			dataset.addValue(ageAsDecadeToProbReleased.get(age), recovered, age);
		}
		
		//Iterate through HashMap: ageAsDecadeToNumDeceased
		Map<String, Double> ageAsDecadeToProbDeceased = DataAnalysis.getAgeAsDecadeToProbDeceased();
		for (String age : ageAsDecadeToProbDeceased.keySet())
		{
			dataset.addValue(ageAsDecadeToProbDeceased.get(age), deceased, age);
		}
		
		return dataset;
		
	}
	
	/**
	 * Creates a bar chart given a parameterized dataset
	 * @param dataset
	 * @return bar chart
	 */
	private static JFreeChart createChart(CategoryDataset dataset)
	{
		JFreeChart chart = ChartFactory.createStackedBarChart(
			"Age Versus Probability of Mortality",  			//chart title
			"Age by Decade",    					   				  //domain axis label
			"Probability of Mortality",		  						//range axis label
			dataset, 												  //data
			PlotOrientation.HORIZONTAL,								  //orientation
			true, 													  //include legend
			true,													  //tooltips?
			false													  //URLs?
		);
				
		//Set background color for the chart
		chart.setBackgroundPaint(Color.white);
		
		//Get a reference to the plot in order to further customize.
		//CategoryPlot uses data from a CategoryDataset and renders each data item.
		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		
		plot.setBackgroundPaint(Color.lightGray);
		
		//Set gradient paint for series
		GradientPaint gpRecovered = new GradientPaint(0.0f, 0.0f, Color.blue, 0.0f, 0.0f, 
				new Color(0, 0, 64));
		renderer.setSeriesPaint(1, gpRecovered);
		GradientPaint gpDeceased = new GradientPaint(0.0f, 0.0f, Color.red, 0.0f, 0.0f, 
				new Color(0, 0, 64));
		renderer.setSeriesPaint(0, gpDeceased);
				
		return chart;
	}
}
