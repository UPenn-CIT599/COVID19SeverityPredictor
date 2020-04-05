import java.util.ArrayList;

/**
 * DataAnalysis class performs various analytic tests on data
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
	 * Gets released patients
	 * @return releasedPatients
	 */
	public ArrayList<Patient> getReleasedPatients()
	{
		ArrayList<Patient> releasedPatients = new ArrayList<>();
		for (Patient p : patients)
		{
			if (p.getState().equals("released"))
			{
				releasedPatients.add(p);
			}
		}
		return releasedPatients;
	}
	/**
	 * Gets deceased patients
	 * @return deceasedPatients
	 */
	public ArrayList<Patient> getDeceasedPatients()
	{
		ArrayList<Patient> deceasedPatients = new ArrayList<>();
		for (Patient p : patients)
		{
			if (p.getState().equals("deceased"))
			{
				deceasedPatients.add(p);
			}
		}
		return deceasedPatients;
	}

	/**
	 * Utility method to calculate mean age
	 * @param pArray
	 * @return
	 */
	public static double getAverageAge(ArrayList<Patient> pArray) 
	{
		double sum = 0;
		int count = 0;         
		for (Patient p : pArray)
		{
			sum+= p.getAge();
			count++;
		}
		return sum / count;
	}
}
