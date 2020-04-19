import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
	 * Maps age as decade to probability of being released
	 * @return ageAsDecadeToProbReleased
	 */
	public Map<String, Double> getAgeAsDecadeToProbReleased()
	{
		//Map age as decade to num released
		Map<String, Double> ageAsDecadeToNumReleased = initAgeAsDecadeToNumReleased();

		//Map age as decade to num deceased
		Map<String, Double> ageAsDecadeToNumDeceased = initAgeAsDecadeToNumDeceased();

		//Map age as decade to prob of being released
		Map<String, Double> ageAsDecadeToProbReleased = new HashMap<>();
		for (String age1 : ageAsDecadeToNumReleased.keySet())
		{
			for (String age2 : ageAsDecadeToNumDeceased.keySet())
			{
				if (age1.equals(age2))
				{
					Double probReleased = ageAsDecadeToNumReleased.get(age1) / (ageAsDecadeToNumReleased.get(age1) + ageAsDecadeToNumDeceased.get(age2));
					ageAsDecadeToProbReleased.put(age1, probReleased);
				}
			}
		}
		return ageAsDecadeToProbReleased;
	}
	/**
	 * Maps age as decade to probability of being deceased
	 * @return decade, numb deceased
	 */
	public Map<String, Double> getAgeAsDecadeToProbDeceased()
	{
		//Set default hashmap to age as decade to prob released, then subtract probability 
		//from 1 to get probability of being deceased
		Map<String, Double> ageAsDecadeToProbDeceased = new HashMap<>();
		Map<String, Double> ageAsDecadeToProbReleased = getAgeAsDecadeToProbReleased();
		for (String age : ageAsDecadeToProbReleased.keySet())
		{
			ageAsDecadeToProbDeceased.put(age, 1.0 - ageAsDecadeToProbReleased.get(age));
		}
		return ageAsDecadeToProbDeceased;
	}
	
	/**
	 * Maps gender to number released, data for chart
	 * @return gender, number released
	 */
	public Map<String, Integer> getGenderToNumberReleased()
	{
		Map<String, Integer> h = new HashMap<>();
		return h;
	}
	/**
	 * Maps gender to number deceased, data for chart
	 * @return gender, number deceased
	 */
	public Map<String, Integer> getGenderToNumberDeceased()
	{
		Map<String, Integer> h = new HashMap<>();
		return h;
	}
	
	/**
	 * Maps healthcare-exposure to number released, data for chart
	 * @return gender, number released
	 */
	public Map<Boolean, Integer> getHealthcareExposureToNumberReleased()
	{
		Map<Boolean, Integer> h = new HashMap<>();
		return h;
	}
	/**
	 * Maps healthcare-exposure to number deceased, data for chart
	 * @return gender, number deceased
	 */
	public Map<Boolean, Integer> getHealthcareExposureToNumberDeceased()
	{
		Map<Boolean, Integer> h = new HashMap<>();
		return h;
	}
	/**
	 * Helper method that initializes a hashmap linking age as decade to 
	 * number of patients released
	 * @return ageAsDecadeToNumReleased
	 */
	public Map<String, Double> initAgeAsDecadeToNumReleased()
	{
		Map<String, Double> ageAsDecadeToNumReleased = new HashMap<>();
		for (Patient p : patients)
		{
			if (!p.getAgeAsDecade().equals(""))
			{
				if (ageAsDecadeToNumReleased.containsKey(p.getAgeAsDecade()))
				{
					if (p.getState().equals("released"))
					{
						Double currReleased = ageAsDecadeToNumReleased.get(p.getAgeAsDecade());
						ageAsDecadeToNumReleased.put(p.getAgeAsDecade(), currReleased + 1.0);
					}
				}			
				else 
				{
					if (p.getState().equals("released"))
					{
						ageAsDecadeToNumReleased.put(p.getAgeAsDecade(), 1.0);
					}
				}
			}
		}
		return ageAsDecadeToNumReleased;
	}
	/**
	 * Helper method that initializes a hashmap linking age as decade to 
	 * number of patients deceased
	 * @return ageAsDecadeToNumDeceased
	 */
	public Map<String, Double> initAgeAsDecadeToNumDeceased()
	{
		Map<String, Double> ageAsDecadeToNumDeceased = new HashMap<>();
		for (Patient p : patients)
		{
			if (!p.getAgeAsDecade().equals(""))
			{
				if (ageAsDecadeToNumDeceased.containsKey(p.getAgeAsDecade()))
				{
					if (p.getState().equals("deceased"))
					{
						Double currDeceased = ageAsDecadeToNumDeceased.get(p.getAgeAsDecade());
						ageAsDecadeToNumDeceased.put(p.getAgeAsDecade(), currDeceased + 1.0);
					}
				}
				else
				{	
					if (p.getState().equals("deceased"))   //Set first value to 1 if deceased
					{
						ageAsDecadeToNumDeceased.put(p.getAgeAsDecade(), 1.0);
					}
					else    //Set first value to 0 for age groups without any deceased to prevent null value;
					{
						ageAsDecadeToNumDeceased.put(p.getAgeAsDecade(), 0.0);
					}
				}
			}
		}		
		return ageAsDecadeToNumDeceased;
	}
}
