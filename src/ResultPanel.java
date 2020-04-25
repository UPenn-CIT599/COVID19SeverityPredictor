import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
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
		JLabel Chart1;
		
		//Create a stacked bar chart with loaded patient data
		BarChart barChart = new BarChart("Risk Factors Associated With COVID-19 Mortality"); 
		barChart.setSize(700, 400);
		RefineryUtilities.centerFrameOnScreen(barChart);
		barChart.setVisible(true);
		
		//Create a box and whisker plot with loaded patient data
		BoxAndWhiskerPlot boxWhisk = new BoxAndWhiskerPlot("Box and Whisker Plot");
		boxWhisk.setSize(1000, 800);
		//RefineryUtilities.centerFrameOnScreen(boxWhisk);
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
        /* 
         * place components in panel
         */
        //placeComponents(panel);

        // set the panel to be visible
        frame.setVisible(true);

        /* 
         * don't use any specific layout
         */
        panel.setLayout(null);
        
        riskScoreLabel = new JLabel("The predicted risk of mortality is: ");
        riskScoreLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        riskScoreLabel.setBounds(60,100,300,100);
        panel.add(riskScoreLabel);
        
        String scoreString = String.valueOf(score);
        riskScore = new JLabel(scoreString+"%");
        riskScore.setFont(new Font("Arial", Font.PLAIN, 30));
        riskScore.setBounds(160,150,200,100);
        panel.add(riskScore);
           
//        BufferedImage bimg;
//		try {
//			bimg = ImageIO.read(new File("images.jpg"));
//			//ImageIcon icon = new ImageIcon("images.jpg");
//	        Image scaleImage = bimg.getScaledInstance(500, 500,Image.SCALE_DEFAULT);
//	        ImageIcon resizedIcon = new ImageIcon(scaleImage);
//	        Chart1 = new JLabel(resizedIcon);
//	        Chart1.setBounds(250,250,500,500);
//	        panel.add(Chart1);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        
		
		
	}
	
	
}
