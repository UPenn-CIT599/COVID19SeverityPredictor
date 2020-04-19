import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
		// Create JFrame
        frame = new JFrame("Result Panel");
        // Setting the width and height of frame
        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
        
        riskScoreLabel = new JLabel("The predicted risk score is: ");
        riskScoreLabel.setBounds(100,100,200,100);
        panel.add(riskScoreLabel);
        
        String scoreString = String.valueOf(score);
        riskScore = new JLabel(scoreString);
        riskScore.setBounds(300,100,200,100);
        panel.add(riskScore);
        
        BufferedImage bimg;
		try {
			bimg = ImageIO.read(new File("images.jpg"));
			//ImageIcon icon = new ImageIcon("images.jpg");
	        Image scaleImage = bimg.getScaledInstance(500, 500,Image.SCALE_DEFAULT);
	        ImageIcon resizedIcon = new ImageIcon(scaleImage);
	        Chart1 = new JLabel(resizedIcon);
	        Chart1.setBounds(250,250,500,500);
	        panel.add(Chart1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		
		
	}
	
	
}
