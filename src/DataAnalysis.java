import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * DataAnalysis class performs various analytic tests on outcomes data
 * read in from the patient dataset
 * 
 * 4/23: THIS CLASS IS NOT USED SINCE PATIENT GENERATOR.  
 * 
 * @author cbusc
 *
 */
public class DataAnalysis {
	
	private static ArrayList<Patient> patients;

	/**
	 * Gets patients array list from PatientReader
	 */
	public static void initializePatients()
	{
		patients = PatientGenerator.getPatients();
	}
	
	/**
	 * Maps patient outcome to number with comorbidities
	 *
	 * @return outcomeToNumComorbid
	 */
	public static Map<String, Integer> initOutcomeToNumComorbid()
	{
		Map<String, Integer> outcomeToNumComorbid = new HashMap<>();
		for (Patient p : patients)
		{
			if (p.isComorbid() == true)
			{
				if (outcomeToNumComorbid.containsKey(p.getOutcome()))
				{
					int currNum = outcomeToNumComorbid.get(p.getOutcome());
					outcomeToNumComorbid.put(p.getOutcome(), currNum + 1);
				}
				else
				{
					outcomeToNumComorbid.put(p.getOutcome(), 1);
				}
			}
		}
		return outcomeToNumComorbid;
	}
	
//	/**
//	 * Maps patient outcome to number who had a healthcare-related exposure
//	 * 
//	 * @return outcomeToNumHealthcareRelated
//	 */
//	public static Map<String, Integer> initOutcomeToNumHealthcareRelated()
//	{
//		Map<String, Integer> outcomeToNumHealthcareRelated = new HashMap<>();
//		for (Patient p : patients)
//		{
//			if (p.isHealthcareRelatedExposure() == true)
//			{
//				if (outcomeToNumHealthcareRelated.containsKey(p.getOutcome()))
//				{
//					int currNum = outcomeToNumHealthcareRelated.get(p.getOutcome());
//					outcomeToNumHealthcareRelated.put(p.getOutcome(), currNum + 1);
//				}
//				else
//				{
//					outcomeToNumHealthcareRelated.put(p.getOutcome(), 1);
//				}
//			}
//		}
//		return outcomeToNumHealthcareRelated;
//	}
	
	/**
	 * Maps patient outcome to number of current smokers
	 * 
	 * @return outcomeToNumCurrentSmokers
	 */
	public static Map<String, Double> initOutcomeToNumCurrentSmokers()
	{
		Map<String, Double> outcomeToNumCurrentSmokers = new HashMap<>();
		for (Patient p : patients)
		{
			if (p.isCurrentSmoker() == true)
			{
				if (outcomeToNumCurrentSmokers.containsKey(p.getOutcome()))
				{
					double currNum = outcomeToNumCurrentSmokers.get(p.getOutcome());
					outcomeToNumCurrentSmokers.put(p.getOutcome(), currNum + 1.0);
				}
				else
				{
					outcomeToNumCurrentSmokers.put(p.getOutcome(), 1.0);
				}
			}
		}
		return outcomeToNumCurrentSmokers;
	}
	/**
	 * Maps patient outcome to number of patients with respiratory rate > 24
	 * 
	 * @return outcomeToRespiratoryRate
	 */
	public static Map<String, Double> initOutcomeRespiratoryRate()
	{
		Map<String, Double> outcomeToRespiratoryRate = new HashMap<>();
		for (Patient p : patients)
		{
			if (p.isRespiratoryRateGreaterThan24() == true)
			{
				if (outcomeToRespiratoryRate.containsKey(p.getOutcome()))
				{
					double currNum = outcomeToRespiratoryRate.get(p.getOutcome());
					outcomeToRespiratoryRate.put(p.getOutcome(), currNum + 1.0);
				}
				else
				{
					outcomeToRespiratoryRate.put(p.getOutcome(), 1.0);
				}
			}
		}
		return outcomeToRespiratoryRate;
	}
	
