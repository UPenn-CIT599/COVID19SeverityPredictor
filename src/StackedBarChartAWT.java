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
public class StackedBarChart_AWT extends ApplicationFrame
{
	//Constructor, which implements ChartPanel for interfacing with JSwing
	public StackedBarChart_AWT(String applicationTitle, String chartTitle)
	{
		super(applicationTitle);
		JFreeChart stackedBarChart = ChartFactory.createStackedBarChart(
				chartTitle,     //title
				"Age",      //category (X) axis
				"Number of patients",      //value (Y) axis
				createDataset(),
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
	private CategoryDataset createDataset()
	{
		//Initialize category names
			final String ISOLATED = "Isolated";
			final String RELEASED = "Released";
			final String DECEASED = "Deceased";
			
			final String zeroToNine = "0-9";
			final String tenToNineteen = "10-19";
			final String twentyToTwentynine = "20-29";
			final String thirtyToThirtynine = "30-39";
			
			final DefaultCategoryDataset DATASET = new DefaultCategoryDataset();
			//Iterate through HashMap of age as decade to num patients with outcome
			//value = numPatients with outcome in age bracket
			//rowKey = ageAsDecade
			//columnKey = outcome
			
			//Example values provided below
			//Iterate through HashMap ageAsDecadeToNumIsolated	
			DATASET.addValue(20, ISOLATED, thirtyToThirtynine);
			DATASET.addValue(10, ISOLATED, twentyToTwentynine);
			DATASET.addValue(20, ISOLATED, tenToNineteen);
			DATASET.addValue(30, ISOLATED, zeroToNine);
			
			//Iterate through HashMap ageAsDecadeToNumReleased	
			DATASET.addValue(20, RELEASED, thirtyToThirtynine);
			DATASET.addValue(4, RELEASED, twentyToTwentynine);
			DATASET.addValue(80, RELEASED, tenToNineteen);
			DATASET.addValue(50, RELEASED, zeroToNine);
			
			//Iterate through HashMap ageAsDecadeToNumDeceased
			DATASET.addValue(20, DECEASED, thirtyToThirtynine);
			DATASET.addValue(20, DECEASED, twentyToTwentynine);
			DATASET.addValue(20, DECEASED, tenToNineteen);
			DATASET.addValue(10, DECEASED, zeroToNine);
		
		return DATASET;
	}
}