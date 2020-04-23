import org.jfree.ui.RefineryUtilities;

/**
 * Driver class for the COVID19 prognosticator
 * @author cbusc
 *
 */
public class Runner {
	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args)
	{
		RiskFactorReader.readCSV();
		PatientReader.readCSV();
		DataAnalysis.initializePatients();
		WriteToCSV.write();

		BarChart1 chart = new BarChart1("Relationship Between Patient Characteristics at Admission and COVID-19 Mortality");
		//BarChart inherits .pack() and .setVisible() from the Window class
		chart.pack();
		RefineryUtilities.centerFrameOnScreen(chart);
		chart.setVisible(true);
		
		BarChart2 chart2 = new BarChart2("Age Versus Probability of Mortality");
		chart2.pack();
		RefineryUtilities.centerFrameOnScreen(chart2);
		chart2.setVisible(true);
	}
}