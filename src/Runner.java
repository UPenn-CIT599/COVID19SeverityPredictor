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
		PatientReaderKorea.readCSV();
		DataAnalysis d = new DataAnalysis(PatientReaderKorea.getPatients());
	}
}
