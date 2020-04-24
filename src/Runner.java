import weka.classifiers.Classifier;

/**
 * Driver class for the COVID19 risk predictor
 * @author cbusc
 *
 */
public class Runner {
	/**
	 * Main method
	 * @param args
	 */
    public static void main(String args[]) throws Exception {
	Object[] userInput = {"male", 56.0, "50s", "FALSE", "FALSE"};
	ClickResponse.response(userInput);
	    //Double[] scores = WekaPipeline.pipeline();
	    //Classifier[] models = WekaClassifier.getModels();
	    //Classifier bestModel = WekaPipeline.selectBestModel(scores, models);
	}
}
