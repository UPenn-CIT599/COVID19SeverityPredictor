import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.jfree.ui.RefineryUtilities;


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
    private JLabel whiteCellNormalRange;
    private JLabel lymphocyteLabel;
    private JTextField lymphocyteText;
    private JLabel lymphocyteRange;
    private JLabel plateletsLabel;
    private JTextField plateletsText;
    private JLabel plateletsRange;
    private JLabel albuminLabel;
    private JTextField albuminText;
    private JLabel albuminRange;
    private JLabel lactateLabel;
    private JTextField lactateText;
    private JLabel lactateRange;
    private JLabel troponinLabel;
    private JTextField troponinText;
    private JLabel troponinRange;
    private JLabel dDimerLabel;
    private JTextField dDimerText;
    private JLabel dDimerRange;
    private JLabel ferritinLabel;
    private JTextField ferritinText;
    private JLabel ferritinRange;
    private JLabel IL6Label;
    private JTextField IL6Text;
    private JLabel IL6Range;
    private JLabel  procalcitoninLabel;
    private JTextField  procalcitoninText;
    private JLabel procalcitoninRange;
    private JLabel referenceRange;
    private JLabel laboratoryTest;

    private JButton calculateButton;
    double[] userInfo = new double[17];

    /**
     * This method create a JFrame and a JPanel. Then add components like label, text area, button, radio Button to the panel. 
     */
    public void placeComponents() {

	// Create JFrame
	frame = new JFrame("COVID19 severity predictor");
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
	ageLabel = new JLabel("Age (years)");
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
	genderLabel = new JLabel("Gender");
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

	/**
	 * History of chronic disease
	 */
	chronicLabel = new JLabel("History of chronic disease");
	chronicLabel.setBounds(10,80,200,25);
	panel.add(chronicLabel);
	rChronicDiseaseY = new JRadioButton("Yes");
	rChronicDiseaseN = new JRadioButton("No");
	rChronicDiseaseY.setBounds(240, 80, 80, 25);
	rChronicDiseaseN.setBounds(320, 80, 80, 25);
	bgChronic = new ButtonGroup();
	bgChronic.add(rChronicDiseaseY);
	bgChronic.add(rChronicDiseaseN);
	panel.add(rChronicDiseaseY);
	panel.add(rChronicDiseaseN);

	/**
	 * Current smoker
	 */
	smokerLabel = new JLabel("Current smoker");
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

	/**
	 * Respiratory Rate > 24
	 */
	respiratoryRateLabel = new JLabel("Respiratory Rate > 24");
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

	/**
	 * Temperature > 37.3 C
	 */
	temperatureLabel = new JLabel("Temperature > 37.3 C");
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

	/**
	 * Ground glass opacity on chest x-ray
	 */
	xrayLabel = new JLabel("Ground glass opacity on chest x-ray");
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
	
	/**
	 * Laboratory test header
	 */
	laboratoryTest = new JLabel("Laboratory tests*");
	laboratoryTest.setBounds(10, 230, 200, 25);
	laboratoryTest.setFont(new Font(null, Font.BOLD, 12));
	panel.add(laboratoryTest);
	
	
	/**
	 * Reference ranges header
	 */
	referenceRange = new JLabel("(Reference range)");
	referenceRange.setBounds(320, 230, 200, 25);
	referenceRange.setFont(new Font(null, Font.BOLD, 12));
	panel.add(referenceRange);

	/**
	 * White blood cell count
	 */
	whiteCellLabel = new JLabel("White blood cell count, x 10 ^ 9 per L");
	whiteCellLabel.setBounds(10,260,220,25);
	panel.add(whiteCellLabel);
	whiteCellText = new JTextField(20);
	whiteCellText.setBounds(240,260,60,25);
	panel.add(whiteCellText);
	whiteCellNormalRange = new JLabel("(4-10)");
	whiteCellNormalRange.setBounds(320, 260, 200, 25);
	panel.add(whiteCellNormalRange);

	/**
	 * Lymphocyte count
	 */
	lymphocyteLabel = new JLabel("Lymphocyte count, x 10 ^ 9 / L)");
	lymphocyteLabel.setBounds(10,290,200,25);
	panel.add(lymphocyteLabel);
	lymphocyteText = new JTextField(20);
	lymphocyteText.setBounds(240,290,60,25);
	panel.add(lymphocyteText);
	lymphocyteRange = new JLabel("(< 1.0)");
	lymphocyteRange.setBounds(320, 290, 200, 25);
	panel.add(lymphocyteRange);

	/**
	 * Platelets
	 */
	plateletsLabel = new JLabel("Platelets, x 10 ^ 9 / L");
	plateletsLabel.setBounds(10,320,200,25);
	panel.add(plateletsLabel);
	plateletsText = new JTextField(20);
	plateletsText.setBounds(240,320,60,25);
	panel.add(plateletsText);
	plateletsRange = new JLabel("(150 - 350)");
	plateletsRange.setBounds(320, 320, 200, 25);
	panel.add(plateletsRange);

	/**
	 * Albumin
	 */
	albuminLabel = new JLabel("Albumin, g/L");
	albuminLabel.setBounds(10,350,200,25);
	panel.add(albuminLabel);
	albuminText = new JTextField(20);
	albuminText.setBounds(240,350,60,25);
	panel.add(albuminText);
	albuminRange = new JLabel("(3.5 - 5)");
	albuminRange.setBounds(320, 350, 200, 25);
	panel.add(albuminRange);

	/**
	 * Lactate dehydrogenase
	 */
	lactateLabel = new JLabel("Lactate dehydrogenase, U/L");
	lactateLabel.setBounds(10,380,200,25);
	panel.add(lactateLabel);
	lactateText = new JTextField(20);
	lactateText.setBounds(240,380,60,25);
	panel.add(lactateText);
	lactateRange = new JLabel("(50 - 150)");
	lactateRange.setBounds(320, 380, 200, 25);
	panel.add(lactateRange);

	/**
	 * Troponin I
	 */
	troponinLabel = new JLabel("Troponin I, pg/mL");
	troponinLabel.setBounds(10,410,200,25);
	panel.add(troponinLabel);
	troponinText = new JTextField(20);
	troponinText.setBounds(240,410,60,25);
	panel.add(troponinText);
	troponinRange = new JLabel("(< 1.0)");
	troponinRange.setBounds(320, 410, 200, 25);
	panel.add(troponinRange);
	


	/**
	 * D-dimer
	 */
	dDimerLabel = new JLabel("D-dimer, ug/mL");
	dDimerLabel.setBounds(10,440,200,25);
	panel.add(dDimerLabel);
	dDimerText = new JTextField(20);
	dDimerText.setBounds(240,440,60,25);
	panel.add(dDimerText);
	dDimerRange = new JLabel("(< 0.5)");
	dDimerRange.setBounds(320, 440, 200, 25);
	panel.add(dDimerRange);

	/**
	 * Ferritin
	 */
	ferritinLabel = new JLabel("Ferritin, ug/mL");
	ferritinLabel.setBounds(10,470,200,25);
	panel.add(ferritinLabel);
	ferritinText = new JTextField(20);
	ferritinText.setBounds(240,470,60,25);
	panel.add(ferritinText);
	ferritinRange = new JLabel("(0.012 - 0.3)");
	ferritinRange.setBounds(320, 470, 200, 25);
	panel.add(ferritinRange);

	/**
	 * IL-6
	 */
	IL6Label = new JLabel("IL-6, pg/mL");
	IL6Label.setBounds(10,500,200,25);
	panel.add(IL6Label);
	IL6Text = new JTextField(20);
	IL6Text.setBounds(240,500,60,25);
	panel.add(IL6Text);
	IL6Range = new JLabel("(< 10)");
	IL6Range.setBounds(320, 500, 200, 25);
	panel.add(IL6Range);
	
	/**
	 * Procalcitonin
	 */
	procalcitoninLabel = new JLabel("Procalcitonin, ng/mL");
	procalcitoninLabel.setBounds(10,530,200,25);
	panel.add(procalcitoninLabel);
	procalcitoninText = new JTextField(20);
	procalcitoninText.setBounds(240,530,60,25);
	panel.add(procalcitoninText);
	procalcitoninRange = new JLabel("(0.1 - 0.49)");
	procalcitoninRange.setBounds(320, 530, 200, 25);
	panel.add(procalcitoninRange);

	/**
	 * Calculate
	 */
	calculateButton = new JButton("Calculate");
	calculateButton.setBounds(190, 590, 100, 25);
	calculateButton.addActionListener(this);
	panel.add(calculateButton);
	
	//Sets minimum frame size to the parameterized dimension
	Dimension d = new Dimension(500, 400);
	frame.setMinimumSize(d);
	//Center frame on screen
	RefineryUtilities.centerFrameOnScreen(frame);
    }

    
    /**
	 *This method will go through the textFields and radio buttons in PanelCompoents and collect the
	 *informations user puts in. Then return an array of integers as inputs for classifier.
	 * @return double[]:This array contains 17 elements. 
	 * double [0] = age
	 * double [1] = Gender, 1 for male and 0 for female.
	 * double [2] = History of chronic disease, 1 for Yea and 0 for No.
	 * double [3] = Current smoker, 1 for Yes and 0 for No.
	 * double [4] = Respiratory Rate > 24, 1 for Yes and 0 for No.
	 * double [5] = Temperature > 37.3 C, 1 for Yes and 0 for No.
	 * double [6] = Ground glass opacity on chest x-ray, 1 for Yes and 0 for No.
	 * double [7] = White blood cell count
	 * double [8] = Lymphocyte count
	 * double [9] = Platelets
	 * double [10] = Albumin
	 * double [11] = Lactate dehydrogenase
	 * double [12] = Troponin I
	 * double [13] = D-dimer
	 * double [14] = Ferritin
	 * double [15] = IL-6
	 * double [16] = Procalcitonin
	 */
    public String[] collect() {
		
    	
    	String[] userInfoString = new String[17];
    	String ageString = new String();

		
		String GenderString = new String();
		String chronicDiseaseString = new String();
		String smokerString = new String();
		String respiratoryRateString = new String();
		String temperatureString = new String();
		String xrayString = new String();
		
		String whiteCellString = new String();
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
		/**
		 * get user's gender information
		 */
		if(rMale.isSelected()) {
			GenderString = "1";
		}
		else if (rFemale.isSelected()) {
			GenderString = "0";
		}
		else {
			GenderString = "empty";
		}
		
		/**
		 * get user's history of chronic disease information
		 */
		if(rChronicDiseaseY.isSelected()) {
			chronicDiseaseString = "1";
		}
		else if (rChronicDiseaseN.isSelected()) {
			chronicDiseaseString = "0";
		}
		else {
			chronicDiseaseString = "empty";
		}
		
		/**
		 * get user's smoking information
		 */
		if( smokerY.isSelected()) {
			smokerString = "1";
		}
		else if ( smokerN.isSelected()) {
			smokerString = "0";
		}
		else {
			smokerString = "empty";
		}
		
		/**
		 * get user's Respiratory Rate information
		 */
		if( respiratoryRateY.isSelected()) {
			respiratoryRateString = "1";
		}
		else if ( respiratoryRateN.isSelected()) {
			respiratoryRateString = "0";
		}
		else {
			respiratoryRateString = "empty";
		}
		
		/**
		 * get user's Temperature information
		 */
		if( temperatureY.isSelected()) {
			temperatureString = "1";
		}
		else if ( temperatureN.isSelected()) {
			temperatureString = "0";
		}
		else {
			temperatureString = "empty";
		}
		
		/**
		 * get user's chest x-ray information
		 */
		if( xrayY.isSelected()) {
			xrayString = "1";
		}
		else if ( xrayN.isSelected()) {
			xrayString = "0";
		}
		else {
			xrayString = "empty";
		}
		
		/**
		 * get user's White blood cell count information
		 */
		whiteCellString = whiteCellText.getText();
		
		/**
		 * get user's Lymphocyte count information
		 */
		lymphocyteString = lymphocyteText.getText();
		
		/**
		 * get user's Platelets information
		 */
		plateletsString = plateletsText.getText();
		
		/**
		 * get user's Albumin information
		 */
		albuminString = albuminText.getText();
		
		/**
		 * get user's Lactate dehydrogenase information
		 */
		lactateString = lactateText.getText();
		
		/**
		 * get user's Troponin I information
		 */
		troponinString = troponinText.getText();
		
		/**
		 * get user's D-dimer information
		 */
		dDimerString = dDimerText.getText();
		
		/**
		 * get user's Ferritin information
		 */
		ferritinString = ferritinText.getText();
		
		/**
		 * get user's IL-6 information
		 */
		IL6String = IL6Text.getText();
		
		/**
		 * get user's Procalcitonin information
		 */
		procalcitoninString = procalcitoninText.getText();
		
		
		
		
		/**
		 * add 17 elements to the array
		 */
		userInfoString[0] = ageString;
		userInfoString[1] = GenderString;
		userInfoString[2] = chronicDiseaseString;
		userInfoString[3] = smokerString;
		userInfoString[4] = respiratoryRateString;
		userInfoString[5] = temperatureString;
		userInfoString[6] = xrayString;
		userInfoString[7] = whiteCellString;
		userInfoString[8] = lymphocyteString;
		userInfoString[9] = plateletsString;
		userInfoString[10] = albuminString;
		userInfoString[11] = lactateString;
		userInfoString[12] = troponinString;
		userInfoString[13] = dDimerString;
		userInfoString[14] = ferritinString;
		userInfoString[15] = IL6String;
		userInfoString[16] = procalcitoninString;
		
		return userInfoString;
    }
    
    /**
     * This method will check if the input is within the given range.
     * @param num
     * @param upper
     * @param lower
     * @return
     */
    public static boolean checkInput(double num, double upper, double lower) {
		
    	if(num >= lower && num <= upper) {
    		return true;
    	}
    	else {
    	return false;
    	}
    }
    
    
    /**This method will check whether the user's input is a number. It will return false if the input String is Blank of contain any punctuation marks other than period.
     * 
     * @param num
     * @param upper
     * @param lower
     * @return 
     */
    public static boolean isNumeric(final String str) {

        // null or empty
        if (str == null || str.length() == 0) {
            return false;
        }

        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c) && !Character.toString(c).equals(".")) {
                return false;
            }
        }

        return true;

    }
    	/**
    	 * This method will perform the following action once the calculation button is clicked.
    	 * 1. Check if all the inputs are validate. If yes, collect user's input. If not, send out corresponding error message.
    	 * 2. Pass user's input into ClickResponse.
    	 * 3. Call ResultPanel.
    	 */
		@Override
		public void actionPerformed(ActionEvent e) {
		
		//ClickResponse resp = new ClickResponse();
		ResultPanel resultPanel = new ResultPanel();
		double score; 
		if(e.getSource() == calculateButton) {
	        
			String[] userinfoString = this.collect();
            double[] userInfo = new double[17];
            
			/**
			 * Check if all the inputs are validate.
			 */
            while(true) {
            if(isNumeric(userinfoString[0]) && isNumeric(userinfoString[1]) && isNumeric(userinfoString[2]) && isNumeric(userinfoString[3]) && isNumeric(userinfoString[4])&& isNumeric(userinfoString[5])
            		&& isNumeric(userinfoString[6]) && isNumeric(userinfoString[7]) && isNumeric(userinfoString[8]) && isNumeric(userinfoString[9]) && isNumeric(userinfoString[10]) && isNumeric(userinfoString[11])
            		&& isNumeric(userinfoString[12]) && isNumeric(userinfoString[13]) && isNumeric(userinfoString[14]) && isNumeric(userinfoString[15]) && isNumeric(userinfoString[16]) 
            		&& checkInput(Double.parseDouble(userinfoString[0]), 200.0, 0.0) && checkInput(Double.parseDouble(userinfoString[7]), 30.0, 0.0) && checkInput(Double.parseDouble(userinfoString[8]), 2.0, 0.0) 
            		&& checkInput(Double.parseDouble(userinfoString[9]), 600.0, 0.0) && checkInput(Double.parseDouble(userinfoString[10]), 10.0, 0.0) && checkInput(Double.parseDouble(userinfoString[11]), 2000.0, 0.0)
            		&& checkInput(Double.parseDouble(userinfoString[12]), 100.0, 0.0) && checkInput(Double.parseDouble(userinfoString[13]), 50.0, 0.0) && checkInput(Double.parseDouble(userinfoString[14]), 3000.0, 0.0)
            		&& checkInput(Double.parseDouble(userinfoString[15]), 20.0, 0.0) && checkInput(Double.parseDouble(userinfoString[16]), 2.0, 0.0)) {
            	
            	
    			userInfo[0] = Double.parseDouble(userinfoString[0]);
    			userInfo[1] = Double.parseDouble(userinfoString[1]);
    			userInfo[2] = Double.parseDouble(userinfoString[2]);
    			userInfo[3] = Double.parseDouble(userinfoString[3]);
    			userInfo[4] = Double.parseDouble(userinfoString[4]);
    			userInfo[5] = Double.parseDouble(userinfoString[5]);
    			userInfo[6] = Double.parseDouble(userinfoString[6]);
    			userInfo[7] = Double.parseDouble(userinfoString[7]);
    			userInfo[8] = Double.parseDouble(userinfoString[8]);
    			userInfo[9] = Double.parseDouble(userinfoString[9]);
    			userInfo[10] = Double.parseDouble(userinfoString[10]) * 10; //Added unit conversion
    			userInfo[11] = Double.parseDouble(userinfoString[11]);
    			userInfo[12] = Double.parseDouble(userinfoString[12]);
    			userInfo[13] = Double.parseDouble(userinfoString[13]);
    			userInfo[14] = Double.parseDouble(userinfoString[14]);
    			userInfo[15] = Double.parseDouble(userinfoString[15]);
    			userInfo[16] = Double.parseDouble(userinfoString[16]);
            	
            	try {
					score = ClickResponse.response(userInfo);
				} catch (Exception e1) {
					score = 0.0;
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	System.out.println(score);
    		    resultPanel.placeComponents(score);
            }
            /**
             * send out corresponding error messages
             */
            else {
			if(isNumeric(userinfoString[0])) {
				if(!checkInput(Double.parseDouble(userinfoString[0]), 200.0, 0.0)) {
					JOptionPane.showMessageDialog(panel,"Input value for \"Age\" is out of range. Please enter again.","Error ",0);
					break;
				}
			}
				
			else {
				JOptionPane.showMessageDialog(panel,"Input value for \"Age\" is not a number. Please enter again.","Error ",0);
				break;
			}
			
			if(!isNumeric(userinfoString[1])) {
				JOptionPane.showMessageDialog(panel,"Input value for \"Gender\" is empty. Please choose one.","Error ",0);
				break;
			}
			
			if(!isNumeric(userinfoString[2])) {
				JOptionPane.showMessageDialog(panel,"Input value for \"History of chronic disease\" is empty. Please choose one.","Error ",0);
				break;
			}
			
			if(!isNumeric(userinfoString[3])) {
				JOptionPane.showMessageDialog(panel,"Input value for \"Current smoker\" is empty. Please choose one.","Error ",0);
				break;
			}
			
			if(!isNumeric(userinfoString[4])) {
				JOptionPane.showMessageDialog(panel,"Input value for \"Respiratory Rate > 24\" is empty. Please choose one.","Error ",0);
				break;
			}
			
			if(!isNumeric(userinfoString[5])) {
				JOptionPane.showMessageDialog(panel,"Input value for \"Temperature > 37.3 C\" is empty. Please choose one.","Error ",0);
				break;
			}
			
			if(!isNumeric(userinfoString[6])) {
				JOptionPane.showMessageDialog(panel,"Input value for \"Ground glass opacity on chest x-ray\" is empty. Please choose one.","Error ",0);
				break;
			}
			
			if(isNumeric(userinfoString[7])) {
				if(!checkInput(Double.parseDouble(userinfoString[7]), 30.0, 0.0)) {
					JOptionPane.showMessageDialog(panel,"Input value for \"White blood cell count\" is out of range. Please enter again.","Error ",0);
					break;
				}
			}
			else {
				JOptionPane.showMessageDialog(panel,"Input value for \"White blood cell count\" is not a number. Please enter again.","Error ",0);
				break;
			}
			
			if(isNumeric(userinfoString[8])) {
				if(!checkInput(Double.parseDouble(userinfoString[8]), 2.0, 0.0)) {
					JOptionPane.showMessageDialog(panel,"Input value for \"Lymphocyte count\" is out of range. Please enter again.","Error ",0);
					break;
				}
			}
			else {
				JOptionPane.showMessageDialog(panel,"Input value for \"Lymphocyte count\" is not a number. Please enter again.","Error ",0);
				break;
			}
			
			if(isNumeric(userinfoString[9])) {
				if(!checkInput(Double.parseDouble(userinfoString[9]), 600.0, 0.0)) {
					JOptionPane.showMessageDialog(panel,"Input value for \"Platelets\" is out of range. Please enter again.","Error ",0);
					break;
				}
			}
			else {
				JOptionPane.showMessageDialog(panel,"Input value for \"Platelets\" is not a number. Please enter again.","Error ",0);
				break;
			}
			
			if(isNumeric(userinfoString[10])) {
				if(!checkInput(Double.parseDouble(userinfoString[10]), 10.0, 0.0)) {
					JOptionPane.showMessageDialog(panel,"Input value for \"Albumin\" is out of range. Please enter again.","Error ",0);
					break;
				}
			}
			else {
				JOptionPane.showMessageDialog(panel,"Input value for \"Albumin\" is not a number. Please enter again.","Error ",0);
				break;
			}
			
			if(isNumeric(userinfoString[11])) {
				if(!checkInput(Double.parseDouble(userinfoString[11]), 2000.0, 0.0)) {
					JOptionPane.showMessageDialog(panel,"Input value for \"Lactate dehydrogenase\" is out of range. Please enter again.","Error ",0);
					break;
				}
			}
			else {
				JOptionPane.showMessageDialog(panel,"Input value for \"Lactate dehydrogenase\" is not a number. Please enter again.","Error ",0);
				break;
			}
			
			if(isNumeric(userinfoString[12])) {
				if(!checkInput(Double.parseDouble(userinfoString[12]), 100.0, 0.0)) {
					JOptionPane.showMessageDialog(panel,"Input value for \"Troponin I\" is out of range. Please enter again.","Error ",0);
					break;
				}
			}
			else {
				JOptionPane.showMessageDialog(panel,"Input value for \"Troponin I\" is not a number. Please enter again.","Error ",0);
				break;
			}
			
			if(isNumeric(userinfoString[13])) {
				if(!checkInput(Double.parseDouble(userinfoString[13]), 50.0, 0.0)) {
					JOptionPane.showMessageDialog(panel,"Input value for \"D-dimer\" is out of range. Please enter again.","Error ",0);
					break;
				}
			}
			else {
				JOptionPane.showMessageDialog(panel,"Input value for \"D-dimer\" is not a number. Please enter again.","Error ",0);
				break;
			}
			
			if(isNumeric(userinfoString[14])) {
				if(!checkInput(Double.parseDouble(userinfoString[14]), 3000.0, 0.0)) {
					JOptionPane.showMessageDialog(panel,"Input value for \"Ferritin\" is out of range. Please enter again.","Error ",0);
					break;
				}
			}
			else {
				JOptionPane.showMessageDialog(panel,"Input value for \"Ferritin\" is not a number. Please enter again.","Error ",0);
				break;
			}
			
			if(isNumeric(userinfoString[15])) {
				if(!checkInput(Double.parseDouble(userinfoString[15]), 20.0, 0.0)) {
					JOptionPane.showMessageDialog(panel,"Input value for \"IL-6\" is out of range. Please enter again.","Error ",0);
					break;
				}
			}
			else {
				JOptionPane.showMessageDialog(panel,"Input value for \"IL-6\" is not a number. Please enter again.","Error ",0);
				break;
			}
			
			if(isNumeric(userinfoString[16])) {
				if(!checkInput(Double.parseDouble(userinfoString[16]), 2.0, 0.0)) {
					JOptionPane.showMessageDialog(panel,"Input value for \"Procalcitonin\" is out of range. Please enter again.","Error ",0);
					break;
				}
			}
			else {
				JOptionPane.showMessageDialog(panel,"Input value for \"Procalcitonin\" is not a number. Please enter again.","Error ",0);
				break;
			}
			
            }
			break;
		}
		}
	}
}
