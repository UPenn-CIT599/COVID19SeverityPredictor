import java.io.File;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.*;

/**
 * Reads in COVID19 mortality risk factors and their associated absolute, relative, and attributable
 * risks of mortality based on previously reported literature:
 * F Zhou, et al. Clinical course and risk factors for mortality of 
 * adult inpatients with COVID-19 in Wuhan, China: a retrospective cohort 
 * study Lancet (2020 Mar 11), 10.1016/S0140-6736(20)30566-3
 * 
 * @author cbusc
 *
 */
public class RiskFactorReader {
	
	//Map boolean risk factors to their calculated risks
	private static Map<String, Double> riskfactorToAttributableRisk;
	private static Map<String, Double> riskfactorToRelativeRisk;
	private static Map<String, Double> riskfactorToAbsoluteRiskTrue;
	private static Map<String, Double> riskfactorToAbsoluteRiskFalse;
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
		riskfactorToAbsoluteRiskTrue = new HashMap<String, Double>();
		riskfactorToAbsoluteRiskFalse = new HashMap<String, Double>();
		riskfactorToRelativeRisk = new HashMap<String, Double>();
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
				//Note: these values were subsequently hard-coded in due to formatting changes that occured to the table .csv
				//from the cited study when uploading to github; these values can be found in Table 1 of the study. 
				//The methods remain in the source code. Previously, this data was extracted as, eg.: double a = getFirstNum(rowElements[3]);
				if (row == 0)
				{
					totalDeceased = 54.0;
					totalRecovered = 137.0;
					mortalityRate = totalDeceased / (totalDeceased + totalRecovered);
				}

				//BOOLEAN RISK FACTORS
				//Current smoker is row 6. Boolean risk factor. 
				//a = num deceased, with feature; b = num recovered, with feature
				//c = num deceased, without feature; d = num recovered, without feature
				if (row == 6)
				{
					double a = 5.0;
					double b = 6.0;
					double c = totalDeceased - a;
					double d = totalRecovered - b;
					riskfactorToAttributableRisk.put("Current smoker", getAttributableRisk(a, b, c, d));
					riskfactorToRelativeRisk.put("Current smoker", getRelativeRisk(a, b, c, d));
					riskfactorToAbsoluteRiskTrue.put("Current smoker", getAbsoluteRiskTrue(a, b));
					riskfactorToAbsoluteRiskFalse.put("Current smoker", getAbsoluteRiskFalse(c, d));
				}
				//Comorbidity is row 7. Boolean risk factor. 
				if (row == 7)
				{
					double a = 36.0;
					double b = 55.0;
					double c = totalDeceased - a;
					double d = totalRecovered - b;
					riskfactorToAttributableRisk.put("Comorbidity", getAttributableRisk(a, b, c, d));
					riskfactorToRelativeRisk.put("Comorbidity", getRelativeRisk(a, b, c, d));
					riskfactorToAbsoluteRiskTrue.put("Comorbidity", getAbsoluteRiskTrue(a, b));
					riskfactorToAbsoluteRiskFalse.put("Comorbidity", getAbsoluteRiskFalse(c, d));
				}
				//Respiratory rate is row 15. Boolean risk factor.
				if (row == 15)
				{
					double a = 34.0;
					double b = 22.0;
					double c = totalDeceased - a;
					double d = totalRecovered - b;
					riskfactorToAttributableRisk.put("Respiratory rate > 24", getAttributableRisk(a, b, c, d));
					riskfactorToRelativeRisk.put("Respiratory rate > 24", getRelativeRisk(a, b, c, d));
					riskfactorToAbsoluteRiskTrue.put("Respiratory rate > 24", getAbsoluteRiskTrue(a, b));
					riskfactorToAbsoluteRiskFalse.put("Respiratory rate > 24", getAbsoluteRiskFalse(c, d));
				}
				//Temperature is row 18. Boolean risk factor.
				if (row == 18)
				{
					double a = 51.0;
					double b = 129.0;
					double c = totalDeceased - a;
					double d = totalRecovered - b;
					riskfactorToAttributableRisk.put("Temperature > 37.3", getAttributableRisk(a, b, c, d));
					riskfactorToRelativeRisk.put("Temperature > 37.3", getRelativeRisk(a, b, c, d));
					riskfactorToAbsoluteRiskTrue.put("Temperature > 37.3", getAbsoluteRiskTrue(a, b));
					riskfactorToAbsoluteRiskFalse.put("Temperature > 37.3", getAbsoluteRiskFalse(c, d));
				}
				//Consolidation is row 72. Boolean risk factor. 
				if (row == 72)
				{
					double a = 40.0;
					double b = 72.0;
					double c = totalDeceased - a;
					double d = totalRecovered - b;
					riskfactorToAttributableRisk.put("Consolidation on x-ray", getAttributableRisk(a, b, c, d));
					riskfactorToRelativeRisk.put("Consolidation on x-ray", getRelativeRisk(a, b, c, d));
					riskfactorToAbsoluteRiskTrue.put("Consolidation on x-ray", getAbsoluteRiskTrue(a, b));
					riskfactorToAbsoluteRiskFalse.put("Consolidation on x-ray", getAbsoluteRiskFalse(c, d));
				}
				
