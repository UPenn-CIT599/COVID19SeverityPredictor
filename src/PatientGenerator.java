import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;

/**
 * The KoreaPatientReader class reads in and stores in an arraylist of Patient objects the csv
 * data from the "Data Science for COVID-19 (DSC4)" dataset available on Kaggle.com.
 * 
 * @author cbusc
 *
 */
public class PatientGenerator 
{
	private static ArrayList<Patient> patients;
	private static Map<String, Double> riskfactorToAttributableRisk;
	private static Map<String, Double[]> riskfactorToRangeDeceased; 
	private static Map<String, Double[]> riskfactorToRangeRecovered;
	private static double mortalityRate;
	
	/**
	 * Reads in data from a CSV file and stores in arraylist of 
	 * Patient objects
	 */
	public static void generatePatients()
	{
		//Initialize risk factors and read-in risk and range values associated therewith
		riskfactorToAttributableRisk = RiskFactorReader.getRiskfactorToAttributableRisk();
		riskfactorToRangeDeceased = RiskFactorReader.getRiskfactorToRangeDeceased();
		riskfactorToRangeRecovered = RiskFactorReader.getRiskfactorToRangeRecovered();
		mortalityRate = RiskFactorReader.getMortalityRate();
		patients = new ArrayList<Patient>();
		for (int i = 0; i < 10001; i++)
		{
			//Temporary; will generate these truly at next update
			double age = 0;
			boolean gender = true;
			
		
			//Sets outcome to "deceased" with probability defined as the mortality rate
			//of the predicate published scientific article. Otherwise set to recovered.
			String outcome = "-1";
			if (probableBoolean(mortalityRate))
			{
				outcome = "deceased";
			}
			else { outcome = "recovered"; }
		
			//Generate new data features substantiated to be associated with COVID-19 mortality.
			//Binary data:
			//Eg. if "deceased" this patient will have a 0.8 probability of being rendered a current smoker when initialized
			//Eg. if "released" he would then have a 0.2 probability of being rendered a current smoker when initialized
			//Probability is defined as 0.5 + attributable risk for each individual risk factor
			boolean comorbidity = generateBinaryRiskFactor(outcome, (0.5 + riskfactorToAttributableRisk.get("Comorbidity")));
			boolean currentSmoker = generateBinaryRiskFactor(outcome, (0.5 + riskfactorToAttributableRisk.get("Current smoker")));
			boolean respiratoryRateGreaterThan24 = generateBinaryRiskFactor(outcome, (0.5 + riskfactorToAttributableRisk.get("Respiratory rate > 24")));
			boolean temperatureGreaterThan37 = generateBinaryRiskFactor(outcome, (0.5 + riskfactorToAttributableRisk.get("Temperature > 37.3")));
			boolean groundGlassOpacity = generateBinaryRiskFactor(outcome, (0.5 + riskfactorToAttributableRisk.get("Consolidation on x-ray")));
			
			//Continuous data:
			//Pass in outcome. Based on whether the patient deceased or survived, 
			//assign a randomly generated risk factor value generated from the published range.
			//For example, if "deceased" a patient's white blood cell count will be randomly generated
			//between the range 6.9 - 13.9, versus a "survived" patient generated between 4.3 - 7.7. 
			double wbc, lymphocyteCount, platelets, albumin, lactateDehydrogenase, troponinI, dDimer, ferritin, interleukin6, procalcitonin = -1;
			if (outcome.equals("deceased"))
			{
				wbc = randomBetweenRange(riskfactorToRangeDeceased.get("White blood cell count")[0],
						riskfactorToRangeDeceased.get("White blood cell count")[1]);
				lymphocyteCount = randomBetweenRange(riskfactorToRangeDeceased.get("Lymphocyte count")[0],
						riskfactorToRangeDeceased.get("Lymphocyte count")[1]);
				platelets = randomBetweenRange(riskfactorToRangeDeceased.get("Platelets")[0],
						riskfactorToRangeDeceased.get("Platelets")[1]);
				albumin = randomBetweenRange(riskfactorToRangeDeceased.get("Albumin")[0],
						riskfactorToRangeDeceased.get("Albumin")[1]);
				lactateDehydrogenase = randomBetweenRange(riskfactorToRangeDeceased.get("Lactate Dehydrogenase")[0],
						riskfactorToRangeDeceased.get("Lactate Dehydrogenase")[1]);
				troponinI = randomBetweenRange(riskfactorToRangeDeceased.get("Troponin I")[0],
						riskfactorToRangeDeceased.get("Troponin I")[1]);
				dDimer = randomBetweenRange(riskfactorToRangeDeceased.get("D-dimer")[0],
						riskfactorToRangeDeceased.get("D-dimer")[1]);
				ferritin = randomBetweenRange(riskfactorToRangeDeceased.get("Ferritin")[0],
						riskfactorToRangeDeceased.get("Ferritin")[1]);
				interleukin6 = randomBetweenRange(riskfactorToRangeDeceased.get("Interleukin 6")[0],
						riskfactorToRangeDeceased.get("Interleukin 6")[1]);
				procalcitonin = randomBetweenRange(riskfactorToRangeDeceased.get("Procalcitonin")[0],
						riskfactorToRangeDeceased.get("Procalcitonin")[1]);
			}
			else
			{
				wbc = randomBetweenRange(riskfactorToRangeRecovered.get("White blood cell count")[0], 
						riskfactorToRangeRecovered.get("White blood cell count")[1]);
				lymphocyteCount = randomBetweenRange(riskfactorToRangeRecovered.get("Lymphocyte count")[0], 
						riskfactorToRangeRecovered.get("Lymphocyte count")[1]);
				platelets = randomBetweenRange(riskfactorToRangeRecovered.get("Platelets")[0], 
						riskfactorToRangeRecovered.get("Platelets")[1]);
				albumin = randomBetweenRange(riskfactorToRangeRecovered.get("Albumin")[0],
						riskfactorToRangeRecovered.get("Albumin")[1]);
				lactateDehydrogenase = randomBetweenRange(riskfactorToRangeRecovered.get("Lactate Dehydrogenase")[0], 
						riskfactorToRangeRecovered.get("Lactate Dehydrogenase")[1]);
				troponinI = randomBetweenRange(riskfactorToRangeRecovered.get("Troponin I")[0], 
						riskfactorToRangeRecovered.get("Troponin I")[1]);
				dDimer = randomBetweenRange(riskfactorToRangeRecovered.get("D-dimer")[0],
						riskfactorToRangeRecovered.get("D-dimer")[1]);
				ferritin = randomBetweenRange(riskfactorToRangeRecovered.get("Ferritin")[0], 
						riskfactorToRangeRecovered.get("Ferritin")[1]);
				interleukin6 = randomBetweenRange(riskfactorToRangeRecovered.get("Interleukin 6")[0], 
						riskfactorToRangeRecovered.get("Interleukin 6")[1]);
				//Procalcitonin in recovered patients ranges from 0.1 to 0.1; therefore it is impossible to generate
				//a random value. We simply here use one value, 0.1. 
				procalcitonin = riskfactorToRangeRecovered.get("Procalcitonin")[0];
			}
			
			//Initialize patient object
			Patient patient = new Patient(age, gender, comorbidity, currentSmoker, respiratoryRateGreaterThan24, temperatureGreaterThan37, 
					groundGlassOpacity, wbc, lymphocyteCount, platelets, albumin, lactateDehydrogenase, troponinI, dDimer, ferritin, 
					 interleukin6, procalcitonin, outcome);
			patients.add(patient);
			}
		}
	/**
	 * Gets array list of patients
	 * @return ArrayList patients
	 */
	public static ArrayList<Patient> getPatients() {
		return patients;
	}
	
	/**
	 * Returns true with n probability if the outcome is "deceased,"
	 * and with a 1 - n probability if the outcome is "released." This indicates
	 * that the factor being generated portends a relatively worse prognosis, and is
	 * hence a risk factor. 
	 * @param outcome, probability
	 * @return true or false
	 */
	public static boolean generateBinaryRiskFactor(String outcome, double n)
	{
		if (outcome.equals("deceased"))
		{
			return probableBoolean(n);
		}
		else
		{
			return !probableBoolean(n);
		}
	}
	
	/**
	 * Returns true with the probability of the argument passed
	 * 
	 * @param probability
	 * @return true or false
	 * 
	 */
	public static boolean probableBoolean(double probability)
	{
		return (Math.random() < probability);
	}

	/**
	 * Generates random double between two parameterized values
	 * @param least (min)
	 * @param bound (max)
	 * @return random double
	 */
	public static double randomBetweenRange(Double min, Double max)
	{
		return ThreadLocalRandom.current().nextDouble(min, max);
	}
	
}
