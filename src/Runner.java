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
		DataAnalysis d = new DataAnalysis(PatientReader.getPatients());
		//System.out.println(PatientReaderKorea.countCompleteRows());
		WriteToCSV w = new WriteToCSV(d);
		w.write();
	}
}