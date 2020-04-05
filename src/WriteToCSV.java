import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * WriteToCSV is responsible for writing processed data to a .csv file
 * @author cbusc
 *
 */
public class WriteToCSV {
	
	private DataAnalysis analysis;

	public WriteToCSV(DataAnalysis analysis)
	{
		this.analysis = analysis;
	}
	/**
	 * Writes out pertinent data to .csv file in 
	 * preparation for classification
	 */
	public void write()
	{
		try
		{
			File f = new File("PatientInfoClean");
			PrintWriter out = new PrintWriter(f);
			
			//Method body..
			
			out.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
