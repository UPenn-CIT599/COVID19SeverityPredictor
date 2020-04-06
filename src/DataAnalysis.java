import java.util.ArrayList;
import java.util.HashMap;

/**
 * DataAnalysis class performs various analytic tests on outcomes data
 * read in from the patient dataset
 * @author cbusc
 *
 */
public class DataAnalysis {
	
	private ArrayList<Patient> patients;
	/**
	 * Constructor which initializes patients array list
	 * @param patients
	 */
	public DataAnalysis(ArrayList<Patient> patients)
	{
		this.patients = patients;
	}
	
	/**
	 * Gets patients
	 * @return patients
	 */
	public ArrayList<Patient> getPatients() {
		return patients;
	}
	
	/**
	 * Maps age as decade to number released, data for population chart
	 * @return decade, numb released
	 */
	public HashMap<String, Integer> getAgeAsDecadeToNumReleased()
	{
	}
	/**
	 * Maps age as decade to number deceased, data for population chart
	 * @return decade, numb deceased
	 */
	public HashMap<String, Integer> getAgeAsDecadeToNumDeceased()
	{
	}
	
	/**
	 * Maps date to number infected, data for chart
	 * @return date, number infected
	 */
	public HashMap<String, Integer> getDateToNumInfected()
	{
	}
	/**
	 * Maps date to no number released, data for chart
	 * @return date, number released
	 */
	public HashMap<String, Integer> getDateToNumReleased()
	{
	}
	/**
	 * Maps date to number deceased, data for chart
	 * @return date, number deceased
	 */
	public HashMap<String, Integer> getDateToNumDeceased()
	{
	}
	
	/**
	 * Maps gender to number released, data for chart
	 * @return gender, number released
	 */
	public HashMap<String, Integer> getGenderToNumberReleased()
	{
	}
	/**
	 * Maps gender to number deceased, data for chart
	 * @return gender, number deceased
	 */
	public HashMap<String, Integer> getGenderToNumberDeceased()
	{
	}
	
	/**
	 * Maps healthcare-exposure to number released, data for chart
	 * @return gender, number released
	 */
	public HashMap<Boolean, Integer> getHealthcareExposureToNumberReleased()
	{
	}
	/**
	 * Maps healthcare-exposure to number deceased, data for chart
	 * @return gender, number deceased
	 */
	public HashMap<Boolean, Integer> getHealthcareExposureToNumberDeceased()
	{
	}
}
