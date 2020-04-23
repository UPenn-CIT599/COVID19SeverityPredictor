import java.util.ArrayList;
import weka.classifiers.Classifier;
import weka.core.Instances;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.FastVector;
import weka.core.Instances;
import weka.core.Utils;
/**
 * Class to connect user response on click to classifier
 * Returns risk score
 * @author 1Air
 *
 */
public class ClickResponse {
    
	public ClickResponse() {
		
	}
	
	
	/**
	 * when user clicked the "calculation" button, this method will call UserInformationCollector
	 * first, pass the array into classifier. Then call GraphGenerator to display the final result.
	 * @param userInput 
	 * @throws Exception 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public double response(double[] userInput) throws Exception {
	    FastVector attributes;
	    FastVector attAge;
	    FastVector attClass;
	    FastVector attInfection;
	    FastVector attComorbid;
	    Instances testing;
	    double[] values;

	    // 1. set up attributes
	    attributes = new FastVector();
	    
	    //age
	    attAge = new FastVector();
	    attAge.addElement("10s");
	    attAge.addElement("20s");
	    attAge.addElement("30s");
	    attAge.addElement("40s");
	    attAge.addElement("50s");
	    attAge.addElement("60s");
	    attAge.addElement("70s");
	    attAge.addElement("80s");
	    attAge.addElement("90s");
	    attributes.addElement(new Attribute("age", attAge));
	    
	    //state
	    attClass = new FastVector();
	    attClass.addElement("released");
	    attClass.addElement("isolated");
	    attClass.addElement("deceased");
	    attributes.addElement(new Attribute("state", attClass));
	    
	    //Infection
	    attInfection = new FastVector();
	    attributes.addElement(new Attribute("infection_case", attInfection));
	    
	    //age
	    attComorbid = new FastVector();
	    
	    attributes.addElement(new Attribute("comorbid", attComorbid));
	    
	    double risk_score = 0.0;
	    
	    // 2. create Instances object
	    testing = new Instances("Test dataset", attributes, 0);
	    
	    // 3. fill with data
	    values = new double[testing.numAttributes()];
	    
	    for (int i=0; i < userInput.length; i++) {
		if (i==2) {
		    values[i] = attAge.indexOf(userInput[i]);
		} else if (i==3) {
		    values[i] = attClass.indexOf(userInput[i]);
		} else {
		    values[i] = userInput[i];
		}
		
	    }
	    
	    // add data to instance
	    testing.add(new DenseInstance(1.0, values));
	    
	    Double[] scores = WekaPipeline.pipeline();
	    Classifier[] models = WekaClassifier.getModels();
	    Classifier bestModel = WekaPipeline.selectBestModel(scores, models);
	    
	    // perform prediction
	    double myValue = bestModel.classifyInstance(testing.lastInstance());
	    // get the name of class value
	    String prediction = testing.classAttribute().value((int) myValue);
	    
	    //risk_score = WekaPipeline.predictOnUserInput(bestModel, testing, null);
	    
	    System.out.println("The predicted value of the instance [" 
		         + "]: " + prediction);
	    
	    return risk_score;
	}
	
	
}