				//CONTINUOUS RISK FACTORS
				//Before hard-coding, eg: riskfactorToRangeDeceased.put("Age", getRange(rowElements[5]));
				//Age is row 1
				if (row == 1)
				{
					riskfactorToRangeDeceased.put("Age", new Double[]{63.0, 76.0});
					riskfactorToRangeRecovered.put("Age", new Double[]{45.0, 58.0});
				}	
				//White blood cell count is row 36
				if (row == 36)
				{
					riskfactorToRangeDeceased.put("White blood cell count", new Double[]{6.9, 13.9});
					riskfactorToRangeRecovered.put("White blood cell count", new Double[]{4.3, 7.7});
				}
				//Lymphocyte count is row 40
				if (row == 40)
				{
					riskfactorToRangeDeceased.put("Lymphocyte count", new Double[]{0.5, 0.8});
					riskfactorToRangeRecovered.put("Lymphocyte count", new Double[]{0.8, 1.5});
				}
				//Platelet count is row 44
				if (row == 44)
				{
					riskfactorToRangeDeceased.put("Platelets", new Double[]{107.0, 229.0});
					riskfactorToRangeRecovered.put("Platelets", new Double[]{168.0, 271.0});
				}
				//Albumin is row 46
				if (row == 46)
				{
					riskfactorToRangeDeceased.put("Albumin", new Double[]{26.5, 31.3});
					riskfactorToRangeRecovered.put("Albumin", new Double[]{30.6, 36.4});
				}		
				//LactateDehydrogenase is row 50
				if (row == 50)
				{
					riskfactorToRangeDeceased.put("Lactate Dehydrogenase", new Double[]{363.0, 669.0});
					riskfactorToRangeRecovered.put("Lactate Dehydrogenase", new Double[]{219.0, 318.0});
				}
				//TroponinI is row 54
				if (row == 54)
				{
					riskfactorToRangeDeceased.put("Troponin I", new Double[]{5.6, 83.1});
					riskfactorToRangeRecovered.put("Troponin I", new Double[]{1.1, 5.5});
				}
				//D-dimer is row 59
				if (row == 59)
				{
					riskfactorToRangeDeceased.put("D-dimer", new Double[]{1.5, 21.1});
					riskfactorToRangeRecovered.put("D-dimer", new Double[]{0.3, 1.0});
				}
				//Ferritin is row 64
				if (row == 63)
				{
					riskfactorToRangeDeceased.put("Ferritin", new Double[]{728.9, 2000.0});
					riskfactorToRangeRecovered.put("Ferritin", new Double[]{264.0, 921.5});
				}
				//Interleukin6 is row 65
				if (row == 65)
				{
					riskfactorToRangeDeceased.put("Interleukin 6", new Double[]{7.5, 14.4});
					riskfactorToRangeRecovered.put("Interleukin 6", new Double[]{5.0, 7.9});
				}
				//Procalcitonin is row 66
				if (row == 66)
				{
					riskfactorToRangeDeceased.put("Procalcitonin", new Double[]{0.1, 0.5});
					riskfactorToRangeRecovered.put("Procalcitonin", new Double[]{0.1, 0.1});
				}
				row++;
			}
			fileParser.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
			System.out.println("'riskFactors.csv' not found. Please ensure that you have saved it in this project subfold of the eclipse-workspace");
		} catch (Exception e) {
			System.out.println("invalid input format..");
			e.printStackTrace();
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
		//String cleanString = string.replaceAll("·", ".");
		
		//Extract minimum number
		String min = string.substring(string.indexOf("(") + 1);
		min = min.substring(0, min.indexOf("-"));
		try { range[0] = new Double(Double.parseDouble(min)); }
		catch (NumberFormatException e) { }
		
		//Extract maximum number
		String max = string.substring(string.indexOf("-") + 1);
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
	 * Calculates absolute risk of mortality if feature is present
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 * @return absolute risk of mortality if feature is present
	 */
	public static double getAbsoluteRiskTrue(double a, double b)
	{
		return ((a / (a + b)));
	}
	
	/**
	 * Get absolute risk of mortality if no feature is present
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 * @return absolute risk of mortality if no feature is present
	 */
	public static double getAbsoluteRiskFalse(double c, double d)
	{
		return ((c / (c + d)));
	}
	
	/**
	 * Calculates attributable risk of a given risk factor. We will use this to extrapolate 
	 * binary risk factors, given that there is not a range of values that we can read in. 
	 * a = num deceased, with feature; b = num recovered, with feature
	 * c = num deceased, without feature; d = num recovered, without feature
	 * Attributable risk = (A / A + B) - (C / C + D)
	 * In order words: (outcome risk among those with feature - outcome risk among those without feature)
	 * @return attributable risk
	 */
	public static double getAttributableRisk(double a, double b, double c, double d)
	{
		return (getAbsoluteRiskTrue(a, b) - getAbsoluteRiskFalse(c, d));
	}
	
	/**
	 * Calculates relative risk. 
	 * Relative risk = (A / A + B) / (C / C + D)
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 * @return relative risk
	 */
	public static double getRelativeRisk(double a, double b, double c, double d)
	{
		return (getAbsoluteRiskTrue(a, b) / getAbsoluteRiskFalse(c, d));
	}

	/**
	 * Gets mortality rate.
	 * Mortality rate = deceased / (deceased + recovered)
	 * @return mortality rate
	 */
	public static double getMortalityRate()
	{
		return mortalityRate;
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
	
	public static Map<String, Double> getRiskfactorToRelativeRisk() {
		return riskfactorToRelativeRisk;
	}

	public static Map<String, Double> getRiskfactorToAbsoluteRiskTrue() {
		return riskfactorToAbsoluteRiskTrue;
	}
	public static Map<String, Double> getRiskfactorToAbsoluteRiskFalse() {
		return riskfactorToAbsoluteRiskFalse;
	}
}
