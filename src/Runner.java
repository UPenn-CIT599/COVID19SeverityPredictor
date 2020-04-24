import org.jfree.ui.RefineryUtilities;


/**
 * Driver class for the COVID19 prognosticator
 * @author cbusc
 *
 */
public class Runner {
    /**
     * Main method
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception
    {
	RiskFactorReader.readCSV();
	PatientGenerator.generatePatients();
	WriteToCSV.write();

	StackedBarChart chart = new StackedBarChart("Relationship Between Patient Characteristics at Admission and COVID-19 Mortality");
	//BarChart inherits .pack() and .setVisible() from the Window class
	chart.pack();
	RefineryUtilities.centerFrameOnScreen(chart);
	chart.setVisible(true);

	Object[] userInput = {"male", 56.0, "50s", "FALSE", "FALSE"};
	ClickResponse.response(userInput);
	//Double[] scores = WekaPipeline.pipeline();
	//Classifier[] models = WekaClassifier.getModels();
	//Classifier bestModel = WekaPipeline.selectBestModel(scores, models);
    }
}