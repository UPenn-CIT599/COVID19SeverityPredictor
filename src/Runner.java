import org.jfree.ui.RefineryUtilities;

/**
 * Driver class for the COVID19 mortality predictor
 * 
 * @author cbusc
 *
 */
public class Runner {
    /**
     * Main method
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
	double[] recoveredUser = {49.78699774, 1, 0, 1, 0, 0, 0, 4.547949847, 0.852495523, 174.1727584, 33.35398543, 277.718766, 3.545130117, 0.587936248, 544.3907973, 5.579234687, 0.1};
	ClickResponse.response(recoveredUser);
	
	double[] deceasedUser = {64.97170575, 0, 0, 1, 1, 0, 0, 10.72312152, 0.652016378, 158.7812943, 30.6666677, 516.5667479, 81.30478587, 5.806466858, 1547.65957, 7.97997343, 0.346800831};
	ClickResponse.response(deceasedUser);
//	RiskFactorReader.readCSV();
//	PatientGenerator.generatePatients();
//	PanelComponents panel = new PanelComponents();
//	panel.placeComponents();
    }
}

// WriteToCSV.write();

//	double[] userInput = {65.43, 0, 1, 0, 1, 1, 1, 12.27030957, 0.517313417, 187.0018316, 31.05054804, 439.3447699, 23.39339414, 9.134254356, 1800.799158, 7.746217501, 0.124234627};
//	ClickResponse.response(userInput);