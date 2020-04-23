import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.*;

/**
 * Reads in COVID19 mortality risk factors and their associated attributable risk
 * based on previously reported literature. 
 * F Zhou, et al. Clinical course and risk factors for mortality of 
 * adult inpatients with COVID-19 in Wuhan, China: a retrospective cohort 
 * study Lancet (2020 Mar 11), 10.1016/S0140-6736(20)30566-3
 * 
 * @author cbusc
 *
 */
public class RiskFactorReader {
	
	//Map risk factors reported as boolean to attributable risk
	private static Map<String, Double> riskfactorToAttributableRisk;
	//Map risk factors reported with continuous data to range of values
	private static Map<String, Double[]> riskfactorToRangeDeceased; 
	private static Map<String, Double[]> riskfactorToRangeAlive;
	private static double mortalityRate;
	
	/*
	 * Reads in data from CSV file and stores risk factor 
	 * key value pairs in hashmap.
	 */
	public static void readCSV()
	{
		riskfactorToAttributableRisk = new HashMap<String, Double>();
		riskfactorToRangeDeceased = new HashMap<String, Double[]>();
		riskfactorToRangeAlive = new HashMap<String, Double[]>();
		
		try
		{
			Scanner fileParser = new Scanner(new File("riskFactors.csv"));
			//fileParser.nextLine();
			int count = 0;
			double totalDeceased;
			double totalRecovered;
			while(fileParser.hasNextLine())
			{
				String row = fileParser.nextLine();
				String[] rowElements = row.split(",");
				
				//Total deceased and surivor values are in the first row
				if (count == 0)
				{
					totalDeceased = getNum(rowElements[3]);
					totalRecovered = getNum(rowElements[4]);
					mortalityRate = totalDeceased / (totalDeceased + totalRecovered);
					System.out.println(totalDeceased + "\n" + totalRecovered + "\n" + mortalityRate);
				}
				
				//Age is row 1
				if (count == 1)
				{
					riskfactorToRangeDeceased.put("Age", getRange(rowElements[4]));
					riskfactorToRangeAlive.put("Age", getRange(rowElements[5]));
					System.out.println("Age");
				}
				
				//White blood cell count is row 36
				if (count == 36)
				{
					riskfactorToRangeDeceased.put("White blood cell count", getRange(rowElements[4]));
					riskfactorToRangeAlive.put("White blood cell count", getRange(rowElements[5]));
				}
				//Lymphocyte count is row 40
				if (count == 40)
				{
					riskfactorToRangeDeceased.put("Lymphocyte count", getRange(rowElements[4]));
					riskfactorToRangeAlive.put("Lymphocyte count", getRange(rowElements[5]));
				}
				//Platelet count is row 44
				if (count == 44)
				{
					//platelet
					riskfactorToRangeDeceased.put("Platelets", getRange(rowElements[4]));
					riskfactorToRangeAlive.put("Platelets", getRange(rowElements[5]));
				}
				//LactateDehydrogenase is row 50
				if (count == 50)
				{
					riskfactorToRangeDeceased.put("Lactate Dehydrogenase", getRange(rowElements[4]));
					riskfactorToRangeAlive.put("Lactate Dehydrogenase", getRange(rowElements[5]));
				}
				//TroponinI is row 54
				if (count == 54)
				{
					riskfactorToRangeDeceased.put("Troponin I", getRange(rowElements[4]));
					riskfactorToRangeAlive.put("Troponin I", getRange(rowElements[5]));
				}
				//Ferritin is row 64
				if (count == 63)
				{
					riskfactorToRangeDeceased.put("Ferritin", getRange(rowElements[4]));
					riskfactorToRangeAlive.put("Ferritin", getRange(rowElements[5]));
				}
				//Interleukin6 is row 65
				if (count == 65)
				{
					riskfactorToRangeDeceased.put("Interleukin 6", getRange(rowElements[4]));
					riskfactorToRangeAlive.put("Interleukin 6", getRange(rowElements[5]));
				}
				count++;
			}
			fileParser.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
			System.out.println("'riskFactors.csv' not found. Please ensure that you have saved it in this project subfold of the eclipse-workspace");
		}
	}
	
	/**
	 * Parses range of doubles from string in a table cell
	 * @param string
	 * @return range of lab value
	 */
	public static Double[] getRange(String string)
	{
		Double[] range = new Double[2];
		String cleanString = string.replaceAll("·", ".");
		
		//Extract minimum number
		String min = cleanString.substring(cleanString.indexOf("(") + 1);
		min = min.substring(0, min.indexOf("–"));
		try { range[0] = new Double(Double.parseDouble(min)); }
		catch (NumberFormatException e) { }
		
		//Extract maximum number
		String max = cleanString.substring(cleanString.indexOf("–") + 1);
		max = max.substring(0, max.indexOf(")"));
		try { range[1] = new Double(Double.parseDouble(max)); }
		catch (NumberFormatException e) { }

		return range;
	}
	
	/**
	 * Parses double from excel cell String
	 * @param str
	 * @return double
	 */
	public static double getNum(String str)
	{
		String s = str.substring(str.indexOf("=") + 1);
		s = s.substring(0, s.indexOf(")"));
		double sDouble = 0.0;
		try { sDouble = Double.parseDouble(s); }
		catch (NumberFormatException e) { }
		
		return sDouble;
	}
	
	
//	/**
//	 * Calculates attributable risk for a given risk factor
//	 */
//	public static double getAttributableRisk()
//	{
//		return 0.0;
//	}

	public static Map<String, Double[]> getRiskfactorToRangeDeceased() {
		return riskfactorToRangeDeceased;
	}

	public static Map<String, Double[]> getRiskfactorToRangeAlive() {
		return riskfactorToRangeAlive;
	}
	
	public static double getMortalityRate()
	{
		return mortalityRate;
	}
	
	public static void main(String[] args)
	{
		RiskFactorReader.readCSV();
	}
}
