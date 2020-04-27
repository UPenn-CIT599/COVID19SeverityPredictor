import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.ui.RefineryUtilities;

public class ResultPanel{
	public ResultPanel() {
		
	}
	/**
	 * This method will create a new JFrame and a JPanel. Then take the output from classifier and data analysis class, add the final risk score and two
	 * graphs as three components.
	 */
	public void placeComponents(Double score) {
		
		JFrame frame;
		JPanel panel;
		JLabel riskScoreLabel;
		JLabel riskScore;
		
		//Create a stacked bar chart with loaded patient data
		BarChart barChart = new BarChart("Risk Factors Associated With COVID-19 Mortality"); 
		barChart.setSize(700, 400);
		RefineryUtilities.centerFrameOnScreen(barChart);
		barChart.setVisible(true);
		
		//Create a box and whisker plot with loaded patient data
		BoxAndWhiskerPlot boxWhisk = new BoxAndWhiskerPlot("Box and Whisker Plot");
		boxWhisk.setSize(1000, 800);
		boxWhisk.setVisible(true);
		
		// Create JFrame
        frame = new JFrame("Result Panel");
        RefineryUtilities.centerFrameOnScreen(frame);
        // Setting the width and height of frame
        frame.setSize(400, 400);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        RefineryUtilities.centerFrameOnScreen(frame);

        /* 
         * Create a panel
         */
        panel = new JPanel();    
        /**
         * Add panel to frame
         */
        frame.add(panel);

        // set the panel to be visible
        frame.setVisible(true);

        /* 
         * don't use any specific layout
         */
        panel.setLayout(null);
        
        /**
         * Set the label content, size, and font.
         */
        riskScoreLabel = new JLabel("The predicted risk of mortality is: ");
        riskScoreLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        riskScoreLabel.setBounds(60,100,300,100);
        panel.add(riskScoreLabel);
        
        /**
         * Take the output from classifier and display in the final panel.
         */
        String scoreString = String.valueOf(score);
        riskScore = new JLabel(scoreString+"%");
        riskScore.setFont(new Font("Arial", Font.PLAIN, 30));
        riskScore.setBounds(160,150,200,100);
        panel.add(riskScore);	
	}	
}
