import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;

/**
 * Generates a patient database from trends in the predicate scientific article
 * parsed by RiskFactorReader.
 * 
 * @author cbusc
 *
 */
public class PatientGenerator {
    private static ArrayList<Patient> patients;
	private static Map<String, Double> riskfactorToAbsoluteRiskTrue;
	private static Map<String, Double> riskfactorToAbsoluteRiskFalse;
    private static Map<String, Double> riskfactorToAttributableRisk;
    private static Map<String, Double[]> riskfactorToRangeDeceased;
    private static Map<String, Double[]> riskfactorToRangeRecovered;
    private static double mortalityRate;

    /**
     * Reads in data from a CSV file and stores in arraylist of Patient objects
     */
    public static void generatePatients() {
	// Initialize risk factors and read-in risk and range values associated
	// therewith
    riskfactorToAbsoluteRiskTrue = RiskFactorReader.getRiskfactorToAbsoluteRiskTrue();
    riskfactorToAbsoluteRiskFalse = RiskFactorReader.getRiskfactorToAbsoluteRiskFalse();
	riskfactorToAttributableRisk = RiskFactorReader.getRiskfactorToAttributableRisk();
	riskfactorToRangeDeceased = RiskFactorReader.getRiskfactorToRangeDeceased();
	riskfactorToRangeRecovered = RiskFactorReader.getRiskfactorToRangeRecovered();
	mortalityRate = RiskFactorReader.getMortalityRate();
	patients = new ArrayList<Patient>();
	for (int i = 0; i < 10001; i++) {
	    // Set outcome to "deceased" with probability defined as the mortality rate
	    // of the cited scientific article. Otherwise set to recovered.
	    // All risk factors will be reconstruction predicated on outcome.
	    String outcome = "-1";
	    if (probableBoolean(mortalityRate)) {
		outcome = "deceased";
	    } else {
		outcome = "recovered";
	    }

	    // Generate data features seen to be associated with COVID-19 mortality.
	    // Binary features:
	    // For each boolean risk factor, if the outcome is "deceased", then generate true with a 
	    // probability equivalent to absolute mortality risk of a patient with the risk factor. 
	    // If the outcome is "recovered", then generate true with a probability equivalent to the
	    // absolute mortality risk of a patient without that risk factor.
	    // This method is not perfect by any means. 
	    
	    boolean comorbidity = generateBinary(outcome, riskfactorToAbsoluteRiskTrue.get("Comorbidity"), 
	    				riskfactorToAbsoluteRiskFalse.get("Comorbidity"));
	    boolean currentSmoker = generateBinary(outcome, riskfactorToAbsoluteRiskTrue.get("Current smoker"), 
				riskfactorToAbsoluteRiskFalse.get("Current smoker"));
	    boolean respiratoryRateGreaterThan24 = generateBinary(outcome, riskfactorToAbsoluteRiskTrue.get("Respiratory rate > 24"), 
				riskfactorToAbsoluteRiskFalse.get("Respiratory rate > 24"));
	    boolean temperatureGreaterThan37 = generateBinary(outcome, riskfactorToAbsoluteRiskTrue.get("Temperature > 37.3"), 
				riskfactorToAbsoluteRiskFalse.get("Temperature > 37.3"));
	    boolean groundGlassOpacity = generateBinary(outcome, riskfactorToAbsoluteRiskTrue.get("Consolidation on x-ray"), 
					riskfactorToAbsoluteRiskFalse.get("Consolidation on x-ray"));
	    boolean gender = generateBinary(outcome, 0.50, 0.50); //Gender is hard-coded to be random

	    // Continuous features:
	    // Pass in outcome. Based on whether the patient deceased or survived,
	    // assign a randomly generated risk factor value generated from the published
	    // range.
	    // For example, if "deceased" a patient's white blood cell count will be
	    // randomly generated
	    // between the range 6.9 - 13.9, versus a "survived" patient generated between
	    // 4.3 - 7.7.
	    double age, wbc, lymphocyteCount, platelets, albumin, lactateDehydrogenase, troponinI, dDimer, ferritin,
		    interleukin6, procalcitonin = -1;
	    if (outcome.equals("deceased")) {
		age = randomBetweenRange(riskfactorToRangeDeceased.get("Age")[0],
			riskfactorToRangeDeceased.get("Age")[1]);
		wbc = randomBetweenRange(riskfactorToRangeDeceased.get("White blood cell count")[0],
			riskfactorToRangeDeceased.get("White blood cell count")[1]);
		lymphocyteCount = randomBetweenRange(riskfactorToRangeDeceased.get("Lymphocyte count")[0],
			riskfactorToRangeDeceased.get("Lymphocyte count")[1]);
		platelets = randomBetweenRange(riskfactorToRangeDeceased.get("Platelets")[0],
			riskfactorToRangeDeceased.get("Platelets")[1]);
		albumin = randomBetweenRange(riskfactorToRangeDeceased.get("Albumin")[0],
			riskfactorToRangeDeceased.get("Albumin")[1]);
		lactateDehydrogenase = randomBetweenRange(riskfactorToRangeDeceased.get("Lactate Dehydrogenase")[0],
			riskfactorToRangeDeceased.get("Lactate Dehydrogenase")[1]);
		troponinI = randomBetweenRange(riskfactorToRangeDeceased.get("Troponin I")[0],
			riskfactorToRangeDeceased.get("Troponin I")[1]);
		dDimer = randomBetweenRange(riskfactorToRangeDeceased.get("D-dimer")[0],
			riskfactorToRangeDeceased.get("D-dimer")[1]);
		ferritin = randomBetweenRange(riskfactorToRangeDeceased.get("Ferritin")[0],
			riskfactorToRangeDeceased.get("Ferritin")[1]);
		interleukin6 = randomBetweenRange(riskfactorToRangeDeceased.get("Interleukin 6")[0],
			riskfactorToRangeDeceased.get("Interleukin 6")[1]);
		procalcitonin = randomBetweenRange(riskfactorToRangeDeceased.get("Procalcitonin")[0],
			riskfactorToRangeDeceased.get("Procalcitonin")[1]);
	    } else {
		age = randomBetweenRange(riskfactorToRangeRecovered.get("Age")[0],
			riskfactorToRangeRecovered.get("Age")[1]);
		wbc = randomBetweenRange(riskfactorToRangeRecovered.get("White blood cell count")[0],
			riskfactorToRangeRecovered.get("White blood cell count")[1]);
		lymphocyteCount = randomBetweenRange(riskfactorToRangeRecovered.get("Lymphocyte count")[0],
			riskfactorToRangeRecovered.get("Lymphocyte count")[1]);
		platelets = randomBetweenRange(riskfactorToRangeRecovered.get("Platelets")[0],
			riskfactorToRangeRecovered.get("Platelets")[1]);
		albumin = randomBetweenRange(riskfactorToRangeRecovered.get("Albumin")[0],
			riskfactorToRangeRecovered.get("Albumin")[1]);
		lactateDehydrogenase = randomBetweenRange(riskfactorToRangeRecovered.get("Lactate Dehydrogenase")[0],
			riskfactorToRangeRecovered.get("Lactate Dehydrogenase")[1]);
		troponinI = randomBetweenRange(riskfactorToRangeRecovered.get("Troponin I")[0],
			riskfactorToRangeRecovered.get("Troponin I")[1]);
		dDimer = randomBetweenRange(riskfactorToRangeRecovered.get("D-dimer")[0],
			riskfactorToRangeRecovered.get("D-dimer")[1]);
		ferritin = randomBetweenRange(riskfactorToRangeRecovered.get("Ferritin")[0],
			riskfactorToRangeRecovered.get("Ferritin")[1]);
		interleukin6 = randomBetweenRange(riskfactorToRangeRecovered.get("Interleukin 6")[0],
			riskfactorToRangeRecovered.get("Interleukin 6")[1]);
		// Procalcitonin in recovered patients ranges from 0.1 to 0.1; therefore it is
		// impossible to generate
		// a random value. We simply here use the one value reported in the literature,
		// 0.1.
		procalcitonin = riskfactorToRangeRecovered.get("Procalcitonin")[0];
	    }

	    // Initialize patient object
	    Patient patient = new Patient(age, gender, comorbidity, currentSmoker, respiratoryRateGreaterThan24,
		    temperatureGreaterThan37, groundGlassOpacity, wbc, lymphocyteCount, platelets, albumin,
		    lactateDehydrogenase, troponinI, dDimer, ferritin, interleukin6, procalcitonin, outcome);
	    patients.add(patient);
	}
    }

    /**
     * Gets array list of patients
     * 
     * @return ArrayList patients
     */
    public static ArrayList<Patient> getPatients() {
	return patients;
    }

    /**
     * Returns true with rPos (Mortality risk given risk factor) probability if the outcome is "deceased," and with probability
     * rNeg (Mortality risk given absence of risk factor) if the outcome is "recovered." This is not a perfect estimation of prevalence. 
     * 
     * @param outcome, probability
     * @return true or false
     */
    public static boolean generateBinary(String outcome, double rPos, double rNeg) {
	if (outcome.equals("deceased")) {
	    return probableBoolean(rPos);
	} else {
	    return probableBoolean(rNeg);
	}
    }

    /**
     * Returns true with the probability of the argument passed
     * 
     * @param probability
     * @return true or false
     * 
     */
    public static boolean probableBoolean(double probability) {
	return (Math.random() < probability);
    }

    /**
     * Generates random double between two parameterized values
     * 
     * @param least (min)
     * @param bound (max)
     * @return random double
     */
    public static double randomBetweenRange(Double min, Double max) {
	return ThreadLocalRandom.current().nextDouble(min, max);
    }
}
