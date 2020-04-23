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
	 * Responsible for creation of bar charts to be implemented in 
	 * JSwing. ApplicationFrame is a subclass of javax.swing.JFrame.
	 * @author cbusc
	 *
	 */
public class BarChart1 extends ApplicationFrame 
{
	/**
	 * Creates a new BarChart instance
	 * 
	 * @param title (the frame title)
	 */
	public BarChart1(String title)
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
	 * Returns a category dataset. 
	 * 
	 * CategoryDataset is an interface for a dataset with one or more series (eg. deceased or 
	 * recovered), and values (positive/negative findings) associated with categories (temp>24 degrees, 
	 * ground glass opacity seen on xray, etc.).
	 * 
	 * @return a dataset implementing the CategoryDataset interface
	 */
	private static CategoryDataset createDataset()
	{
		//Row keys
		String deceased = "Deceased";
		String recovered = "Recovered";
		
		//Column keys
		String comorbidity = "Comorbidity";
		String healthcareRelated = "Healthcare worker";
		String currentSmoker = "Current smoker";
		String rrGreaterThan24 = "Respiratory Rate > 24";   //respiratory rate; respirations per minute
		String tempGreaterThan37 = "T > 37.3 °C";   //degrees celsius
		String ggoOnCXR = "GGO on chest x-ray";   //ground glass opacity
		
		//Create the dataset by iterating through DataAnalysis hashmaps to enumerate patients with positive
		//findings for each row key in each category. 
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		//Series1
		dataset.addValue(DataAnalysis.initOutcomeToNumComorbid().get("deceased"), deceased, comorbidity);
		dataset.addValue(DataAnalysis.initOutcomeToNumHealthcareRelated().get("deceased"), deceased, healthcareRelated);
		dataset.addValue(DataAnalysis.initOutcomeToNumCurrentSmokers().get("deceased"), deceased, currentSmoker);
		dataset.addValue(DataAnalysis.initOutcomeRespiratoryRate().get("deceased"), deceased, rrGreaterThan24);
		dataset.addValue(DataAnalysis.initOutcomeToTempGreaterThan37().get("deceased"), deceased, tempGreaterThan37);
		
		//Series2
		dataset.addValue(DataAnalysis.initOutcomeToNumComorbid().get("recovered"), recovered, comorbidity);
		dataset.addValue(DataAnalysis.initOutcomeToNumHealthcareRelated().get("recovered"), recovered, healthcareRelated);
		dataset.addValue(DataAnalysis.initOutcomeToNumCurrentSmokers().get("recovered"), recovered, currentSmoker);
		dataset.addValue(DataAnalysis.initOutcomeRespiratoryRate().get("recovered"), recovered, rrGreaterThan24);
		dataset.addValue(DataAnalysis.initOutcomeToTempGreaterThan37().get("recovered"), recovered, tempGreaterThan37);
		
		return dataset;
		
	}
	
	/**
	 * Creates a bar chart given a parameterized dataset
	 * @param dataset
	 * @return bar chart
	 */
	private static JFreeChart createChart(CategoryDataset dataset)
	{
		JFreeChart chart = ChartFactory.createBarChart(
			"Relationship Between Patient Characteristics at Admission and COVID-19 Mortality",  			//chart title
			"Patient Feature",    					   				  //domain axis label
			"Number Of Patients With Feature",		  				//range axis label
			dataset, 												  //data
			PlotOrientation.VERTICAL,								  //orientation
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

	public static void main(String[] args)
	{
		RiskFactorReader.readCSV();
		PatientGenerator.generatePatients();
		DataAnalysis.initializePatients();
		
		//Create a chart
		BarChart1 chart1 = new BarChart1("Relationship Between Patient Characteristics at Admission and COVID-19 Mortality");
		//Use .pack() and .setVisible() to visualize the chart. These methods are inherited from Window. 
		chart1.pack();
		RefineryUtilities.centerFrameOnScreen(chart1);
		chart1.setVisible(true);
		
//		BarChart2 chart2 = new BarChart2("Age Versus Probability of Mortality");
//		chart2.pack();
//		RefineryUtilities.centerFrameOnScreen(chart2);
//		chart2.setVisible(true);
	}
}
