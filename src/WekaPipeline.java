import java.io.BufferedReader;
import java.io.File;

import weka.core.converters.CSVLoader;
import weka.core.converters.ConverterUtils.DataSource;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.FastVector;
import weka.core.Instances;

public class WekaPipeline {
    
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
	    	
	    	//CSVLoader loader = new CSVLoader();
	    	//loader.setSource(new File("filename.csv"));
	    	//Instances trainingDataSet = loader.getDataSet();
	    	
	    	DataSource source = new DataSource("PatientInfo.csv");
	    	Instances data = source.getDataSet();

		// Instances data = new Instances(datafile);
		data.setClassIndex(data.numAttributes() - 1);

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

			// Print current classifier's name and accuracy in a complicated,
			// but nice-looking way.
			System.out.println("Accuracy of " + models[j].getClass().getSimpleName() + ": "
					+ String.format("%.2f%%", accuracy)
					+ "\n---------------------------------");
		}
		return scores;

	}

	/**
	 * Selects best score based on accuracy scores
	 * of several models (weka classifiers)
	 * @param scores
	 * @param models
	 * @return
	 */
	public static Classifier selectBestModel(Double[] scores, Classifier[] models) {
	    double max_score = 0.0;
	    Classifier best_classifier = new J48(); // initialize as any classifier
	    for (int i=0; i < scores.length; i++) {
		if (scores[i] > max_score) {
		    best_classifier = models[i];
		    max_score = scores[i];
		}
	    }
	    
	    return best_classifier;
	}
	
	
	/**
	 * [WIP] Collects data from users and predicts risk
	 * 
	 * @param model
	 * @param userInput
	 * @param evaluation
	 * @return
	 * @throws Exception
	 */
	public static double predictOnUserInput(Classifier model, Instances userInput,
		Evaluation evaluation) throws Exception {
	    double risk_score = 0.0;
	    evaluation = WekaClassifier.predict(model, evaluation, userInput);
	    FastVector predictions = new FastVector();
	    predictions.appendElements(evaluation.predictions());
	    risk_score = predictions.size();
	    return risk_score;
	}
	
}
