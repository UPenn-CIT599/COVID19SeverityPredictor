import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.evaluation.NominalPrediction;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.functions.SimpleLogistic;
import weka.classifiers.rules.DecisionTable;
import weka.classifiers.rules.PART;
import weka.classifiers.trees.DecisionStump;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.RandomForest;
import weka.core.FastVector;
import weka.core.Instances;

/***
 * 
 * @author Tobi Olatunji
 * Machine learning classifier class
 */
public class WekaClassifier {

    /**
     * Static class fits the classifier on the training set 
     * and predicts on the test set
     * @param model weka classifier object
     * @param trainingSet weka instance object, training data
     * @param testingSet weka instance object, test data
     * @return Weka Evaluation class
     * @throws Exception
     */
    public static Evaluation classify(Classifier model,
	    Instances trainingSet, Instances testingSet) throws Exception {
	Evaluation evaluation = new Evaluation(trainingSet);

	model.buildClassifier(trainingSet);
	evaluation.evaluateModel(model, testingSet);
	//System.out.println(evaluation.toSummaryString("\nResults\n======\n", false));

	return evaluation;
    }

    /**
     * Compares classifier predictions to ground truth
     * and returns the accuracy of the model on
     * the test set
     * @param predictions
     * @return
     */
    public static double calculateAccuracy(FastVector predictions) {
	double correct = 0;

	for (int i = 0; i < predictions.size(); i++) {
	    NominalPrediction np = (NominalPrediction) predictions.elementAt(i);
	    if (np.predicted() == np.actual()) {
		correct++;
	    }
	}

	return 100 * correct / predictions.size();
    }


    /**
     * Performs cross validation. Splits the data into 
     * required number of folds and returns the split data
     * @param data
     * @param numberOfFolds
     * @return
     */
    public static Instances[][] crossValidationSplit(Instances data, int numberOfFolds) {
	Instances[][] split = new Instances[2][numberOfFolds];

	for (int i = 0; i < numberOfFolds; i++) {
	    split[0][i] = data.trainCV(numberOfFolds, i);
	    split[1][i] = data.testCV(numberOfFolds, i);
	}

	return split;
    }

    /**
     * Instantiates several weka classifier objects,
     * sets hyperparameters, and returns the models
     * @return
     * @throws Exception
     */
    public static Classifier[] getModels() throws Exception {
	String[] mlpOptions = new String[7];
	mlpOptions[0] = "-L"; 
	mlpOptions[1] = "0.001";
	mlpOptions[2] = "-M"; 
	mlpOptions[3] = "0.9";
	mlpOptions[4] = "-N"; 
	mlpOptions[5] = "-100"; 
	mlpOptions[6] = "-D"; 
	MultilayerPerceptron mlp = new MultilayerPerceptron();
	mlp.setOptions(mlpOptions);


	String[] rfOptions = new String[4];
	RandomForest rf = new RandomForest();
	rfOptions[0] = "-I";
	rfOptions[1] = "50";
	rfOptions[2] = "-depth";
	rfOptions[3] = "2";
	rf.setOptions(rfOptions);

	// Use a set of classifiers
	Classifier[] models = { 
//		new J48(), // a decision tree
//		new PART(), 
//		new DecisionTable(),//decision table majority classifier
//		new DecisionStump(), //one-level decision tree
//		new NaiveBayes(),
//		rf,
//		new SimpleLogistic(),
		mlp

	};

	return models;

    }


    /**
     * Predict on test instance (user input)
     * @param model
     * @param evaluation
     * @param testingSet
     * @return
     * @throws Exception
     */
    public static double predict(Classifier model,
	    Instances testing) throws Exception {

	double myValue = model.classifyInstance(testing.lastInstance());
	return myValue;
    }
    
    /**
     * Iterates through a preselected number of models
     * Trains and evaluates each model on the training test
     * selects the best model,
     * writes the model to file
     * and returns the best model
     * @return
     * @throws Exception
     */
    public static Classifier train() throws Exception {
	Classifier[] models = WekaClassifier.getModels();
	
	Double[] scores = WekaPipeline.pipeline();
	int bestModel_idx = WekaPipeline.selectBestModel(scores, models);

	Instances data = WekaPipeline.readData();
	Classifier bestModel = models[bestModel_idx];
	bestModel.buildClassifier(data); 
	weka.core.SerializationHelper.write("mlp.model", bestModel);
	return bestModel;
    } 
}