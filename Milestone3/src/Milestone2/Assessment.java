/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Milestone2;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.collections.ObservableList;

/**
 * The purpose of this class is to inherit methods from the PropertyHandler
 *  class. In its construction, it takes a string and filters out all properties
 *  that don't match the Assessment class field
 * @author Korey
 * @version 1.0
 * @since Lab3
 */
public class Assessment extends PropertyHandler {
    private String assessmentClass;
    
    
    /**
     * This constructor takes its parent and a string and then compares it to the
     *  total property list. 
     *      Version 2.0 updated allProperties to be an observableList
     * @author Korey Sniezek
     * @version 2.0
     * @param parent
     * @param targetAssess
     * @since Lab3
     * @throws FileNotFoundException
     */
    public Assessment(PropertyHandler parent, String targetAssess) throws FileNotFoundException{
        
        ObservableList <Property> allProperties = parent.getAllProperties();
        assessmentClass = targetAssess;
        for(int i = 0; i < allProperties.size(); i++){
            ArrayList <String> classes = allProperties.get(i).getClasses();
            for(int j = 0; j < classes.size(); j++){
                if(classes.get(j).equalsIgnoreCase(targetAssess)){
                    properties.add(allProperties.get(i));
                    break;
                }
            }
        }
        

    }
    
    /**
     * getAssessmentClass -- returns the assessment class
     * @author Korey Sniezek
     * @version 1.0
     * @since Lab3
     * @return Assessment Class string
     */
    public String getAssessmentClass(){
        return assessmentClass;
    }
}
