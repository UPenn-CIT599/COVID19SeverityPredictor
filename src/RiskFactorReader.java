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
	
	//Map boolean risk factors to their calculated attributable risk
	private static Map<String, Double> riskfactorToAttributableRisk;
	//Map continuous risk factors to their reported range of values
	private static Map<String, Double[]> riskfactorToRangeDeceased; 
	private static Map<String, Double[]> riskfactorToRangeRecovered;
	private static double mortalityRate;
	
	/*
	 * Reads in data from CSV file and stores risk factor 
	 * key value pairs in hashmap.
	 */
	public static void readCSV()
	{
		riskfactorToAttributableRisk = new HashMap<String, Double>();
		riskfactorToRangeDeceased = new HashMap<String, Double[]>();
		riskfactorToRangeRecovered = new HashMap<String, Double[]>();
		
		try
		{
			Scanner fileParser = new Scanner(new File("riskFactors.csv"));
			int row = 0;
			double totalDeceased = 0;
			double totalRecovered = 0;
			while(fileParser.hasNextLine())
			{
				String rowString = fileParser.nextLine();
				String[] rowElements = rowString.split(",");
				
				//Parse total deceased and total recovered patients and store in local variables
				if (row == 0)
				{
					totalDeceased = getFirstNum(rowElements[3]);
					totalRecovered = getFirstNum(rowElements[4]);
					mortalityRate = totalDeceased / (totalDeceased + totalRecovered);
				}

				//BOOLEAN RISK FACTORS
				//Current smoker is row 6. Boolean risk factor. 
				//a = num deceased, with feature; b = num recovered, with feature
				//c = num deceased, without feature; d = num recovered, without feature
				if (row == 6)
				{
					double a = getFirstNum(rowElements[3]);
					double b = getFirstNum(rowElements[4]);
					double c = totalDeceased - a;
					double d = totalRecovered - b;
					riskfactorToAttributableRisk.put("Current smoker", getAttributableRisk(a, b, c, d));
				}
				//Comorbidity is row 7. Boolean risk factor. 
				if (row == 7)
				{
					double a = getFirstNum(rowElements[3]);
					double b = getFirstNum(rowElements[4]);
					double c = totalDeceased - a;
					double d = totalRecovered - b;
					riskfactorToAttributableRisk.put("Comorbidity", getAttributableRisk(a, b, c, d));
				}
				//Respiratory rate is row 15. Boolean risk factor.
				if (row == 15)
				{
					double a = getFirstNum(rowElements[3]);
					double b = getFirstNum(rowElements[4]);
					double c = totalDeceased - a;
					double d = totalRecovered - b;
					riskfactorToAttributableRisk.put("Respiratory rate > 24", getAttributableRisk(a, b, c, d));
				}
				//Temperature is row 18. Boolean risk factor.
				if (row == 18)
				{
					double a = getFirstNum(rowElements[3]);
					double b = getFirstNum(rowElements[4]);
					double c = totalDeceased - a;
					double d = totalRecovered - b;
					riskfactorToAttributableRisk.put("Temperature > 37.3", getAttributableRisk(a, b, c, d));
				}
				//Consolidation is row 72. Boolean risk factor. 
				if (row == 72)
				{
					double a = getFirstNum(rowElements[3]);
					double b = getFirstNum(rowElements[4]);
					double c = totalDeceased - a;
					double d = totalRecovered - b;
					riskfactorToAttributableRisk.put("Consolidation on x-ray", getAttributableRisk(a, b, c, d));
				}
				
				//CONTINUOUS RISK FACTORS
				//Age is row 1
				if (row == 1)
				{
					riskfactorToRangeDeceased.put("Age", getRange(rowElements[4]));
					riskfactorToRangeRecovered.put("Age", getRange(rowElements[5]));
				}	
				//White blood cell count is row 36
				if (row == 36)
				{
					riskfactorToRangeDeceased.put("White blood cell count", getRange(rowElements[4]));
					riskfactorToRangeRecovered.put("White blood cell count", getRange(rowElements[5]));
				}
				//Lymphocyte count is row 40
				if (row == 40)
				{
					riskfactorToRangeDeceased.put("Lymphocyte count", getRange(rowElements[4]));
					riskfactorToRangeRecovered.put("Lymphocyte count", getRange(rowElements[5]));
				}
				//Platelet count is row 44
				if (row == 44)
				{
					riskfactorToRangeDeceased.put("Platelets", getRange(rowElements[4]));
					riskfactorToRangeRecovered.put("Platelets", getRange(rowElements[5]));
				}
				//Albumin is row 46
				if (row == 46)
				{
					riskfactorToRangeDeceased.put("Albumin", getRange(rowElements[4]));
					riskfactorToRangeRecovered.put("Albumin", getRange(rowElements[5]));
				}		
				//LactateDehydrogenase is row 50
				if (row == 50)
				{
					riskfactorToRangeDeceased.put("Lactate Dehydrogenase", getRange(rowElements[4]));
					riskfactorToRangeRecovered.put("Lactate Dehydrogenase", getRange(rowElements[5]));
				}
				//TroponinI is row 54
				if (row == 54)
				{
					riskfactorToRangeDeceased.put("Troponin I", getRange(rowElements[4]));
					riskfactorToRangeRecovered.put("Troponin I", getRange(rowElements[5]));
				}
				//D-dimer is row 59
				if (row == 59)
				{
					riskfactorToRangeDeceased.put("D-dimer", getRange(rowElements[4]));
					riskfactorToRangeRecovered.put("D-dimer", getRange(rowElements[5]));
				}
				//Ferritin is row 64
				if (row == 63)
				{
					riskfactorToRangeDeceased.put("Ferritin", getRange(rowElements[4]));
					riskfactorToRangeRecovered.put("Ferritin", getRange(rowElements[5]));
				}
				//Interleukin6 is row 65
				if (row == 65)
				{
					riskfactorToRangeDeceased.put("Interleukin 6", getRange(rowElements[4]));
					riskfactorToRangeRecovered.put("Interleukin 6", getRange(rowElements[5]));
				}
				//Procalcitonin is row 66
				if (row == 66)
				{
					riskfactorToRangeDeceased.put("Procalcitonin", getRange(rowElements[4]));
					riskfactorToRangeRecovered.put("Procalcitonin", getRange(rowElements[5]));
				}
				row++;
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
	 * Returns range of lab values (doubles) from string input
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
	 * Parses and returns a double from parameterized string using regex
	 * @param str
	 * @return number
	 */
	public static double getFirstNum(String str)
	{
		//Set regex
		Pattern p = Pattern.compile("[\\d]+");
		//Instantiate matcher
		Matcher m = p.matcher(str);
		//Find match
		m.find();
		//Converts number as string to double
		double num = 0;
		try { num = Double.parseDouble(m.group(0)); }
		catch (NumberFormatException e) { }
		
		return num;
	}
	/**
	 * Calculates attributable risk of a given risk factor. We will use this to extrapolate 
	 * binary risk factors, given that there is not a range of values that we can read in. 
	 * a = num deceased, with feature; b = num recovered, with feature
	 * c = num deceased, without feature; d = num recovered, without feature
	 * Attributable risk = (A / A + B) - (C / C + D)
	 * In order words: (outcome risk among those with feature - outcome risk among those without feature)
	 */
	public static double getAttributableRisk(double a, double b, double c, double d)
	{
		return ((a / (a + b)) - (c / (c + d)));
	}

	public static Map<String, Double[]> getRiskfactorToRangeDeceased() {
		return riskfactorToRangeDeceased;
	}

	public static Map<String, Double[]> getRiskfactorToRangeRecovered() {
		return riskfactorToRangeRecovered;
	}
	
	public static Map<String, Double> getRiskfactorToAttributableRisk()
	{
		return riskfactorToAttributableRisk;
	}
	/**
	 * Calculates mortality rate.
	 * Mortality rate = deceased / (deceased + recovered)
	 * @return mortality rate
	 */
	public static double getMortalityRate()
	{
		return mortalityRate;
	}
//	public static void main(String[] args)
//	{
//		RiskFactorReader.readCSV();
//		System.out.println(Arrays.toString(riskfactorToRangeDeceased.get("Age")));
//		System.out.println(Arrays.toString(riskfactorToRangeRecovered.get("Age")));
//	}
}
