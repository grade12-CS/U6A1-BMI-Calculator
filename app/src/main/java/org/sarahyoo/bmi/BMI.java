package org.sarahyoo.bmi;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * a JPanel that constructs a BMI calculator
 */
public class BMI extends JPanel {
    private final JRadioButton rbtn_matric, rbtn_imperial;
    private final JTextField text_height, text_weight;
    private final JLabel label_height, label_weight, label_bmi, label_bmi_result;
    private final JButton btn_bmi, btn_exit;
    private double height = 0.0, weight = 0.0, bmi = 0.0;
    private boolean is_metric = true;
    
    private final DecimalFormat df = new DecimalFormat("0.00");
    private final JFrame frame; //recieves main frame to disclose it when exit button clicked
    
    /**
     * initializes Jcomponents, adding layouts and action listeners
     * @param frame main frame the panel will be displayed
     */
    public BMI(JFrame frame) {
        this.frame = frame;
        setSize(getSize());
        setLayout(new GridBagLayout());
        setBorder(new EmptyBorder(10, 10, 10, 10)); //add padding to edges
        rbtn_matric = new JRadioButton("Matric", is_metric);
        rbtn_imperial = new JRadioButton("Imperial", !is_metric);
        text_height = new JTextField();
        text_weight = new JTextField();
        label_height = new JLabel("Height " + (is_metric ? "(m)" : "(ft)"));
        label_weight = new JLabel("Weight " + (is_metric ? "(kg)" : "(ibs)"));
        label_bmi = new JLabel("BMI: ");
        label_bmi_result = new JLabel();
        btn_bmi = new JButton("BMI");
        btn_exit = new JButton("Exit");
        add_components();
        add_actionlisteners();
    }

    /**
     * calculates bmi for either metric and imperial mode
     * @return bmi
     */
    public double calculate_bmi() {
        return is_metric ? utils.bmi_metric(height, weight) : utils.bmi_imperial(height, weight);
    }

    /**
     * converts units of labels and changes height, and weight into appropriate value depending on the units
     * also re-calculates bmi to reflect the changes
     */
    private void update_units() {
        label_height.setText("Height " + (is_metric ? "(cm)" : "(inch)"));
        label_weight.setText("Weight " + (is_metric ? "(kg)" : "(ibs)"));
        height = is_metric ? utils.to_cm(height) : utils.to_inch(height);
        weight = is_metric ? utils.to_kg(weight) : utils.to_ibs(weight);
        text_height.setText(df.format(height));
        text_weight.setText(df.format(weight));
        update_bmi_result_label();
    }
    
    /**
     * this is called on units change
     * it calculates bmi for both metric and imperial units and update the label
     */
    private void update_bmi_result_label() {
        String result;
        if (height <= 0 || weight <= 0) {
            result = "invalid input values";
        } else {
            bmi = calculate_bmi() * (is_metric ? 10000 : 1);
            System.out.println(bmi);
            result = df.format(bmi);
        }
        label_bmi_result.setText(result);
    }
    
    /**
     * defines actions for all buttons in the panel on their click events
     */
    private void add_actionlisteners() {
        rbtn_matric.addActionListener(e -> {
            is_metric = true;
            rbtn_imperial.setSelected(false);
            update_units();
        });
        rbtn_imperial.addActionListener(e -> {
            is_metric = false;
            rbtn_matric.setSelected(false);
            update_units();
        });
        text_height.getDocument().addDocumentListener((SimpleDocumentListener) (DocumentEvent e) -> {
            String str = text_height.getText();
            if (str.isBlank()) return; 
            height = Double.parseDouble(str);
        });
        text_weight.getDocument().addDocumentListener((SimpleDocumentListener) (DocumentEvent e) -> {
            String str = text_weight.getText();
            if (str.isBlank()) return; 
            weight = Double.parseDouble(str);
        });
        btn_bmi.addActionListener(e -> {
            String height_str = text_height.getText();
            String weight_str = text_weight.getText();
            if (height_str.isEmpty() || weight_str.isEmpty()) {
                label_bmi_result.setText("inputs are empty");
                return;
            }
            height = Double.parseDouble(height_str);
            weight = Double.parseDouble(weight_str);
            update_bmi_result_label();
        });
        btn_exit.addActionListener(e -> {
            //closes the program
           frame.dispose(); 
        });
    }
    
    /**
     * adds Jcomponents to this JPanel and defines layout
     */
    private void add_components() {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        
        c.gridx = 0;
        c.gridy = 0;
        add(rbtn_matric, c);
        
        c.gridx = 1;
        add(rbtn_imperial, c);
        
        c.gridx = 0;
        c.gridy = 1;
        add(label_height, c);
        
        c.gridx = 1;
        add(text_height, c);
        
        c.gridx = 0;
        c.gridy = 2;
        add(label_weight, c);
        
        c.gridx = 1;
        add(text_weight, c);

        c.gridx = 0;
        c.gridy = 3;
        add(label_bmi, c);

        c.gridx = 1;
        add(label_bmi_result, c);

        c.gridx = 0;
        c.gridy = 4;
        
        add(btn_bmi, c);

        c.gridx = 1;
        add(btn_exit, c);
    }

    /**
     * constructs a simple interface for a document listner that allows recieving all document event in one method
     * it is to remove the cubersome that original document listener has
     */
    @FunctionalInterface
    public interface SimpleDocumentListener extends DocumentListener {
        void update(DocumentEvent e);

        @Override
        default void insertUpdate(DocumentEvent e) {
            update(e);
        }
        @Override
        default void removeUpdate(DocumentEvent e) {
            update(e);
        }
        @Override
        default void changedUpdate(DocumentEvent e) {
            update(e);
        }
    }
}
