import java.io.BufferedReader;
import java.io.File;
import java.util.Arrays;
import java.util.List;

import weka.core.converters.CSVLoader;
import weka.core.converters.ConverterUtils.DataSource;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.FastVector;
import weka.core.Instances;

public class WekaPipeline {

    
    /**
     * Reads in the csv and converts to a datasource
     * for downstream classification
     * @return
     * @throws Exception
     */
    public static Instances readData() throws Exception {
	DataSource source = new DataSource("PatientInfoClean.csv");
	Instances data = source.getDataSet();

	// Instances data = new Instances(datafile);
	data.setClassIndex(data.numAttributes() - 1);
	return data;
    }
    
    
    /**
     * Training pipeline
     * Evaluates performance of various models and returns
     * accuracy scores
     * on the dataset
     * @param args
     * @throws Exception
     */
    public static Double[] pipeline() throws Exception {
	//BufferedReader datafile = WekaFileReader.readDataFile("breast-cancer.txt");

	Instances data = readData();

	// Do 10-split cross validation
	Instances[][] split = WekaClassifier.crossValidationSplit(data, 5);

	// Separate split into training and testing arrays
	Instances[] trainingSplits = split[0];
	Instances[] testingSplits = split[1];

	Classifier[] models = WekaClassifier.getModels();

	Double[] scores = new Double[models.length];
	
	// Run for each model
	for (int j = 0; j < models.length; j++) {

	    // Collect every group of predictions for current model in a FastVector
	    FastVector predictions = new FastVector();
	    
	    
	    // For each training-testing split pair, train and test the classifier
	    for (int i = 0; i < trainingSplits.length; i++) {
		Evaluation validation = WekaClassifier.classify(models[j], trainingSplits[i], testingSplits[i]);

		predictions.appendElements(validation.predictions());

		// Uncomment to see the summary for each training-testing pair.
		//System.out.println(models[j].toString());
	    }

	    // Calculate overall accuracy of current classifier on all splits
	    double accuracy = WekaClassifier.calculateAccuracy(predictions);
	    scores[j] = accuracy;
	    
	    // weka.core.SerializationHelper.write("/path/tree.model", models[j]);

	    // Print current classifier's name and accuracy in a complicated,
	    // but nice-looking way.
	    System.out.println("Accuracy of " + models[j].getClass().getSimpleName() + ": "
		    + String.format("%.2f%%", accuracy)
		    + "\n---------------------------------");
	}
	return scores;

    }

    /**
     * Selects index of highest scoring model based on accuracy scores
     * of several models (weka classifiers)
     * @param scores
     * @param models
     * @return
     */
    public static int selectBestModel(Double[] scores, Classifier[] models) {
	double max_score = 0.0;
	int best_idx = 0;
	Classifier best_classifier = new J48(); // initialize as any classifier
	for (int i=0; i < scores.length; i++) {
	    if (scores[i] > max_score) {
		best_classifier = models[i];
		max_score = scores[i];
		best_idx = i;
	    }
	}

	return best_idx;
    }


