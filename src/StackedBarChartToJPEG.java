import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class StackedBarChartToJPEG
{
	/**
	 * Writes a stacked bar chart to JPEG file
	 */
	public static void writeToJPEG()
	{		
		Map<String, Double> riskfactorToAttributableRisk = RiskFactorReader.getRiskfactorToAttributableRisk();
		//Row keys
		String deceased = "Deceased";
		String recovered = "Recovered";
		
		//Column keys are the mortality risk factors, stored as the hash map keys
		String comorbidity = "Comorbidity";
		String currentSmoker = "Current smoker";
		String rrGreaterThan24 = "Respiratory rate > 24";
		String tempGreaterThan37 = "Temperature > 37.3";
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		//Series1: risk factor to probability of death
		dataset.addValue((0.5 + riskfactorToAttributableRisk.get(comorbidity)), deceased, comorbidity);
		dataset.addValue((0.5 + riskfactorToAttributableRisk.get(currentSmoker)), deceased, currentSmoker);
		dataset.addValue((0.5 + riskfactorToAttributableRisk.get(rrGreaterThan24)), deceased, rrGreaterThan24);
		dataset.addValue((0.5 + riskfactorToAttributableRisk.get(tempGreaterThan37)), deceased, tempGreaterThan37);
		
		//Series2: risk factor to probability of recovery
		dataset.addValue(1 - (0.5 + riskfactorToAttributableRisk.get(comorbidity)), recovered, comorbidity);
		dataset.addValue(1 - ( 0.5 + riskfactorToAttributableRisk.get(currentSmoker)), recovered, currentSmoker);
		dataset.addValue(1 - (0.5 + riskfactorToAttributableRisk.get(rrGreaterThan24)), recovered, rrGreaterThan24);
		dataset.addValue(1 - ( 0.5 + riskfactorToAttributableRisk.get(tempGreaterThan37)), recovered, tempGreaterThan37);
		
		JFreeChart stackedBarChart = ChartFactory.createStackedBarChart(
				"COVID19 Pandemic",     //title
				"Age",      //category (X) axis
				"Probability of Outcome",      //value (Y) axis
				dataset,
				PlotOrientation.VERTICAL,
				true, true, false);       //legends, tooltips, URLS
		int width = 600;
		int height = 500;
		File f = new File("StackedBarChart.jpeg");
		try {
			ChartUtilities.saveChartAsJPEG(f, stackedBarChart, width, height);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}