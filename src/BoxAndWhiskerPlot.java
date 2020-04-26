import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.util.ArrayList;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BoxAndWhiskerRenderer;
import org.jfree.data.statistics.BoxAndWhiskerCategoryDataset;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
/**
 * Box and whisker plot class for implementation in JSwing. 
 * @author cbusc
 *
 */
public class BoxAndWhiskerPlot extends ApplicationFrame
{
	/**
	 * Creates a new box and whisker plot of patient laboratory data
	 * @param title
	 */
	public BoxAndWhiskerPlot(String title)
	{
		super(title);
		BoxAndWhiskerCategoryDataset dataset = createDataset();
		//Set axes and with custom fonts
		CategoryAxis xAxis = new CategoryAxis("Risk Factor");
		xAxis.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 17 ));
		xAxis.setLabelFont(new Font("SansSerif", Font.PLAIN, 17 ));
		NumberAxis yAxis = new NumberAxis ("Laboratory Value");
		yAxis.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 17 ));
		yAxis.setLabelFont(new Font("SansSerif", Font.PLAIN, 17 ));
		yAxis.setAutoRangeIncludesZero(false);
		//Instantiate chart renderer and fill boxes
		BoxAndWhiskerRenderer renderer = new BoxAndWhiskerRenderer();
		renderer.setFillBox(true);	
		//Paint recovered series blue using renderer
		GradientPaint gpRecovered = new GradientPaint(0.0f, 0.0f, Color.blue, 0.0f, 0.0f, 
		new Color(0, 0, 64));
		renderer.setSeriesPaint(1, gpRecovered);
		//Paint deceased series red using renderer
		GradientPaint gpDeceased = new GradientPaint(0.0f, 0.0f, Color.red, 0.0f, 0.0f, 
				new Color(0, 0, 64));
		renderer.setSeriesPaint(0, gpDeceased);
		//Instatiate and set background color using plot
		CategoryPlot plot = new CategoryPlot(dataset, xAxis, yAxis, renderer);
		//plot.setBackgroundPaint(Color.lightGray);
		//Constructor is JFreeChart(String title, Font titleFont, Plot plot, boolean createLegend)
		JFreeChart chart = new JFreeChart(
				"Admission Day Laboratory Values and COVID19 Mortality",
				new Font("SansSerif", Font.BOLD, 30),
				plot,
				true
		);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(800, 600));
		setContentPane(chartPanel);
	}	
	
	/**
	 * Creates categorical dataset of patient laboratory values for box and whisker plot
	 * @return
	 */
	private BoxAndWhiskerCategoryDataset createDataset()
	{
		//Get patients arraylist from patient generator
		ArrayList<Patient> patients = PatientGenerator.getPatients();
		//Declare arraylists of laboratory values for all patients
		//"deceased"
		ArrayList<Double> wbcD = new ArrayList<>();
		ArrayList<Double> il6D = new ArrayList<>();
		ArrayList<Double> dDimerD = new ArrayList<>();
		ArrayList<Double> tropD = new ArrayList<>();
		//"recovered"
		ArrayList<Double> wbcR = new ArrayList<>();
		ArrayList<Double> il6R = new ArrayList<>();
		ArrayList<Double> dDimerR = new ArrayList<>();
		ArrayList<Double> tropR = new ArrayList<>();

		
		//Fill arraylists with patient data
		for (Patient p : patients)
		{
			if (p.getOutcome().equals("deceased"))
			{
				wbcD.add(p.getWbc());
				il6D.add(p.getInterleukin6());
				dDimerD.add(p.getdDimer());
				tropD.add(p.getTroponinI());
			}
			else
			{
				wbcR.add(p.getWbc());
				il6R.add(p.getInterleukin6());
				dDimerR.add(p.getdDimer());
				tropR.add(p.getTroponinI());
			}
		}
		//Declare default category dataset
		final DefaultBoxAndWhiskerCategoryDataset dataset 
        = new DefaultBoxAndWhiskerCategoryDataset();
		
		//Pass in these parameters to add to dataset (Values, Series (outcome), Category (risk factor))
		//Add deceased values to dataset
		dataset.add(wbcD, "Deceased", "WBC, x 10 ^ 9 per L");
		dataset.add(il6D, "Deceased", "IL-6, pg/mL");
		dataset.add(dDimerD, "Deceased", "D-Dimer, ug/mL");
		//dataset.add(tropD, "Deceased", "Troponin I, pg/mL");
		//Add recovered values to dataset
		dataset.add(wbcR, "Recovered", "WBC, x 10 ^ 9 per L");
		dataset.add(il6R, "Recovered", "IL-6, pg/mL");
		dataset.add(dDimerR, "Recovered", "D-Dimer, ug/mL");
		//dataset.add(tropR, "Recovered", "Troponin I, pg/mL");
	
		
		return dataset;	
	}
}
