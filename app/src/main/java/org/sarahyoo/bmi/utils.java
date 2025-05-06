package org.sarahyoo.bmi;

/**
 * math utils for converting units and calculation bmi
 */
public class utils {

    /**
     * calculates bmi in metric units
     * @param height_cm height in centimeters
     * @param weight_kg weight in kilograms
     * @return bmi in metric units
     */
    public static double bmi_metric(double height_cm, double weight_kg) {
        return weight_kg / (height_cm * height_cm);
    }

    /**
     * calculate bmi in imperial units
     * @param height_inch height in inches
     * @param weight_ibs weight in pounds
     * @return bmi in imperial units
     */
    public static double bmi_imperial(double height_inch, double weight_ibs) {
        return weight_ibs / (height_inch * height_inch) * 703;
    }    

    /**
     * converts centimeters into inches 
     * @param cm length in centimeters
     * @return length in inches
     */
    public static double to_inch(double cm) {
        return cm / 2.54;
    }

    /**
     * converts inches into centimeters
     * @param inch length in inches
     * @return length in centimeters
     */
    public static double to_cm(double inch) {
        return inch * 2.54;
    }

    /**
     * converts pounds into kilograms
     * @param ibs weight in pounds
     * @return weight in kilograms
     */
    public static double to_kg(double ibs) {
        return ibs / 2.205;
    }
    
    /**
     * converts kilograms into pounds
     * @param kg weight in kilograms
     * @return weight in pounds
     */
    public static double to_ibs(double kg) {
        return kg * 2.205;
    }
}
