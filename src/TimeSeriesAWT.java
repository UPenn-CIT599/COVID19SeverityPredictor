import org.jfree.chart.ChartFactory; 
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.data.general.SeriesException; 
import org.jfree.data.time.Second; 
import org.jfree.data.time.TimeSeries; 
import org.jfree.data.time.TimeSeriesCollection; 
import org.jfree.data.xy.XYDataset; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities;

/**
 * TimeSeries_AWT is responsible for creating time series charts from XY data
 * @author cbusc
 *
 */
public class TimeSeries_AWT extends ApplicationFrame
{
	/**
	 * Constructor, which implements ChartPanel for interfacing with JSwing
	 * @param title
	 */
	public TimeSeries_AWT(String title)
	{
	}
	
	/**
	 * Creates X Y dataset for time series visualization
	 * @return TimeSeriesCollection
	 */
	private XYDataset createDataset()
	{
	}
	
	/**
	 * Creates TimeSeries chart
	 * @param XY dataset
	 * @return JFreeChart timeSeries
	 */
	private JFreeChart createChart(final XYDataset dataset)
	{
	}
}
