import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
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
	/**
	 * Reads in data from a CSV file and stores in arraylist of 
	 * Patient objects
	 */
	public static void generatePatients()
	{
		patients = new ArrayList<Patient>();
		for (int i = 0; i < 10001; i++)
		{
			//Temporary; will generate these truly at next update
			double age = 0;
			boolean gender = true;
			
		
			//Sets outcome to "deceased" with probability defined as the mortality rate
			//of the predicate scientific article. Otherwise set to recovered.
			String outcome = "-1";
			if (probableBoolean(RiskFactorReader.getMortalityRate()))
			{
				outcome = "deceased";
			}
			else { outcome = "recovered"; }
		
			//Generate new data features substantiated to be associated with COVID-19 mortality
			//Binary data:
			//If "deceased" this patient will have a 0.8 probability of being rendered a current smoker when initialized
			//If "released" he will have a 0.2 probability of being rendered a current smoker when initialized
			boolean comorbidity = generateBinaryRiskFactor(outcome, 0.79);
			boolean healthcareRelatedExposure = generateBinaryRiskFactor(outcome, 0.41);
			boolean currentSmoker = generateBinaryRiskFactor(outcome, 0.6);
			boolean respiratoryRateGreaterThan24 = generateBinaryRiskFactor(outcome, 0.72);
			boolean temperatureGreaterThan37 = generateBinaryRiskFactor(outcome, 0.66);
			boolean groundGlassOpacity = generateBinaryRiskFactor(outcome, 0.71);
			
			//Continuous data:
			//Pass in outcome. Based on whether the patient deceased or survived, 
			//assign a randomly generated risk factor value generated from the published range.
			//For example, if "deceased" a patient's white blood cell count will be randomly generated
			//between the range 6.9 - 13.9, versus a "survived" patient generated between 4.3 - 7.7. 
			double wbc, lymphocyteCount, platelets, albumin, lactateDehydrogenase, troponinI, dDimer, ferritin, interleukin6 = -1;
			if (outcome.equals("deceased"))
			{
				wbc = randomBetweenRange(RiskFactorReader.getRiskfactorToRangeDeceased().get("White blood cell count")[0],
						RiskFactorReader.getRiskfactorToRangeDeceased().get("White blood cell count")[1]);
				lymphocyteCount = randomBetweenRange(RiskFactorReader.getRiskfactorToRangeDeceased().get("Lymphocyte count")[0],
						RiskFactorReader.getRiskfactorToRangeDeceased().get("Lymphocyte count")[1]);
				platelets = randomBetweenRange(RiskFactorReader.getRiskfactorToRangeDeceased().get("Platelets")[0],
						RiskFactorReader.getRiskfactorToRangeDeceased().get("Platelets")[1]);
				albumin = randomBetweenRange(RiskFactorReader.getRiskfactorToRangeDeceased().get("Albumin")[0],
						RiskFactorReader.getRiskfactorToRangeDeceased().get("Albumin")[1]);
				lactateDehydrogenase = randomBetweenRange(RiskFactorReader.getRiskfactorToRangeDeceased().get("Lactate Dehydrogenase")[0],
						RiskFactorReader.getRiskfactorToRangeDeceased().get("Lactate Dehydrogenase")[1]);
				troponinI = randomBetweenRange(RiskFactorReader.getRiskfactorToRangeDeceased().get("Troponin I")[0],
						RiskFactorReader.getRiskfactorToRangeDeceased().get("Troponin I")[1]);
				dDimer = randomBetweenRange(RiskFactorReader.getRiskfactorToRangeDeceased().get("D-dimer")[0],
						RiskFactorReader.getRiskfactorToRangeDeceased().get("D-dimer")[1]);
				ferritin = randomBetweenRange(RiskFactorReader.getRiskfactorToRangeDeceased().get("Ferritin")[0],
						RiskFactorReader.getRiskfactorToRangeDeceased().get("Ferritin")[1]);
				interleukin6 = randomBetweenRange(RiskFactorReader.getRiskfactorToRangeDeceased().get("Interleukin 6")[0],
						RiskFactorReader.getRiskfactorToRangeDeceased().get("Interleukin 6")[1]);
			}
			else
			{
				wbc = randomBetweenRange(RiskFactorReader.getRiskfactorToRangeAlive().get("White blood cell count")[0], 
						RiskFactorReader.getRiskfactorToRangeAlive().get("White blood cell count")[1]);
				lymphocyteCount = randomBetweenRange(RiskFactorReader.getRiskfactorToRangeAlive().get("Lymphocyte count")[0], 
						RiskFactorReader.getRiskfactorToRangeAlive().get("Lymphocyte count")[1]);
				platelets = randomBetweenRange(RiskFactorReader.getRiskfactorToRangeAlive().get("Platelets")[0], 
						RiskFactorReader.getRiskfactorToRangeAlive().get("Platelets")[1]);
				albumin = randomBetweenRange(RiskFactorReader.getRiskfactorToRangeAlive().get("Albumin")[0],
						RiskFactorReader.getRiskfactorToRangeAlive().get("Albumin")[1]);
				lactateDehydrogenase = randomBetweenRange(RiskFactorReader.getRiskfactorToRangeAlive().get("Lactate Dehydrogenase")[0], 
						RiskFactorReader.getRiskfactorToRangeAlive().get("Lactate Dehydrogenase")[1]);
				troponinI = randomBetweenRange(RiskFactorReader.getRiskfactorToRangeAlive().get("Troponin I")[0], 
						RiskFactorReader.getRiskfactorToRangeAlive().get("Troponin I")[1]);
				dDimer = randomBetweenRange(RiskFactorReader.getRiskfactorToRangeAlive().get("D-dimer")[0],
						RiskFactorReader.getRiskfactorToRangeAlive().get("D-dimer")[1]);
				ferritin = randomBetweenRange(RiskFactorReader.getRiskfactorToRangeAlive().get("Ferritin")[0], 
						RiskFactorReader.getRiskfactorToRangeAlive().get("Ferritin")[1]);
				interleukin6 = randomBetweenRange(RiskFactorReader.getRiskfactorToRangeAlive().get("Interleukin 6")[0], 
						RiskFactorReader.getRiskfactorToRangeAlive().get("Interleukin 6")[1]);
			}
			
			//Initialize patient object
			Patient patient = new Patient(gender, age, comorbidity, healthcareRelatedExposure, 
					outcome, currentSmoker, respiratoryRateGreaterThan24, temperatureGreaterThan37, wbc, 
					lymphocyteCount, platelets, albumin, lactateDehydrogenase, troponinI, dDimer, ferritin, interleukin6, groundGlassOpacity);
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
	 * @param min
	 * @param max
	 * @return random double
	 */
	public static double randomBetweenRange(Double min, Double max)
	{
		return ThreadLocalRandom.current().nextDouble(min, max);
	}
	
}
