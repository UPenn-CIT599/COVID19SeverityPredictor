import java.io.BufferedReader;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.Logistic;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.functions.SimpleLogistic;
import weka.classifiers.rules.DecisionTable;
import weka.classifiers.rules.PART;
import weka.classifiers.trees.DecisionStump;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.RandomForest;
import weka.core.FastVector;
import weka.core.Instances;

public class WekaRunner {

	public static void main(String[] args) throws Exception {
	    	BufferedReader datafile = WekaFileReader.readDataFile("breast-cancer.txt");

		Instances data = new Instances(datafile);
		data.setClassIndex(data.numAttributes() - 1);

		// Do 10-split cross validation
		Instances[][] split = WekaClassifier.crossValidationSplit(data, 10);

		// Separate split into training and testing arrays
		Instances[] trainingSplits = split[0];
		Instances[] testingSplits = split[1];
		
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
				new J48(), // a decision tree
				new PART(), 
				new DecisionTable(),//decision table majority classifier
				new DecisionStump(), //one-level decision tree
				new NaiveBayes(),
				rf,
				new SimpleLogistic(),
				mlp
				
		};

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

			// Print current classifier's name and accuracy in a complicated,
			// but nice-looking way.
			System.out.println("Accuracy of " + models[j].getClass().getSimpleName() + ": "
					+ String.format("%.2f%%", accuracy)
					+ "\n---------------------------------");
		}

	}

}
