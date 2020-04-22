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
 * UPDATED 4/18: Deleted symptom onset and confirmed diagnosis date features since this severely
 * limited the number of complete rows. Now, this reads in 1835 patients with complete data to an array list,
 * with a total of 4 features (age is included both as continuous and as a decade).
 * However, only ~400 of patients have a state of "released" or "deceased"; the majority are still "isolated."
 * Can we generate either released or deceased based on age and/or other features?
 * 
 * @author cbusc
 *
 */
public class PatientReader 
{
	private static ArrayList<Patient> patients;
	/**
	 * Reads in data from a CSV file and stores in arraylist of 
	 * Patient objects
	 */
	public static void readCSV()
	{
		patients = new ArrayList<Patient>();
		try
		{
			Scanner fileParser = new Scanner(new File("PatientInfo.csv"));
			fileParser.nextLine();
			while(fileParser.hasNextLine())
			{
				String row = fileParser.nextLine();
				String[] rowElements = row.split(",");

				//Running boolean check whether column data has any empty cell
				boolean bAllValidData = true;
				
				if (rowElements[2].equals(""))
				{
					bAllValidData = false;
				}
				String gender = rowElements[2];
				
				//store age as -1 default; update to age if given; else update to decade
				//integer between 1 and 9, if available
				int age = -1;
				if (!rowElements[3].equals(""))
				{
					try 
					{
						age = 2020 - Integer.parseInt(rowElements[3]);
					}
					catch (NumberFormatException e)   //there are many instances of input string: "" in this column
					{
						e.printStackTrace();
					}
				}
				else
				{
					//If decade is present, then add to decade a randomly generated number between 1 and 9 to yield age
					//Else, we are not able to discover an age, so flag this as invalid data
					if (!rowElements[4].equals(""))
					{
						String decade = rowElements[4].substring(0, rowElements[4].indexOf("s"));
						Random r = new Random();
						try
						{
							age = Integer.parseInt(decade) + r.nextInt(10);
						}
						catch (NumberFormatException e)
						{
							e.printStackTrace();
						}
					}
					else
					{
						bAllValidData = false;
					}
				}
				
				//Presence of comorbidity will always be complete since cell contains either TRUE or are blank
				boolean comorbid = !rowElements[8].equals("");   
				
				//Check for blank cell in healthcare related exposure column
				if (rowElements[9].equals(""))
				{
					bAllValidData = false;
				}
				boolean healthcareRelatedExposure = Pattern.matches(".*\\bpatient\\b.*", rowElements[9]);
				
				//Check the row index accessed is not out of bounds in later columns with early empty cells, 
				//as this has caused index out of bounds exceptions
				String ageAsDecade = "-1";
				if (4 < rowElements.length) 
				{
					ageAsDecade = rowElements[4];
				}
				if (ageAsDecade.equals("-1"))
				{
					bAllValidData = false;
				}
				
				String outcome = "-1";
				if (17 < rowElements.length)
				{
					outcome = rowElements[17];
				}
				if (outcome.equals("-1") || outcome.equals("isolated"))
				{
					bAllValidData = false;
				}
				
				//Below, generate new data features substantiated to be associated with COVID-19 mortality
				//Binary data
				//If "deceased" this patient will have a 0.8 probability of being rendered a current smoker when initialized
				//If "released" he will have a 0.2 probability of being rendered a current smoker when initialized
				boolean currentSmoker = generateBinaryRiskFactor(outcome, 0.58);
				boolean respiratoryRateGreaterThan24 = generateBinaryRiskFactor(outcome, 0.72);
				boolean temperatureGreaterThan37 = generateBinaryRiskFactor(outcome, 0.5);
				boolean groundGlassOpacity = generateBinaryRiskFactor(outcome, 0.67);
				
				//Continuous data
				//Pass in outcome, then based on whether the patient deceased or survived, 
				//assign a randomly generated risk factor value generated from the published range.
				//For example, if "deceased" a patient's white blood cell count will be randomly generated and assigned
				//between the range 6.9 - 13.9, versus a "survived" patient's from 4.3 - 7.7. 
				double wbc, lymphocyteCount, platelets, lactateDehydrogenase, troponinI, ferritin, interleukin6 = -1;
				if (outcome.equals("deceased"))
				{
					wbc = randomBetweenRange(RiskFactorReader.getRiskfactorToRangeDeceased().get("wbc")[0],
							RiskFactorReader.getRiskfactorToRangeDeceased().get("wbc")[1]);
					lymphocyteCount = randomBetweenRange(RiskFactorReader.getRiskfactorToRangeDeceased().get("lymphocyteCount")[0],
							RiskFactorReader.getRiskfactorToRangeDeceased().get("lymphocyteCount")[1]);
					platelets = randomBetweenRange(RiskFactorReader.getRiskfactorToRangeDeceased().get("platelets")[0],
							RiskFactorReader.getRiskfactorToRangeDeceased().get("platelets")[1]);
					lactateDehydrogenase = randomBetweenRange(RiskFactorReader.getRiskfactorToRangeDeceased().get("lactateDehydrogenase")[0],
							RiskFactorReader.getRiskfactorToRangeDeceased().get("lactateDehydrogenase")[1]);
					troponinI = randomBetweenRange(RiskFactorReader.getRiskfactorToRangeDeceased().get("troponinI")[0],
							RiskFactorReader.getRiskfactorToRangeDeceased().get("troponinI")[1]);
//					ferritin = randomBetweenRange(RiskFactorReader.getRiskfactorToRangeDeceased().get("ferritin")[0],
//							RiskFactorReader.getRiskfactorToRangeDeceased().get("ferritin")[1]);
					interleukin6 = randomBetweenRange(RiskFactorReader.getRiskfactorToRangeDeceased().get("interleukin6")[0],
							RiskFactorReader.getRiskfactorToRangeDeceased().get("interleukin6")[1]);
				}
				else
				{
					wbc = randomBetweenRange(RiskFactorReader.getRiskfactorToRangeAlive().get("wbc")[0], 
							RiskFactorReader.getRiskfactorToRangeAlive().get("wbc")[1]);
					lymphocyteCount = randomBetweenRange(RiskFactorReader.getRiskfactorToRangeAlive().get("lymphocyteCount")[0], 
							RiskFactorReader.getRiskfactorToRangeAlive().get("lymphocyteCount")[1]);
					platelets = randomBetweenRange(RiskFactorReader.getRiskfactorToRangeAlive().get("platelets")[0], 
							RiskFactorReader.getRiskfactorToRangeAlive().get("platelets")[1]);
					lactateDehydrogenase = randomBetweenRange(RiskFactorReader.getRiskfactorToRangeAlive().get("lactateDehydrogenase")[0], 
							RiskFactorReader.getRiskfactorToRangeAlive().get("lactateDehydrogenase")[1]);
					troponinI = randomBetweenRange(RiskFactorReader.getRiskfactorToRangeAlive().get("troponinI")[0], 
							RiskFactorReader.getRiskfactorToRangeAlive().get("troponinI")[1]);
//					ferritin = randomBetweenRange(RiskFactorReader.getRiskfactorToRangeAlive().get("ferritin")[0], 
//							RiskFactorReader.getRiskfactorToRangeAlive().get("ferritin")[1]);
					interleukin6 = randomBetweenRange(RiskFactorReader.getRiskfactorToRangeAlive().get("interleukin6")[0], 
							RiskFactorReader.getRiskfactorToRangeAlive().get("interleukin6")[1]);
				}
				
				
				/*
				 * 		this.platelets = platelets;
		this.lactateDehydrogenase = lactateDehydrogenase;
		this.troponinI = troponinI;
		this.ferritin = ferritin;
		this.interleukin6 = interleukin6;
		this.groundGlassOpacity = groundGlassOpacity;
				 */
				
				//Initialize patient object if all of the column data was present
				if (bAllValidData == true)
				{
					Patient patient = new Patient(gender, age, ageAsDecade, comorbid, healthcareRelatedExposure, 
							outcome, currentSmoker, respiratoryRateGreaterThan24, temperatureGreaterThan37, wbc, 
							lymphocyteCount, platelets, lactateDehydrogenase, troponinI, interleukin6, groundGlassOpacity);
					patients.add(patient);
				}
			}
			fileParser.close();
		}
		catch (FileNotFoundException e)
		{
			System.out.println("'PatientInfo.csv' file was not found.");
			e.printStackTrace();
		}
	}
	/**
	 * Gets array list of patients
	 * @return ArrayList patients
	 */
	public static ArrayList<Patient> getPatients() {
		return patients;
	}
//
//	/**
//	 * Randomly generates 0 or 1 with equal probability
//	 * @return 1 or 0
//	 */
//	public static int random50()
//	{
//		return (int) (2 * Math.random());
//	}
//	/**
//	 * Returns true with a 75% probability and false with 25% probability
//	 * predicated on bitwise operations, bits generated by random50
//	 * @return true or false
//	 */
//	public static boolean random75Boolean()
//	{
//		// 0.5 * 0.5 = 0.25
//		if (random50() == 0 && random50() == 0)
//		{
//			return false;
//		}
//		return true;
//	}
	
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
