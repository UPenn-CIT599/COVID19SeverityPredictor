
import weka.core.Instances;

/**
 * Class to connect user response on click to classifier Returns risk score
 * 
 * @author 1Air
 *
 */
public class ClickResponse {


    /**
     * when user clicks the "calculate" button, 
     * submitted data is pre-processed, converted 
     * an array of doubles and passed to the classifier
     * to return a response.
     * @param userInput
     * @throws Exception
     * @return
     */
    public static double response(double[] userInput) throws Exception {
	System.out.println("Got User Response");
	System.out.println("Begin risk score computation...");
	System.out.println("Converting User Response to Instance...");
	
	Instances testing = WekaPipeline.userInputToInstance(userInput);
	
	System.out.println("Done converting");
	System.out.println("Starting Prediction Pipeline...");
	
	double risk_score = WekaPipeline.predictOnUserInput(testing);
	
	System.out.println("Risk Score: " + Double.toString(risk_score));

	return risk_score;
    }

}
