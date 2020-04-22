//import java.util.Map;
//
//import org.jfree.data.category.CategoryDataset;
//import org.jfree.data.category.DefaultCategoryDataset;
//import org.jfree.ui.ApplicationFrame;
//
///**
//	 * Responsible for creation of bar charts to be implemented in 
//	 * JSwing. ApplicationFrame is a subclass of javax.swing.JFrame.
//	 * @author cbusc
//	 *
//	 */
//public class BarChart extends ApplicationFrame 
//{
//	/**
//	 * Creates a new BarChart instance
//	 * 
//	 * @param title (the frame title)
//	 */
//	public BarChart(String title)
//	{
//		//Superclass constructor
//		super(title);
//	}
//	
//	/**
//	 * Returns a category dataset. 
//	 * 
//	 * CategoryDataset is an interface for a dataset with one or more series (eg. deceased or 
//	 * recovered), and values (positive/negative findings) associated with categories (temp>24 degrees, 
//	 * ground glass opacity seen on xray, etc.).
//	 * 
//	 * @return a dataset implementing the CategoryDataset interface
//	 */
//	private static CategoryDataset createDataset()
//	{
//		//Row keys
//		String deceased = "Deceased";
//		String recovered = "Recovered";
//		
//		//Column keys
//		String comorbidity = "Comorbidity";
//		String healthcareRelated = "Healthcare-related exposure";
//		String currentSmoker = "Current smoker";
//		String rrGreaterThan24 = "RR > 24";   //respiratory rate; respirations per minute
//		String tempGreaterThan37 = "T > 37.3 °C";   //degrees celsius
//		String ggoOnCXR = "GGO on chest x-ray";   //ground glass opacity
//		
//		//Create the dataset by iterating through DataAnalysis hashmaps to enumerate patients with positive
//		//findings for each row key in each category. 
//		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//		
//		
//	}
//	
//	//Iterate through HashMap of age as decade to num patients with outcome
//	//value = numPatients with outcome in age bracket
//	//rowKey = outcome
//	//columnKey = ageAsDecade
//	
////	PatientReader.readCSV();
////	DataAnalysis.initializePatients();
////	
////
////	//Iterate through HashMap: ageAsDecadeToNumReleased	
////	Map<String, Double> ageAsDecadeToProbReleased = DataAnalysis.getAgeAsDecadeToProbReleased();
////	for (String age : ageAsDecadeToProbReleased.keySet())
////	{
////		dataset.addValue(ageAsDecadeToProbReleased.get(age), "Released", age);
////	}
//
//	public static void main(String[] args)
//	{
//		
//	}
//}
