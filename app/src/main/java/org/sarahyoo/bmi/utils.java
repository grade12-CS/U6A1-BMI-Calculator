package org.sarahyoo.bmi;

public class utils {
    
    public static double bmi_matric(double height_m, double weight_kg) {
        return weight_kg / (height_m * height_m);
    }

    public static double bmi_imperial(double height_inch, double weight_ibs) {
        return weight_ibs / (height_inch * height_inch) * 703;
    }    

    public static double to_inch(double cm) {
        return cm / 2.54;
    }

    public static double to_cm(double inch) {
        return inch * 2.54;
    }

    public static double to_kg(double ibs) {
        return ibs / 2.205;
    }
    
    public static double to_ibs(double kg) {
        return kg * 2.205;
    }
}
