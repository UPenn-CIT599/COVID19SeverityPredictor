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
		//Initializes patients arraylist in the data analysis class
		DataAnalysis.initializePatients();
		WriteToCSV.write();
	}
}