    /**
     * Collects data from users as instances,
     * retrains the classifier and predicts risk
     * @param testing
     * @return
     * @throws Exception
     */
    public static double predictOnUserInput(Instances testing) throws Exception {
	double risk_score = 0.0;
	
//	Classifier bestModel = WekaClassifier.train();
	
	String modelPath = "mlp.model"; 
	//load model
        Classifier bestModel = (Classifier) weka.core.SerializationHelper.read(modelPath);

	System.out.println("best model selected: " + bestModel.getClass().getSimpleName());
	System.out.println("Starting classification");
	
	// perform prediction
	double myValue = WekaClassifier.predict(bestModel, testing);

	System.out.println("classification done");
	System.out.println("Starting prediction");
	
	//get the prediction percentage or distribution
        double[] percentage=bestModel.distributionForInstance(testing.lastInstance());
        risk_score = percentage[0];
        
	// get the name of class value
	String prediction = testing.classAttribute().value((int) myValue);
	System.out.println("The predicted outcome is: " + prediction); 
	
	//Format the distribution
        String distribution="";
        for(int i=0; i <percentage.length; i=i+1)
        {
            //System.out.println(i); 
            if(i==myValue)
            {
        	//System.out.println("Percentage is: " + Double.toString(percentage[i]));
                distribution=distribution+"*"+Double.toString(percentage[i])+",";
            }
            else
            {
        	//System.out.println("Percentage is: " + Double.toString(percentage[i]));
                distribution=distribution+Double.toString(percentage[i])+",";
            }
        }
        distribution=distribution.substring(0, distribution.length()-1);

        //System.out.println("Distribution:"+ distribution);

	return risk_score;
    }

    
    /**
     * Transforms user inputs as doubles to attributes 
     * ready to feed into the classifier
     * @param userInput
     * @return
     */
    @SuppressWarnings({ "deprecation", "unchecked", "rawtypes" })
    public static Instances userInputToInstance(double[] userInput) {
	FastVector attributes;
	FastVector attAge;
	FastVector attGender;
	FastVector attClass;
	FastVector attSmoker;
	FastVector attComorbid;
	FastVector attResp;
	FastVector attTemp;
	FastVector attGGO;
	FastVector attWBC;
	FastVector attLymph;
	FastVector attPlatelets;
	FastVector attAlbumin;
	FastVector attLactate;
	FastVector attTroponin;
	FastVector attDdimer;
	FastVector attFerritin;
	FastVector attIL6;
	FastVector attProcalc;
	Instances testing;
	double[] values;

	// 1. set up attributes
	attributes = new FastVector();

	// age
	attAge = new FastVector();
	attributes.addElement(new Attribute("Age", attAge));

	// GENDER
	attGender = new FastVector();
	attGender.addElement("TRUE");
	attGender.addElement("FALSE");
	attributes.addElement(new Attribute("Gender", attGender));

	// comorbid
	attComorbid = new FastVector();
	attComorbid.addElement("FALSE");
	attComorbid.addElement("TRUE");
	attributes.addElement(new Attribute("Comorbid", attComorbid));

	// CurrentSmoker
	attSmoker = new FastVector();
	attSmoker.addElement("FALSE");
	attSmoker.addElement("TRUE");
	attributes.addElement(new Attribute("CurrentSmoker", attSmoker));

	// RespiratoryRateGreaterThan24
	attResp = new FastVector();
	attResp.addElement("FALSE");
	attResp.addElement("TRUE");
	attributes.addElement(new Attribute("RespiratoryRateGreaterThan24", attResp));

	// TemperatureGreaterThan37
	attTemp = new FastVector();
	attTemp.addElement("FALSE");
	attTemp.addElement("TRUE");
	attributes.addElement(new Attribute("TemperatureGreaterThan37", attTemp));

	// GroundGlassOpacity
	attGGO = new FastVector();
	attGGO.addElement("FALSE");
	attGGO.addElement("TRUE");
	attributes.addElement(new Attribute("GroundGlassOpacity", attGGO));

	// WBC
	attWBC = new FastVector();
	attributes.addElement(new Attribute("WBC", attWBC));

	// LymphocyteCount
	attLymph = new FastVector();
	attributes.addElement(new Attribute("LymphocyteCount", attLymph));

	// Platelets
	attPlatelets = new FastVector();
	attributes.addElement(new Attribute("Platelets", attPlatelets));

	// Albumin
	attAlbumin = new FastVector();
	attributes.addElement(new Attribute("Albumin", attAlbumin));

	// LactateDehydrogenase
	attLactate = new FastVector();
	attributes.addElement(new Attribute("LactateDehydrogenase", attLactate));

	// TroponinI
	attTroponin = new FastVector();
	attributes.addElement(new Attribute("TroponinI", attTroponin));

	// D-dimer
	attDdimer = new FastVector();
	attributes.addElement(new Attribute("D-dimer", attDdimer));

	// Ferritin
	attFerritin = new FastVector();
	attributes.addElement(new Attribute("Ferritin", attFerritin));

	// Interleukin6
	attIL6 = new FastVector();
	attributes.addElement(new Attribute("Interleukin6", attIL6));

	// Procalcitonin
	attProcalc = new FastVector();
	attributes.addElement(new Attribute("Procalcitonin", attProcalc));

	// class
	attClass = new FastVector();
	attClass.addElement("recovered");
	attClass.addElement("deceased");
	attributes.addElement(new Attribute("Outcome", attClass));

	double risk_score = 0.0;

	// 2. create Instances object
	testing = new Instances("Test dataset", attributes, 0);
	

	testing.setClassIndex(attributes.size() - 1);

	// 3. fill with data
	values = new double[testing.numAttributes()];
	
	Integer[] boolean_attributes_idx = {1, 2, 3, 4, 5, 6};
	// Convert String Array to List
        List<Integer> boolean_attributes_list = Arrays.asList(boolean_attributes_idx);

	for (int i = 0; i < userInput.length; i++) {
	    if (boolean_attributes_list.contains(i)) {
		if (i==1) {
		    values[i] = attGender.indexOf(userInput[i]);
		} else if (i == 2) {
		    values[i] = attComorbid.indexOf(userInput[i]);
		} else if (i == 3) {
		    values[i] = attSmoker.indexOf(userInput[i]);
		} else if (i == 4) {
		    values[i] = attResp.indexOf(userInput[i]);
		} else if (i == 5) {
		    values[i] = attTemp.indexOf(userInput[i]);
		} else if (i == 6) {
		    values[i] = attGGO.indexOf(userInput[i]);
		} 
		
	    } else {
		values[i] = (double) userInput[i];
	    }

	}

	// add data to instance
	testing.add(new DenseInstance(1.0, values));
	return testing;
    }

}
