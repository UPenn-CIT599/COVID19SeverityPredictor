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
		PatientGenerator.generatePatients();
		DataAnalysis.initializePatients();
		WriteToCSV.write();

		BarChart1 chart = new BarChart1("Relationship Between Patient Characteristics at Admission and COVID-19 Mortality");
		//BarChart inherits .pack() and .setVisible() from the Window class
		chart.pack();
		RefineryUtilities.centerFrameOnScreen(chart);
		chart.setVisible(true);
	}
}