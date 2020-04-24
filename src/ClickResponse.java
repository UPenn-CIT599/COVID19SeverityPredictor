
import weka.classifiers.Classifier;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.FastVector;
import weka.core.Instances;
import java.util.ArrayList;
import weka.core.Utils;

/**
 * Class to connect user response on click to classifier
 * Returns risk score
 * @author 1Air
 *
 */
public class ClickResponse {

    /**
     * Just an example. This method will be replaced by Tobi's classifier.
     * @param userInfo
     * @return
     */
    //    public double response(double[] userInfo){
    //	double risk_score = 0.0;
    //	for (double i : userInfo) {
    //	    risk_score += i;
    //	}
    //	return risk_score;
    //    }

    /**
     * when user clicked the "calculation" button, this method will call UserInformationCollector
     * first, pass the array into classifier. Then call GraphGenerator to display the final result.
     * @param userInput 
     * @throws Exception 
     * @return
     */
    @SuppressWarnings("deprecation")
    public static double response(Object[] userInput) throws Exception {
	FastVector attributes;
	FastVector attAge;
	FastVector attAgeNum;
	FastVector attGender;
	FastVector attClass;
	FastVector attExposure;
	FastVector attComorbid;
	Instances testing;
	double[] values;

	// 1. set up attributes
	attributes = new FastVector();

	//age as decade
	attGender = new FastVector();
	attGender.addElement("male");
	attGender.addElement("female");
	attributes.addElement(new Attribute("gender", attGender));


	//age
	attAgeNum = new FastVector();
	attributes.addElement(new Attribute("ageAsNum", attAgeNum));

	//age as decade
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
	attributes.addElement(new Attribute("ageAsDecade", attAge));

	//HealthcareRelatedExposure
	attExposure = new FastVector();
	attExposure.addElement("FALSE");
	attExposure.addElement("TRUE");
	attributes.addElement(new Attribute("exposure", attExposure));

	//comorbid
	attComorbid = new FastVector();
	attComorbid.addElement("FALSE");
	attComorbid.addElement("TRUE");
	attributes.addElement(new Attribute("comorbid", attComorbid));

	//class
	attClass = new FastVector();
	attClass.addElement("released");
	attClass.addElement("isolated");
	attClass.addElement("deceased");
	attributes.addElement(new Attribute("class", attClass));

	double risk_score = 0.0;

	// 2. create Instances object
	testing = new Instances("Test dataset", attributes, 0);

	// 3. fill with data
	values = new double[testing.numAttributes()];

	for (int i=0; i < userInput.length; i++) {
	    if (i==0) {
		values[i] = attGender.indexOf(userInput[i]);
	    } else if (i==2) {
		values[i] = attAge.indexOf(userInput[i]);
	    } else if (i==3) {
		values[i] = attComorbid.indexOf(userInput[i]);
	    }else if (i==4) {
		values[i] = attExposure.indexOf(userInput[i]);
	    }else {
		values[i] = (double) userInput[i];
	    }

	}

	// add data to instance
	testing.add(new DenseInstance(1.0, values));

	Double[] scores = WekaPipeline.pipeline();
	Classifier[] models = WekaClassifier.getModels();
	Classifier bestModel = WekaPipeline.selectBestModel(scores, models);

	System.out.println("best model done, now classify");

	// perform prediction
	double myValue = bestModel.classifyInstance(testing.lastInstance());

	System.out.println("classification done, now predict");

	// get the name of class value
	String prediction = testing.classAttribute().value((int) myValue);

	//risk_score = WekaPipeline.predictOnUserInput(bestModel, testing, null);

	System.out.println("The predicted value of the instance [" 
		+ "]: " + prediction);

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
