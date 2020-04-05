import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * WriteToCSV is responsible for writing processed data to a .csv file
 * @author cbusc
 *
 */
public class WriteToCSV {
	
	//Declare instance variable
	private DataAnalysis analysis;
	
	//Constructor
	public WriteToCSV(DataAnalysis analysis)
	{
		this.analysis = analysis;
	}
	/**
	 * Writes out pertinent data to .csv file in 
	 * preparation for machine learning classification
	 */
	public void write()
	{
		try
		{
			File f = new File("PatientInfoDistilled");
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
