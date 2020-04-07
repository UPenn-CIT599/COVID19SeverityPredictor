import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField; 
public class PanelComponents {
    
    public static void main(String[] args) {    
        PanelComponents panel = new PanelComponents();
        panel.placeComponents();
    }
    
    public PanelComponents() {
    	
    }
    public void placeComponents() {
    	
    	// Create JFrame
        JFrame frame = new JFrame("COVID19 morality predictor");
        // Setting the width and height of frame
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* 
         * Create a panel
         */
        JPanel panel = new JPanel();    
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
        JLabel ageLabel = new JLabel("Age (years):");
        /* 
         * Set the size and position of label
         */
        ageLabel.setBounds(10,20,80,25);
        panel.add(ageLabel);
        
        /* 
         * Set text area for user's input
         */
        JTextField ageText = new JTextField(20);
        ageText.setBounds(200,20,40,25);
        panel.add(ageText);
        
        
        //Gender
        /**
         * Create label for gender
         */
        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(10,50,80,25);
        panel.add(genderLabel);
        
        /**
         * Create radio button for "Male" and "Female"
         */
        JRadioButton rMale = new JRadioButton("Male");
        JRadioButton rFemale = new JRadioButton("Female");
        
        /**
         * Set the size and position of buttons
         */
        rMale.setBounds(200, 50, 80, 25);
        rFemale.setBounds(280, 50, 80, 25);
        
        /**
         * Group the buttons, so that only one in the group can be selected
         */
        ButtonGroup bgGender = new ButtonGroup();
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
        JLabel chronicLabel = new JLabel("History of chronic disease:");
        chronicLabel.setBounds(10,80,200,25);
        panel.add(chronicLabel);
        /**
         * Create radio button for ChronicDisease
         */
        JRadioButton rChronicDiseaseY = new JRadioButton("Yes");
        JRadioButton rChronicDiseaseN = new JRadioButton("No");
        
        /**
         * Set the size and position of buttons
         */
        rChronicDiseaseY.setBounds(200, 80, 80, 25);
        rChronicDiseaseN.setBounds(280, 80, 80, 25);
        
        /**
         * Group the buttons, so that only one in the group can be selected
         */
        ButtonGroup bgChronic = new ButtonGroup();
        bgChronic.add(rMale);
        bgChronic.add(rFemale);
        
        /**
         * Add button to the panel
         */
        panel.add(rChronicDiseaseY);
        panel.add(rChronicDiseaseN);
        

      //Healthcare-related exposure
        /**
         * Create label for gender
         */
        JLabel healthercareLabel = new JLabel("Healthcare-related exposure:");
        healthercareLabel.setBounds(10,110,200,25);
        panel.add(healthercareLabel);
        /**
         * Create radio button for Healthcare
         */
        JRadioButton healthcareY = new JRadioButton("Yes");
        JRadioButton healthcareN = new JRadioButton("No");
        
        /**
         * Set the size and position of buttons
         */
        healthcareY.setBounds(200, 110, 80, 25);
        healthcareN.setBounds(280, 110, 80, 25);
        
        /**
         * Group the buttons, so that only one in the group can be selected
         */
        ButtonGroup bgHealthcare = new ButtonGroup();
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
        JLabel symptomLabel = new JLabel("Days since symptom onset:");
        /**
         * Set the size and position of the label
         */
        symptomLabel.setBounds(10,140,200,25);
        panel.add(symptomLabel);
        
        /**
         * Create the text area user's answer
         */
        JTextField symptomText = new JTextField(20);
        symptomText.setBounds(200,140,40,25);
        panel.add(symptomText);
        
      //Days since tested positive
        /**
         * Create new label
         */
        JLabel testLabel = new JLabel("Days since tested positive:");
        /**
         * Set the size and position of the label
         */
        testLabel.setBounds(10,170,200,25);
        panel.add(testLabel);
        
        /**
         * Create the text area user's answer
         */
        JTextField testText = new JTextField(20);
        testText.setBounds(200,170,40,25);
        panel.add(testText);
        

        //Create button
        JButton calculateButton = new JButton("Calculate");
        calculateButton.setBounds(130, 220, 100, 25);
        panel.add(calculateButton);
    }

}