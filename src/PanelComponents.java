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
    private JLabel smokerLabel;
    private JRadioButton smokerY;
    private JRadioButton smokerN;
    private ButtonGroup bgSmoker;
    private JLabel respiratoryRateLabel;
    private JRadioButton respiratoryRateY;
    private JRadioButton respiratoryRateN;
    private ButtonGroup bgRespiratoryRate;
    
    
    private JLabel temperatureLabel;
    private JRadioButton temperatureY;
    private JRadioButton temperatureN;
    private ButtonGroup bgTemperature;
    private JLabel xrayLabel;
    private JRadioButton xrayY;
    private JRadioButton xrayN;
    private ButtonGroup bgXray;
    private JLabel whiteCellLabel;
    private JTextField whiteCellText;
    private JLabel lymphocyteLabel;
    private JTextField lymphocyteText;
    private JLabel plateletsLabel;
    private JTextField plateletsText;
    private JLabel albuminLabel;
    private JTextField albuminText;
    private JLabel lactateLabel;
    private JTextField lactateText;
    private JLabel troponinLabel;
    private JTextField troponinText;
    private JLabel dDimerLabel;
    private JTextField dDimerText;
    private JLabel ferritinLabel;
    private JTextField ferritinText;
    private JLabel IL6Label;
    private JTextField IL6Text;
    private JLabel  procalcitoninLabel;
    private JTextField  procalcitoninText;
    
    private JButton calculateButton;
    double[] userInfo = new double[17];
    
    /**
     * This method create a JFrame and a JPanel. Then add components like label, text area, button, radio Button to the panel. 
     */
    public void placeComponents() {
    	
    	// Create JFrame
        frame = new JFrame("COVID19 morality predictor");
        // Setting the width and height of frame
        frame.setSize(450, 700);
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
        ageText.setBounds(240,20,40,25);
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
        rMale.setBounds(240, 50, 80, 25);
        rFemale.setBounds(320, 50, 80, 25);
        
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
        rChronicDiseaseY.setBounds(240, 80, 80, 25);
        rChronicDiseaseN.setBounds(320, 80, 80, 25);
        
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
        smokerLabel = new JLabel("Current smoker:");
        smokerLabel.setBounds(10,110,200,25);
        panel.add(smokerLabel);
        smokerY = new JRadioButton("Yes");
        smokerN = new JRadioButton("No");
        smokerY.setBounds(240, 110, 80, 25);
        smokerN.setBounds(320, 110, 80, 25);
        bgSmoker = new ButtonGroup();
        bgSmoker.add(smokerY);
        bgSmoker.add(smokerN);
        panel.add(smokerY);
        panel.add(smokerN);
        
        respiratoryRateLabel = new JLabel("Respiratory Rate > 24:");
        respiratoryRateLabel.setBounds(10,140,200,25);
        panel.add(respiratoryRateLabel);
        respiratoryRateY = new JRadioButton("Yes");
        respiratoryRateN = new JRadioButton("No");
        respiratoryRateY.setBounds(240, 140, 80, 25);
        respiratoryRateN.setBounds(320, 140, 80, 25);
        bgRespiratoryRate = new ButtonGroup();
        bgRespiratoryRate.add(respiratoryRateY);
        bgRespiratoryRate.add(respiratoryRateN);
        panel.add(respiratoryRateY);
        panel.add(respiratoryRateN);
        
        temperatureLabel = new JLabel("Temperature > 37.3 C:");
        temperatureLabel.setBounds(10,170,200,25);
        panel.add(temperatureLabel);
        temperatureY = new JRadioButton("Yes");
        temperatureN = new JRadioButton("No");
        temperatureY.setBounds(240, 170, 80, 25);
        temperatureN.setBounds(320, 170, 80, 25);
        bgTemperature = new ButtonGroup();
        bgTemperature.add(temperatureY);
        bgTemperature.add(temperatureN);
        panel.add(temperatureY);
        panel.add(temperatureN);
        
        xrayLabel = new JLabel("Ground glass opacity on chest x-ray:");
        xrayLabel.setBounds(10,200,220,25);
        panel.add(xrayLabel);
        xrayY = new JRadioButton("Yes");
        xrayN = new JRadioButton("No");
        xrayY.setBounds(240, 200, 80, 25);
        xrayN.setBounds(320, 200, 80, 25);
        bgXray = new ButtonGroup();
        bgXray.add(xrayY);
        bgXray.add(xrayN);
        panel.add(xrayY);
        panel.add(xrayN);
        
        whiteCellLabel = new JLabel("White blood cell count:");
        whiteCellLabel.setBounds(10,230,200,25);
        panel.add(whiteCellLabel);
        whiteCellText = new JTextField(20);
        whiteCellText.setBounds(240,230,60,25);
        panel.add(whiteCellText);
        
        lymphocyteLabel = new JLabel("Lymphocyte count:");
        lymphocyteLabel.setBounds(10,260,200,25);
        panel.add(lymphocyteLabel);
        lymphocyteText = new JTextField(20);
        lymphocyteText.setBounds(240,260,60,25);
        panel.add(lymphocyteText);
        
        plateletsLabel = new JLabel("Platelets:");
        plateletsLabel.setBounds(10,290,200,25);
        panel.add(plateletsLabel);
        plateletsText = new JTextField(20);
        plateletsText.setBounds(240,290,60,25);
        panel.add(plateletsText);
        
        albuminLabel = new JLabel("Albumin:");
        albuminLabel.setBounds(10,320,200,25);
        panel.add(albuminLabel);
        albuminText = new JTextField(20);
        albuminText.setBounds(240,320,60,25);
        panel.add(albuminText);
        
        lactateLabel = new JLabel("Lactate dehydrogenase:");
        lactateLabel.setBounds(10,350,200,25);
        panel.add(lactateLabel);
        lactateText = new JTextField(20);
        lactateText.setBounds(240,350,60,25);
        panel.add(lactateText);
        
        troponinLabel = new JLabel("Troponin I:");
        troponinLabel.setBounds(10,380,200,25);
        panel.add(troponinLabel);
        troponinText = new JTextField(20);
        troponinText.setBounds(240,380,60,25);
        panel.add(troponinText);
        
        dDimerLabel = new JLabel("D-dimer:");
        dDimerLabel.setBounds(10,410,200,25);
        panel.add(dDimerLabel);
        dDimerText = new JTextField(20);
        dDimerText.setBounds(240,410,60,25);
        panel.add(dDimerText);
        
        ferritinLabel = new JLabel("Ferritin:");
        ferritinLabel.setBounds(10,440,200,25);
        panel.add(ferritinLabel);
        ferritinText = new JTextField(20);
        ferritinText.setBounds(240,440,60,25);
        panel.add(ferritinText);
        
        IL6Label = new JLabel("IL-6:");
        IL6Label.setBounds(10,470,200,25);
        panel.add(IL6Label);
        IL6Text = new JTextField(20);
        IL6Text.setBounds(240,470,60,25);
        panel.add(IL6Text);
        
        procalcitoninLabel = new JLabel("Procalcitonin:");
        procalcitoninLabel.setBounds(10,500,200,25);
        panel.add(procalcitoninLabel);
        procalcitoninText = new JTextField(20);
        procalcitoninText.setBounds(240,500,60,25);
        panel.add(procalcitoninText);
        

        //Create button
        calculateButton = new JButton("Calculate");
        calculateButton.setBounds(150, 560, 100, 25);
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
    public double[] collect() {
		
    	String ageString = new String();
		int Gender = 1;
		int chronicDisease = 1;
		int smoker = 1;
		int respiratoryRate = 1;
		int temperature = 1;
		int xray = 1;
		String  whiteCellString = new String();
		String lymphocyteString = new String();
		String plateletsString = new String();
		String albuminString = new String();
		String lactateString = new String();
		
		String troponinString = new String();
		String dDimerString = new String();
		String ferritinString = new String();
		String IL6String = new String();
		String procalcitoninString = new String();
		/**
		 * get user's age
		 */
		ageString = ageText.getText();
		double ageInt = Double.parseDouble(ageString);
		
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
		if( smokerY.isSelected()) {
			smoker = 1;
		}
		else if ( smokerN.isSelected()) {
			smoker = 0;
		}
		
		if( respiratoryRateY.isSelected()) {
			respiratoryRate = 1;
		}
		else if ( respiratoryRateN.isSelected()) {
			respiratoryRate = 0;
		}
		
		if( temperatureY.isSelected()) {
			temperature = 1;
		}
		else if ( temperatureN.isSelected()) {
			temperature = 0;
		}
		
		if( xrayY.isSelected()) {
			xray = 1;
		}
		else if ( xrayN.isSelected()) {
			xray = 0;
		}
		
		
		/**
		 * get days since symptom onset
		 */
		whiteCellString = whiteCellText.getText();
		double whiteCell = Double.parseDouble(whiteCellString);
		
		lymphocyteString = lymphocyteText.getText();
		double lymphocytePositive = Double.parseDouble(lymphocyteString);
		
		plateletsString = plateletsText.getText();
		double platelets = Double.parseDouble(plateletsString);
		
		albuminString = albuminText.getText();
		double albumin = Double.parseDouble(albuminString);
		
		lactateString = lactateText.getText();
		double lactate = Double.parseDouble(lactateString);
		
		troponinString = troponinText.getText();
		double troponin = Double.parseDouble(troponinString);
		
		dDimerString = dDimerText.getText();
		double dDimer = Double.parseDouble(dDimerString);
		
		ferritinString = ferritinText.getText();
		double ferritin = Double.parseDouble(ferritinString);
		
		IL6String = IL6Text.getText();
		double IL6 = Double.parseDouble(IL6String);
		
		procalcitoninString = procalcitoninText.getText();
		double procalcitonin = Double.parseDouble(procalcitoninString);
		
		
		
		
		/**
		 * add 6 elements to the array
		 */
		userInfo[0] = ageInt;
		userInfo[1] = Double.valueOf(Gender);
		userInfo[2] = Double.valueOf(chronicDisease);
		userInfo[3] = Double.valueOf(smoker);
		userInfo[4] = Double.valueOf(respiratoryRate);
		userInfo[5] = Double.valueOf(temperature);
		userInfo[6] = Double.valueOf(xray);
		userInfo[7] = whiteCell;
		userInfo[8] = lymphocytePositive;
		userInfo[9] = platelets;
		userInfo[10] = albumin ;
		userInfo[11] = lactate;
		userInfo[12] = troponin;
		userInfo[13] = dDimer;
		userInfo[14] = ferritin;
		userInfo[15] = IL6;
		userInfo[16] = procalcitonin;
		
		//System.out.println(Arrays.toString(userInfo));
		return userInfo;
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		ClickResponse resp = new ClickResponse();
		ResultPanel resultPanel = new ResultPanel();
		double score; 
		if(e.getSource() == calculateButton) {
	        
	        score = resp.response(this.collect());
	        System.out.println(score);
	        resultPanel.placeComponents(score);
	        
		
		}
	}

}