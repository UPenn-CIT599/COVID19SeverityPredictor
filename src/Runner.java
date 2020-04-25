import org.jfree.ui.RefineryUtilities;


/**
 * Driver class for the COVID19 mortality predictor
 * @author cbusc
 *
 */
public class Runner {
    /**
     * Main method
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception
    {
		RiskFactorReader.readCSV();
		PatientGenerator.generatePatients();
		PanelComponents panel = new PanelComponents();
        panel.placeComponents();
    }
}
        
        //WriteToCSV.write();

//	double[] userInput = {65.43, 0, 1, 0, 1, 1, 1, 12.27030957, 0.517313417, 187.0018316, 31.05054804, 439.3447699, 23.39339414, 9.134254356, 1800.799158, 7.746217501, 0.124234627};
//	ClickResponse.response(userInput);