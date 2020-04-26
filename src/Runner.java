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
	RiskFactorReader.readCSV();
	PatientGenerator.generatePatients();
	PanelComponents panel = new PanelComponents();
	panel.placeComponents();
    }
}
<<<<<<< HEAD


=======
>>>>>>> 984c0fedeef383b045b4b09b4022e1f7b798530c
