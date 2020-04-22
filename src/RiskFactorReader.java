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
			while(fileParser.hasNextLine())
			{
				String row = fileParser.nextLine();
				String[] rowElements = row.split(",");
				
				//White blood cell count is row 36
				if (count == 36)
				{
					riskfactorToRangeDeceased.put("wbc", getRange(rowElements[4]));
					riskfactorToRangeAlive.put("wbc", getRange(rowElements[5]));
				}
				//Lymphocyte count is row 40
				if (count == 40)
				{
					riskfactorToRangeDeceased.put("lymphocyteCount", getRange(rowElements[4]));
					riskfactorToRangeAlive.put("lymphocyteCount", getRange(rowElements[5]));
				}
				//Platelet count is row 44
				if (count == 44)
				{
					//platelet
					riskfactorToRangeDeceased.put("platelets", getRange(rowElements[4]));
					riskfactorToRangeAlive.put("platelets", getRange(rowElements[5]));
				}
				//LactateDehydrogenase is row 50
				if (count == 50)
				{
					riskfactorToRangeDeceased.put("lactateDehydrogenase", getRange(rowElements[4]));
					riskfactorToRangeAlive.put("lactateDehydrogenase", getRange(rowElements[5]));
				}
				//TroponinI is row 54
				if (count == 54)
				{
					riskfactorToRangeDeceased.put("troponinI", getRange(rowElements[4]));
					riskfactorToRangeAlive.put("troponinI", getRange(rowElements[5]));
				}
//				//Ferritin is row 64
//				if (count == 64)
//				{
//					riskfactorToRangeDeceased.put("ferritin", getRange(rowElements[4]));
//					riskfactorToRangeAlive.put("ferritin", getRange(rowElements[5]));
//				}
				//Interleukin6 is row 65
				if (count == 65)
				{
					riskfactorToRangeDeceased.put("interleukin6", getRange(rowElements[4]));
					riskfactorToRangeAlive.put("interleukin6", getRange(rowElements[5]));
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
	 * Extracts double from data table string
	 * @param string
	 * @return
	 */
	public static Double[] getRange(String string)
	{
		Double[] range = new Double[2];
		String cleanString = string.replaceAll("·", ".");
		
		//Clean min
		String min = cleanString.substring(cleanString.indexOf("(") + 1);
		min = min.substring(0, min.indexOf("–"));
		try { range[0] = new Double(Double.parseDouble(min)); }
		catch (NumberFormatException e) { }
		
		//Clean max
		String max = cleanString.substring(cleanString.indexOf("–") + 1);
		max = max.substring(0, max.indexOf(")"));
		try { range[1] = new Double(Double.parseDouble(max)); }
		catch (NumberFormatException e) { }

		return range;
	}
	
	/**
	 * Calculates attributable risk for a given risk factor
	 */
	public static double calculateAttributableRisk()
	{
		return 0.0;
	}

	public static Map<String, Double[]> getRiskfactorToRangeDeceased() {
		return riskfactorToRangeDeceased;
	}

	public static Map<String, Double[]> getRiskfactorToRangeAlive() {
		return riskfactorToRangeAlive;
	}
}
