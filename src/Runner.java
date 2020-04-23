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
		System.out.println(DataAnalysis.initOutcomeToNumCurrentSmokers().get("deceased"));
		System.out.println(DataAnalysis.initOutcomeToNumCurrentSmokers().get("recovered"));
		//WriteToCSV.write();
	}
}