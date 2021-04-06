package MS2;

import java.io.IOException;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * This class contains all the Getter methods for the program. It contains 
 * functions to read the csv file, and get all the values from the csv file
 * either unfiltered, or based on acct number, neighbourhood, or assessment 
 * class.
 * @author Jason
 */
public class Getter {
    /**
     * readFile - returns the list of data created from the csv file read within
     * the function.
     * 
     * 
     * @return - the list of PropertyAssessment objects created from the csv file
     * that is read.
     */
    public static List<PropertyAssessment> readFile(String file) {
        List<PropertyAssessment> data = new ArrayList<>();
        
        try {
            Scanner s = new Scanner(Paths.get(file));
            s.nextLine();
            while (s.hasNextLine()) {
                String input = s.nextLine();
                String[] cells = input.split(",", 18);
                PropertyAssessment p = new PropertyAssessment(cells);
                data.add(p);
            }
        } catch(IOException exception){
            System.out.println("Error: file not found");
        }
        return data;
    }
    /**
     * getAllValues - returns a list of assessed values from all the property
     * assessments in the csv file that was read.
     * 
     * @param data - list of PropertyAssessment objects.
     * @return - list of assessed values extracted from the list of 
     * PropertyAssessment objects.
     */
    public static List<Double> getAllValues(List<PropertyAssessment> data){
        List<Double> values = new ArrayList<>();
                
        for (int i = 0; i < data.size(); i++){
            values.add((data.get(i)).getValue());
        }
        return values;
    }
    /**
     * getAssessClass - this function returns a set containing strings of all
     * the assessment classes that a PropertyAssessment object may have. To 
     * be used for search parameters in the UI.
     * 
     * @return - set containing strings of all assessment classes for a Property
     * Assessment object
     */
    public static Set<String> getAssessClass() {
        Set<String> assessClasses = new HashSet<>();
        List<String> ls;
        
        List<PropertyAssessment> data = Getter.readFile("Property_Assessment_Data.csv");
        for (int i = 0; i < data.size(); i++) {
            ls = data.get(i).getAssessmentClass();
            assessClasses.addAll(ls);
        }
        return assessClasses;
    }
}
