import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * WriteToCSV is responsible for writing processed data to a .csv file
 * @author cbusc
 *
 */
public class WriteToCSV {
	/**
	 * Writes out pertinent data to .csv file in 
	 * preparation for classification
	 */
	public static void write()
	{
		try
		{
			FileWriter fw = new FileWriter("PatientInfoClean.csv");
			PrintWriter out = new PrintWriter(fw);
			
			//Get patient ArrayList
			ArrayList<Patient> patients = PatientGenerator.getPatients();
			out.print("Age," + "Gender," + "Comorbid,"
					+ "CurrentSmoker," + "RespiratoryRateGreaterThan24," + "TemperatureGreaterThan37," + "GroundGlassOpacity," + "WBC," + 
					"LymphocyteCount," + "Platelets," + "Albumin," + "LactateDehydrogenase," + "TroponinI," + "D-dimer," + "Ferritin," +
					"Interleukin6," + "Procalcitonin," + "Outcome\n");
			for (Patient p : patients)
			{
				out.print(p.getAge() + "," + p.getGender() + "," + p.isComorbid()
				+ "," + p.isCurrentSmoker() + "," + p.isRespiratoryRateGreaterThan24() + "," + p.isTemperatureGreaterThan37() + "," + p.isGroundGlassOpacity() + ","
				+ p.getWbc() + "," + p.getLymphocyteCount() + "," + p.getPlatelets() + "," + p.getAlbumin() + "," + p.getLactateDehydrogenase() 
				+ "," + p.getTroponinI() + "," + p.getdDimer() + "," + p.getFerritin() + "," + p.getInterleukin6() + "," + p.getProcalcitonin() + ","
				 + p.getOutcome() + "\n");
			}	
			out.flush();
			out.close();
			System.out.println("You have written cleaned data to 'PatientInfoClean.csv'");           //TEST CODE
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
