import weka.classifiers.Classifier;
import weka.core.Instances;

/**
 * Class to connect user response on click to classifier
 * Returns risk score
 * @author 1Air
 *
 */
public class ClickResponse {
	public ClickResponse() {
		
	}
	
	
	public double response(int[] userInfo){
		double risk_score = 1.0;
	    for (int i : userInfo) {
			risk_score += i;
		}
	    return risk_score;
	}
	
	
	
	/**
	 * when user clicked the "calculation" button, this method will call UserInformationCollector
	 * first, pass the array into classifier. Then call GraphGenerator to display the final result.
	 * @param userInput 
	 * @throws Exception 
	 * @return
	 */
//	public double response(Instances userInput) throws Exception {
//	    double risk_score = 0.0;
//	    Double[] scores = WekaPipeline.pipeline();
//	    Classifier[] models = WekaClassifier.getModels();
//	    Classifier bestModel = WekaPipeline.selectBestModel(scores, models);
//	    risk_score = WekaPipeline.predictOnUserInput(bestModel, userInput, null);
//	    return risk_score;
//	}
}
