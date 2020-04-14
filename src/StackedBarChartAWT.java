import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
/**
 * StackedBarChart_AWT is responsible for creating stacked bar charts from categorical data
 * @author cbusc
 *
 */
public class StackedBarChartAWT extends ApplicationFrame
{
	//Constructor, which implements ChartPanel for interfacing with JSwing
	public StackedBarChartAWT(String applicationTitle, String chartTitle, DataAnalysis d)
	{
		super(applicationTitle);
		JFreeChart stackedBarChart = ChartFactory.createStackedBarChart(
				chartTitle,     //title
				"Age",      //category (X) axis
				"Probability of Outcome",      //value (Y) axis
				createDataset(d),
				PlotOrientation.HORIZONTAL,
				true, true, false);       //legends, tooltips, URLS
			
			//Chartpanel is a Swing GUI component for displaying a JFreeChart object
			ChartPanel chartPanel = new ChartPanel(stackedBarChart);    
			chartPanel.setPreferredSize(new java.awt.Dimension(500, 380));
			setContentPane(chartPanel);  	//Method inherited from JFrame to set content pane
	}
	
	/**
	 * Creates dataset to be displayed in stacked bar chart by collaborating with 
	 * Data Analysis class
	 * @return CategoryDataset
	 */
	private CategoryDataset createDataset(DataAnalysis d)
	{	
			final DefaultCategoryDataset DATASET = new DefaultCategoryDataset();
			//value = probability of outcome
			//rowKey = outcome
			//columnKey = ageAsDecade
			
			//Iterate through hashmap to fill released dataset
			Map<String, Double> ageAsDecadeToProbReleased = d.getAgeAsDecadeToProbReleased();
			for (String age : ageAsDecadeToProbReleased.keySet())
			{
				DATASET.addValue(ageAsDecadeToProbReleased.get(age), "Released", age);   //value, row key, column key
			}
			
			//Iterate through hashmap to fill deceased dataset
			Map<String, Double> ageAsDecadeToProbDeceased = d.getAgeAsDecadeToProbDeceased();
			for (String age : ageAsDecadeToProbDeceased.keySet())
			{
				DATASET.addValue(ageAsDecadeToProbDeceased.get(age), "Deceased", age);
			}
		return DATASET;
	}
}