	/**
	 * Maps patient outcome to number of patients with temperature > 37.3
	 * 
	 * @return outcomeToTempGreaterThan37
	 */
	public static Map<String, Double> initOutcomeToTempGreaterThan37()
	{
		Map<String, Double> outcomeToTempGreaterThan37 = new HashMap<>();
		for (Patient p : patients)
		{
			if (p.isTemperatureGreaterThan37() == true)
			{
				if (outcomeToTempGreaterThan37.containsKey(p.getOutcome()))
				{
					double currNum = outcomeToTempGreaterThan37.get(p.getOutcome());
					outcomeToTempGreaterThan37.put(p.getOutcome(), currNum + 1.0);
				}
				else
				{
					outcomeToTempGreaterThan37.put(p.getOutcome(), 1.0);
				}
			}
		}
		return outcomeToTempGreaterThan37;
	}
	
	/**
	 * Maps patient outcome to number of patients with ground glass opacities
	 * found on chest x-ray.
	 * 
	 * @return outcomeToGGO
	 */
	public static Map<String, Double> initOutcomeToGGO()
	{
		Map<String, Double> outcomeToGGO = new HashMap<>();
		for (Patient p : patients)
		{
			if (p.isGroundGlassOpacity() == true)
			{
				if (outcomeToGGO.containsKey(p.getOutcome()))
				{
					double currNum = outcomeToGGO.get(p.getOutcome());
					outcomeToGGO.put(p.getOutcome(), currNum + 1.0);
				}
				else
				{
					outcomeToGGO.put(p.getOutcome(), 1.0);
				}
			}
		}
		return outcomeToGGO;
	}
	
	
//	/**
//	 * Map boolean comorbidity to probability of deceased
//	 * @return
//	 */
//	public static Map<String, Double> getAgeAsDecadeToProbReleased()
//	{
//		//Map age as decade to num released
//		Map<String, Double> ageAsDecadeToNumReleased = initAgeAsDecadeToNumReleased();
//
//		//Map age as decade to num deceased
//		Map<String, Double> ageAsDecadeToNumDeceased = initAgeAsDecadeToNumDeceased();
//
//		//Map age as decade to prob of being released
//		Map<String, Double> ageAsDecadeToProbReleased = new HashMap<>();
//		for (String age1 : ageAsDecadeToNumReleased.keySet())
//		{
//			for (String age2 : ageAsDecadeToNumDeceased.keySet())
//			{
//				if (age1.equals(age2))
//				{
//					Double probReleased = ageAsDecadeToNumReleased.get(age1) / (ageAsDecadeToNumReleased.get(age1) + ageAsDecadeToNumDeceased.get(age2));
//					ageAsDecadeToProbReleased.put(age1, probReleased);
//				}
//			}
//		}
//		return ageAsDecadeToProbReleased;
//	}
//
//	/**
//	 * Maps age as decade to probability of being deceased
//	 * 
//	 * @return decade, numb deceased
//	 */
//	public static Map<String, Double> getAgeAsDecadeToProbDeceased()
//	{
//		//Set default hashmap to age as decade to prob released, then subtract probability 
//		//from 1 to get probability of being deceased
//		Map<String, Double> ageAsDecadeToProbDeceased = new HashMap<>();
//		Map<String, Double> ageAsDecadeToProbReleased = getAgeAsDecadeToProbReleased();
//		for (String age : ageAsDecadeToProbReleased.keySet())
//		{
//			ageAsDecadeToProbDeceased.put(age, 1.0 - ageAsDecadeToProbReleased.get(age));
//		}
//		return ageAsDecadeToProbDeceased;
//	}
//
//	/**
//	 * Helper method that initializes a hashmap linking age as decade to 
//	 * number of patients released
//	 * 
//	 * @return ageAsDecadeToNumReleased
//	 */
//	public static Map<String, Double> initAgeAsDecadeToNumReleased()
//	{
//		Map<String, Double> ageAsDecadeToNumReleased = new HashMap<>();
//		for (Patient p : patients)
//		{
//			if (!p.getAgeAsDecade().equals(""))
//			{
//				if (ageAsDecadeToNumReleased.containsKey(p.getAgeAsDecade()))
//				{
//					if (p.getOutcome().equals("recovered"))
//					{
//						Double currReleased = ageAsDecadeToNumReleased.get(p.getAgeAsDecade());
//						ageAsDecadeToNumReleased.put(p.getAgeAsDecade(), currReleased + 1.0);
//					}
//				}			
//				else 
//				{
//					if (p.getOutcome().equals("recovered"))
//					{
//						ageAsDecadeToNumReleased.put(p.getAgeAsDecade(), 1.0);
//					}
//				}
//			}
//		}
//		return ageAsDecadeToNumReleased;
//	}
//	/**
//	 * Helper method that initializes a hashmap linking age as decade to 
//	 * number of patients deceased
//	 * @return ageAsDecadeToNumDeceased
//	 */
//	public static Map<String, Double> initAgeAsDecadeToNumDeceased()
//	{
//		Map<String, Double> ageAsDecadeToNumDeceased = new HashMap<>();
//		for (Patient p : patients)
//		{
//			if (!p.getAgeAsDecade().equals(""))
//			{
//				if (ageAsDecadeToNumDeceased.containsKey(p.getAgeAsDecade()))
//				{
//					if (p.getOutcome().equals("deceased"))
//					{
//						Double currDeceased = ageAsDecadeToNumDeceased.get(p.getAgeAsDecade());
//						ageAsDecadeToNumDeceased.put(p.getAgeAsDecade(), currDeceased + 1.0);
//					}
//				}
//				else
//				{	
//					if (p.getOutcome().equals("deceased"))   //Set first value to 1 if deceased
//					{
//						ageAsDecadeToNumDeceased.put(p.getAgeAsDecade(), 1.0);
//					}
//					else    //Set first value to 0 for age groups without any deceased to prevent null value;
//					{
//						ageAsDecadeToNumDeceased.put(p.getAgeAsDecade(), 0.0);
//					}
//				}
//			}
//		}		
//		return ageAsDecadeToNumDeceased;
//	}
	
//	/**
//	 * Helper method that initializes a hashmap linking age boolean comorbidity
//	 * to number deceased.
//	 * @return ageAsDecadeToNumDeceased
//	 */
//	public static Map<Boolean, Double> initComorbidityToNumDeceased()
//	{
//		Map<Boolean, Double> comorbidityToNumDeceased = new HashMap<>();
//		for (Patient p : patients)
//		{
//			if (comorbidityToNumDeceased.containsKey(p.isComorbid()))
//			{
//				if (p.getOutcome().equals("deceased"))
//				{
//					Double currDeceased = comorbidityToNumDeceased.get(p.isComorbid());
//					comorbidityToNumDeceased.put(p.isComorbid(), currDeceased + 1.0);
//				}
//			}
//			else
//			{
//				if (p.getOutcome().equals("deceased"))
//				{
//					comorbidityToNumDeceased.put(p.isComorbid(), 1.0);
//				}
//			}
//		}
//		return comorbidityToNumDeceased;
//	}
//	
//	public static Map<Boolean, Double> initComorbidityToNumRecovered()
//	{
//		Map<Boolean, Double> comorbidityToNumRecovered = new HashMap<>();
//		for (Patient p : patients)
//		{
//			if (comorbidityToNumRecovered.containsKey(p.isComorbid()))
//			{
//				if (p.getOutcome().equals("recovered"))
//				{
//					Double currDeceased = comorbidityToNumRecovered.get(p.isComorbid());
//					comorbidityToNumRecovered.put(p.isComorbid(), currDeceased + 1.0);
//				}
//			}
//			else
//			{
//				if (p.getOutcome().equals("recovered"))
//				{
//					comorbidityToNumRecovered.put(p.isComorbid(), 1.0);
//				}
//			}
//		}
//		return comorbidityToNumRecovered;
//	}

}
