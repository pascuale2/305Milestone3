package view;

import MS2.Getter;
import MS2.PropertyAssessment;
import MS2.Statistics;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class contains the functions to get all observables to be displayed in 
 * the User Interface.
 * 
 * @author Jason
 */
public class Observable {
    /**
     * getObservableLs() - method used to create an ObservableList of 
     * PropertyAssessment objects from the csv file, and returns this 
     * ObservableList to allow the UI to display the PropertyAssessment info
     * for each object.
     * 
     * @return 
     */
    public static ObservableList<PropertyAssessment> getObservableLs() {
       ObservableList<PropertyAssessment> pAssessment = FXCollections.observableArrayList();
       List<PropertyAssessment> data = Getter.readFile("Property_Assessment_Data.csv");

       for (int i = 0; i < data.size(); i++) {
           pAssessment.add(data.get(i));
       }
       return pAssessment;
    }
    /**
     * editObservable - this function takes an ObservableList of PropertyAssessment
     * objects, creates a new ObservableList using the Set searchValues to
     * filter results from the original list, and returns this list. To be
     * used to display search results in the UI.
     * 
     * @param searchValues - a set of search parameters from user input in the UI.
     * @param observeLS - the original ObservableList containing the PropertyAssessment
     * objects. To be searched through.
     * @return - new ObservableList containing only PropertyAssessments that 
     * meet the searchValues properties.
     */
    public static ObservableList<PropertyAssessment> editObservable(Set<String> searchValues, ObservableList<PropertyAssessment> observeLS){
        ObservableList<PropertyAssessment> pAssessment = FXCollections.observableArrayList();
        Set<String> paSet = new HashSet<>();
        Set<String> copy = new HashSet<>();
        copy.addAll(searchValues);
        
        for (PropertyAssessment p : observeLS) {
            paSet.add(String.valueOf(p.getAcctNumber()));
            paSet.add(p.getNeighbourhood().getNbrhdName());
            paSet.add(p.getAddress().toString());
            paSet.addAll(p.getAssessmentClass());
            copy.removeAll(paSet);
            if (copy.isEmpty()) {
                pAssessment.add(p);
            }
            paSet.removeAll(paSet);
            copy.addAll(searchValues);
        }
        return pAssessment;
    }
    /**
     * getOLSStats - this function takes an ObservableList of PropertyAssessment
     * objects, creates a List of doubles of all the values associated with the 
     * list and gets the statistics for the values. These statistics are then
     * used to create a string to be displayed in the UI textArea labeled 
     * statistics.
     * 
     * @param observeLS - an ObservableList of PropertyAssessment objects
     * @return - a string to display the statistics in the UI textArea.
     */
    public static String getOLSStats(ObservableList<PropertyAssessment> observeLS){
        List<PropertyAssessment> data = new ArrayList<>();
        
        data.addAll(observeLS);
        List<String> stats = Statistics.getStatistics(Getter.getAllValues(data));
        String text = "Statistics of Assessed Values:\n\n" +
               "Number of properties: " + stats.get(0)
                + "\nMin: $" + stats.get(1) + "\nMax: $"
                + stats.get(2) + "\nRange: $" + stats.get(3)
                + "\nMean: $" + stats.get(4) + "\nMedian: $"
                + stats.get(5) + "\nStandard deviation: $"
                + stats.get(6);
        
        return text;
    }
}
