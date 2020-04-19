import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
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
public class PatientReaderKorea 
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
			int completeRows = 0;                									//TEST CODE
			int incompleteRows = 0;                 								 //TEST CODE
			Scanner fileParser = new Scanner(new File("PatientInfo.csv"));
			fileParser.nextLine();
			while(fileParser.hasNextLine())
			{
				String row = fileParser.nextLine();
				String[] rowElements = row.split(",");

				//Running boolean check whether column data has any empty cell
				boolean bAllValidData = true;
				
				if (rowElements.equals(""))
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
				
				String state = "-1";
				if (17 < rowElements.length)
				{
					state = rowElements[17];
				}
				if (state.equals("-1"))
				{
					bAllValidData = false;
				}
				
				//Initialize patient object if all of the column data was present
				if (bAllValidData == true)
				{
					Patient patient = new Patient(gender, age, ageAsDecade, comorbid, healthcareRelatedExposure, state);
					patients.add(patient);
					completeRows++;                          //TEST CODE
				}
				else
				{
					incompleteRows++;                       //TEST CODE
				}
			}
			fileParser.close();
			System.out.println("Complete rows: " + completeRows + "\nIncomplete rows: " + incompleteRows);             //TEST CODE
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
}
