import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField; 
public class PanelComponents extends JFrame implements ActionListener{
    
	/**
	 * create panel components
	 */
	private JFrame frame;
	private JPanel panel;
    private JLabel ageLabel;
    private JTextField ageText;
    private JLabel genderLabel;
    private JRadioButton rMale;
    private JRadioButton rFemale;
    private ButtonGroup bgGender;
    private JLabel chronicLabel;
    private JRadioButton rChronicDiseaseY;
    private JRadioButton rChronicDiseaseN;
    private ButtonGroup bgChronic;
    private JLabel healthercareLabel;
    private JRadioButton healthcareY;
    private JRadioButton healthcareN;
    private ButtonGroup bgHealthcare;
    private JLabel symptomLabel;
    private JTextField symptomText;
    private JLabel testLabel;
    private JTextField testText;
    private JButton calculateButton;
    int[] userInfo = new int[6];
    
    /**
     * This method create a JFrame and a JPanel. Then add components like label, text area, button, radio Button to the panel. 
     */
    public void placeComponents() {
    	
    	// Create JFrame
        frame = new JFrame("COVID19 morality predictor");
        // Setting the width and height of frame
        frame.setSize(400, 400);
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
         * Didn't use any special layout
         */
        panel.setLayout(null);

        // create label for age
        ageLabel = new JLabel("Age (years):");
        /* 
         * Set the size and position of label
         */
        ageLabel.setBounds(10,20,80,25);
        panel.add(ageLabel);
        
        /* 
         * Set text area for user's input
         */
        ageText = new JTextField(20);
        ageText.setBounds(200,20,40,25);
        panel.add(ageText);
        
        
        //Gender
        /**
         * Create label for gender
         */
        genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(10,50,80,25);
        panel.add(genderLabel);
        
        /**
         * Create radio button for "Male" and "Female"
         */
        rMale = new JRadioButton("Male");
        rFemale = new JRadioButton("Female");
        
        /**
         * Set the size and position of buttons
         */
        rMale.setBounds(200, 50, 80, 25);
        rFemale.setBounds(280, 50, 80, 25);
        
        /**
         * Group the buttons, so that only one in the group can be selected
         */
        bgGender = new ButtonGroup();
        bgGender.add(rMale);
        bgGender.add(rFemale);
        
        /**
         * Add button to the panel
         */
        panel.add(rMale);
        panel.add(rFemale);
        
      //History of chronic disease
        /**
         * Create label for gender
         */
        chronicLabel = new JLabel("History of chronic disease:");
        chronicLabel.setBounds(10,80,200,25);
        panel.add(chronicLabel);
        /**
         * Create radio button for ChronicDisease
         */
        rChronicDiseaseY = new JRadioButton("Yes");
        rChronicDiseaseN = new JRadioButton("No");
        
        /**
         * Set the size and position of buttons
         */
        rChronicDiseaseY.setBounds(200, 80, 80, 25);
        rChronicDiseaseN.setBounds(280, 80, 80, 25);
        
        /**
         * Group the buttons, so that only one in the group can be selected
         */
        bgChronic = new ButtonGroup();
        bgChronic.add(rChronicDiseaseY);
        bgChronic.add(rChronicDiseaseN);
        
        /**
         * Add button to the panel
         */
        panel.add(rChronicDiseaseY);
        panel.add(rChronicDiseaseN);
        

      //Healthcare-related exposure
        /**
         * Create label for gender
         */
        healthercareLabel = new JLabel("Healthcare-related exposure:");
        healthercareLabel.setBounds(10,110,200,25);
        panel.add(healthercareLabel);
        /**
         * Create radio button for Healthcare
         */
        healthcareY = new JRadioButton("Yes");
        healthcareN = new JRadioButton("No");
        
        /**
         * Set the size and position of buttons
         */
        healthcareY.setBounds(200, 110, 80, 25);
        healthcareN.setBounds(280, 110, 80, 25);
        
        /**
         * Group the buttons, so that only one in the group can be selected
         */
        bgHealthcare = new ButtonGroup();
        bgHealthcare.add(healthcareY);
        bgHealthcare.add(healthcareN);
        
        /**
         * Add button to the panel
         */
        panel.add(healthcareY);
        panel.add(healthcareN);
        
        
       //Days since symptom onset
        /**
         * Create new label
         */
        symptomLabel = new JLabel("Days since symptom onset:");
        /**
         * Set the size and position of the label
         */
        symptomLabel.setBounds(10,140,200,25);
        panel.add(symptomLabel);
        
        /**
         * Create the text area user's answer
         */
        symptomText = new JTextField(20);
        symptomText.setBounds(200,140,40,25);
        panel.add(symptomText);
        
      //Days since tested positive
        /**
         * Create new label
         */
        testLabel = new JLabel("Days since tested positive:");
        /**
         * Set the size and position of the label
         */
        testLabel.setBounds(10,170,200,25);
        panel.add(testLabel);
        
        /**
         * Create the text area user's answer
         */
        testText = new JTextField(20);
        testText.setBounds(200,170,40,25);
        panel.add(testText);
        

        //Create button
        calculateButton = new JButton("Calculate");
        calculateButton.setBounds(130, 220, 100, 25);
        calculateButton.addActionListener(this);
        panel.add(calculateButton);
    }
    
    
    /**
	 *This method will go through the textFields and radio buttons in PanelCompoents and collect the
	 *informations user puts in. Then return an array of integers as inputs for classifier.
	 * @return int[]:This array contains 6 elements. 
	 * int[0] = age
	 * int[1] = Gender, 1 for male and 0 for female.
	 * int[2] = History of chronic disease, 1 for Yea and 0 for No.
	 * int[3] = Healthcare-related exposure, 1 for Yes and 0 for No.
	 * int[4] = Days since symptom onset.
	 * int[5] = Days since tested positive.
	 */
    public int[] collect() {
		
    	String ageString = new String();
		String daysSymptonString = new String();
		String daysPositiveString = new String();
		int Gender = 1;
		int chronicDisease = 1;
		int healthCare = 1;
		
		/**
		 * get user's age
		 */
		ageString = ageText.getText();
		int ageInt = Integer.parseInt(ageString);
		/**
		 * get days since symptom onset
		 */
		daysSymptonString = symptomText.getText();
		int daysSympton = Integer.parseInt(daysSymptonString);
		/**
		 * get days since tested positive
		 */
		daysPositiveString = testText.getText();
		int daysPositive = Integer.parseInt(daysPositiveString);
		
		/**
		 * get user's gender information
		 */
		if(rMale.isSelected()) {
			Gender = 1;
		}
		else if (rFemale.isSelected()) {
			Gender = 0;
		}
		
		/**
		 * get user's history of chronic disease information
		 */
		if(rChronicDiseaseY.isSelected()) {
			chronicDisease = 1;
		}
		else if (rChronicDiseaseN.isSelected()) {
			chronicDisease = 0;
		}
		
		/**
		 * get user's health-care exposure information
		 */
		if( healthcareY.isSelected()) {
			healthCare = 1;
		}
		else if ( healthcareN.isSelected()) {
			healthCare = 0;
		}
    	
		/**
		 * add 6 elements to the array
		 */
		userInfo[0] = ageInt;
		userInfo[1] = Gender;
		userInfo[2] = chronicDisease;
		userInfo[3] = healthCare;
		userInfo[4] = daysSympton;
		userInfo[5] = daysPositive;
		
		//System.out.println(Arrays.toString(userInfo));
		return userInfo;
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		ClickResponse resp = new ClickResponse();
		if(e.getSource() == calculateButton) {
		
		//resp.response(this.collect());	 
		System.out.println(Arrays.toString(this.collect()));
		
		}
	}

}