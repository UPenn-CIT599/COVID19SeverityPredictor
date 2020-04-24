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
public class BarChart extends ApplicationFrame 
{
	/**
	 * Creates a new BarChart instance
	 * 
	 * @param title (the frame title)
	 */
	public BarChart(String title)
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
		Map<String, Double> riskfactorToRelativeRisk = RiskFactorReader.getRiskfactorToRelativeRisk();
		Map<String, Double> riskfactorToAttributableRisk = RiskFactorReader.getRiskfactorToAttributableRisk();
		//Row keys
		String rr = "Relative Risk";
		String ar = "Attributable Risk";
		
		//Column keys are the mortality risk factors, stored as the hash map keys
		String comorbidity = "Comorbidity";
		String currentSmoker = "Current smoker";
		String rrGreaterThan24 = "Respiratory rate > 24";
		String tempGreaterThan37 = "Temperature > 37.3";
		String xray = "Consolidation on x-ray";
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		//Series1: risk factor to relative risk of mortality
		dataset.addValue((riskfactorToRelativeRisk.get(comorbidity)), rr, comorbidity);
		dataset.addValue((riskfactorToRelativeRisk.get(currentSmoker)), rr, currentSmoker);
		dataset.addValue((riskfactorToRelativeRisk.get(rrGreaterThan24)), rr, rrGreaterThan24);
		dataset.addValue((riskfactorToRelativeRisk.get(tempGreaterThan37)), rr, tempGreaterThan37);
		dataset.addValue((riskfactorToRelativeRisk.get(xray)), rr, xray);
		
		//Series2: risk factor to attributable risk of mortality
//		dataset.addValue(riskfactorToAttributableRisk.get(comorbidity), ar, comorbidity);
//		dataset.addValue(riskfactorToAttributableRisk.get(currentSmoker), ar, currentSmoker);
//		dataset.addValue(riskfactorToAttributableRisk.get(rrGreaterThan24), ar, rrGreaterThan24);
//		dataset.addValue(riskfactorToAttributableRisk.get(tempGreaterThan37), ar, tempGreaterThan37);
//		dataset.addValue(riskfactorToAttributableRisk.get(xray), ar, xray);
		
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
			"Relative Risk of COVID19 Mortality at Day of Hospital Admission",  			//chart title
			"Risk Factor",    					   				  //domain axis label
			"Relative Risk of Mortality",		  				//range axis label
			dataset, 												  //data
			PlotOrientation.VERTICAL,								  //orientation
			false, 													  //include legend
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

//	public static void main(String[] args)
//	{
//		RiskFactorReader.readCSV();
//		PatientGenerator.generatePatients();
//		
//		//Create a chart
//		StackedBarChart chart1 = new StackedBarChart("Risk Factors at Admission and Probability of COVID-19 Mortality");
//		//Use .pack() and .setVisible() to visualize the chart. These methods are inherited from Window. 
//		chart1.pack();
//		RefineryUtilities.centerFrameOnScreen(chart1);
//		chart1.setVisible(true);
//		
//		BarChart2 chart2 = new BarChart2("Age Versus Probability of Mortality");
//		chart2.pack();
//		RefineryUtilities.centerFrameOnScreen(chart2);
//		chart2.setVisible(true);
//	}
}
