/**
 * Driver class for the COVID19 risk predictor
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
		WriteToCSV w = new WriteToCSV(d);
		w.write();
	}
}
