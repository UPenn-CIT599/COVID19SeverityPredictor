//import java.io.File;
//import java.util.Map;
//
//import org.jfree.chart.ChartFactory;
//import org.jfree.chart.ChartPanel;
//import org.jfree.chart.ChartUtilities;
//import org.jfree.chart.JFreeChart;
//import org.jfree.chart.plot.CategoryPlot;
//import org.jfree.chart.plot.PlotOrientation;
//import org.jfree.data.category.CategoryDataset;
//import org.jfree.data.category.DefaultCategoryDataset;
//import org.jfree.data.general.DatasetUtilities;
//import org.jfree.ui.ApplicationFrame;
//import org.jfree.ui.RefineryUtilities;

// 4/23: NEEDS UPDATE GIVEN CATEGORICAL AGE DATA WAS REMOVED
//
//public class BarChart2jpeg
//{
//	public static void main(String[] args) throws Exception
//	{
//		System.out.println("You have wrote to 'StackedBarChart.jpeg'");
//		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//		//Iterate through HashMap of age as decade to num patients with outcome
//		//value = numPatients with outcome in age bracket
//		//rowKey = outcome
//		//columnKey = ageAsDecade
//		
//		RiskFactorReader.readCSV();
//		PatientReader.readCSV();
//		DataAnalysis.initializePatients();
//		
//
//		//Iterate through HashMap: ageAsDecadeToNumReleased	
//		Map<String, Double> ageAsDecadeToProbReleased = DataAnalysis.getAgeAsDecadeToProbReleased();
//		for (String age : ageAsDecadeToProbReleased.keySet())
//		{
//			dataset.addValue(ageAsDecadeToProbReleased.get(age), "Recovered", age);
//		}
//		
//		//Iterate through HashMap: ageAsDecadeToNumDeceased
//		Map<String, Double> ageAsDecadeToProbDeceased = DataAnalysis.getAgeAsDecadeToProbDeceased();
//		for (String age : ageAsDecadeToProbDeceased.keySet())
//		{
//			dataset.addValue(ageAsDecadeToProbDeceased.get(age), "Deceased", age);
//		}
//		
//		
//		JFreeChart stackedBarChart = ChartFactory.createStackedBarChart(
//				"COVID19 Pandemic",     //title
//				"Age",      //category (X) axis
//				"Probability of Outcome",      //value (Y) axis
//				dataset,
//				PlotOrientation.VERTICAL,
//				true, true, false);       //legends, tooltips, URLS
//		int width = 600;
//		int height = 500;
//		File f = new File("StackedBarChart.jpeg");
//		ChartUtilities.saveChartAsJPEG(f, stackedBarChart, width, height);
//	}
//}