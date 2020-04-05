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
 * @author cbusc
 *
 */
public class PatientReaderKorea 
{
	private static ArrayList<Patient> patients;
	
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
				//System.out.println(Arrays.toString(rowElements));

				//store useful elements of each line in variables
				String gender = rowElements[2];
				
				//store age as -1 default; update to age if given; else update to decade
				//integer between 1 and 9 if given
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
				}	
				boolean comorbid = !rowElements[8].equals("");
				boolean healthcareRelatedExposure = Pattern.matches(".*\\bpatient\\b.*", rowElements[9]);
				
				//It seems that many of the excel rows read in only 10-15 columns, therefore
				//array index out of bounds exceptions occur when accessing the later elements; 
				//exception handling code below stores these missing column values simply as ""
				String ageAsDecade = "";
				try
				{
					ageAsDecade = rowElements[4];
				}
				catch (ArrayIndexOutOfBoundsException e)
				{
				}
				String symptomOnsetDate = "";
				try
				{
					symptomOnsetDate = rowElements[13];
				}
				catch (ArrayIndexOutOfBoundsException e)
				{
				}
				String confirmedDate = "";
				try
				{
					confirmedDate = rowElements[14];
				}
				catch (ArrayIndexOutOfBoundsException e)
				{
				}
				String releasedDate = "";
				try
				{
					releasedDate = rowElements[15];
				}
				catch (ArrayIndexOutOfBoundsException e)
				{
				}
				String deceasedDate = "";
				try
				{
					deceasedDate = rowElements[16];
				}
				catch (ArrayIndexOutOfBoundsException e)
				{
				}
				String state = "";
				try
				{
					state = rowElements[17];
				}
				catch (ArrayIndexOutOfBoundsException e)
				{
				}
				Patient patient = new Patient(gender, age, ageAsDecade, comorbid, healthcareRelatedExposure, symptomOnsetDate, confirmedDate, releasedDate, deceasedDate, state);
				patients.add(patient);
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
}
