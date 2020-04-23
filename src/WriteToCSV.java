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
			ArrayList<Patient> patients = PatientReader.getPatients();
			out.print("Gender," + "Age," + "AgeAsDecade," + "Comorbid," + "HealthcareRelatedExposure,"
					+ "CurrentSmoker," + "RespiratoryRateGreaterThan24," + "TemperatureGreaterThan37," + "WBC," + 
					"LymphocyteCount," + "Platelets," + "LactateDehydrogenase," + "TroponinI," + "Ferritin" +
					"Interleukin6," + "GroundGlassOpacity,"+ "Outcome\n");
			for (Patient p : patients)
			{
				out.print(p.getGender() + "," + p.getAge() + "," + p.getAgeAsDecade() + "," + p.isComorbid() + "," + p.isHealthcareRelatedExposure()
				+ "," + p.isCurrentSmoker() + "," + p.isRespiratoryRateGreaterThan24() + "," + p.isTemperatureGreaterThan37() + 
				"," + p.getWbc() + "," + p.getLymphocyteCount() + "," + p.getPlatelets() + "," + p.getLactateDehydrogenase() + "," + p.getTroponinI() + ","
				+ p.getFerritin() + "," + p.getInterleukin6() + "," + p.isGroundGlassOpacity() + "," + p.getOutcome() + "\n");
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
