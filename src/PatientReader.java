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
				double age = -1;
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
				
//				//Presence of comorbidity will always be complete since cell contains either TRUE or are blank
//				boolean comorbid = !rowElements[8].equals("");   
//				
//				//Check for blank cell in healthcare related exposure column
//				if (rowElements[9].equals(""))
//				{
//					bAllValidData = false;
//				}
//				boolean healthcareRelatedExposure = Pattern.matches(".*\\bpatient\\b.*", rowElements[9]);
				
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
				
				//Generate outcome "deceased" based on probability: age / 100
				//Otherwise outcome is set to "recovered." This was done due to scarcity 
				//of outcomes data (most were still isolated) to train the classifier. 
				String outcome = "-1";
				if (probableBoolean(age / 100) && age > 20)
				{
					outcome = "deceased";
				}
				else { outcome = "recovered"; }
				
				if (outcome.equals("-1"))
				{
					bAllValidData = false;
				}
				
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
				double wbc, lymphocyteCount, platelets, lactateDehydrogenase, troponinI, ferritin, interleukin6 = -1;
				if (outcome.equals("deceased"))
				{
					wbc = randomBetweenRange(RiskFactorReader.getRiskfactorToRangeDeceased().get("White blood cell count")[0],
							RiskFactorReader.getRiskfactorToRangeDeceased().get("White blood cell count")[1]);
					lymphocyteCount = randomBetweenRange(RiskFactorReader.getRiskfactorToRangeDeceased().get("Lymphocyte count")[0],
							RiskFactorReader.getRiskfactorToRangeDeceased().get("Lymphocyte count")[1]);
					platelets = randomBetweenRange(RiskFactorReader.getRiskfactorToRangeDeceased().get("Platelets")[0],
							RiskFactorReader.getRiskfactorToRangeDeceased().get("Platelets")[1]);
					lactateDehydrogenase = randomBetweenRange(RiskFactorReader.getRiskfactorToRangeDeceased().get("Lactate Dehydrogenase")[0],
							RiskFactorReader.getRiskfactorToRangeDeceased().get("Lactate Dehydrogenase")[1]);
					troponinI = randomBetweenRange(RiskFactorReader.getRiskfactorToRangeDeceased().get("Troponin I")[0],
							RiskFactorReader.getRiskfactorToRangeDeceased().get("Troponin I")[1]);
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
					lactateDehydrogenase = randomBetweenRange(RiskFactorReader.getRiskfactorToRangeAlive().get("Lactate Dehydrogenase")[0], 
							RiskFactorReader.getRiskfactorToRangeAlive().get("Lactate Dehydrogenase")[1]);
					troponinI = randomBetweenRange(RiskFactorReader.getRiskfactorToRangeAlive().get("Troponin I")[0], 
							RiskFactorReader.getRiskfactorToRangeAlive().get("Troponin I")[1]);
					ferritin = randomBetweenRange(RiskFactorReader.getRiskfactorToRangeAlive().get("Ferritin")[0], 
							RiskFactorReader.getRiskfactorToRangeAlive().get("Ferritin")[1]);
					interleukin6 = randomBetweenRange(RiskFactorReader.getRiskfactorToRangeAlive().get("Interleukin 6")[0], 
							RiskFactorReader.getRiskfactorToRangeAlive().get("Interleukin 6")[1]);
				}
				
				//Initialize patient object if all of the column data was present
				if (bAllValidData == true)
				{
					Patient patient = new Patient(gender, age, ageAsDecade, comorbidity, healthcareRelatedExposure, 
							outcome, currentSmoker, respiratoryRateGreaterThan24, temperatureGreaterThan37, wbc, 
							lymphocyteCount, platelets, lactateDehydrogenase, troponinI, ferritin, interleukin6, groundGlassOpacity);
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
