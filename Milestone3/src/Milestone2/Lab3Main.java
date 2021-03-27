/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Milestone2;

import java.io.FileNotFoundException;

/**
 * This is the main class for Lab 3, its purpose is to load a property handler
 *  and populate it from a file, then prompt for the name of an assessment
 *  class and populate an Assessment class, then print out descriptive
 *  statistics for this class
 * 
 * EDIT: The constructor of this class has been modified so that it can be
 *      called from the consolidated main file.
 * 
 * @author Korey
 * @version 2.0
 * @since Lab3
 */
public class Lab3Main {
    
    
    public Lab3Main() throws FileNotFoundException{
        
        //initialize property handler
        PropertyHandler pHandler = new PropertyHandler();
        pHandler.loadPropertyFromFile();
        
        // error check and return, added for MS1 to work with the menu better
        if(pHandler.getAllProperties().isEmpty()){
            return;
        }
        
        Assessment assess = pHandler.promptAndGetAssessment();
        
        if(assess == null){
            System.out.println("Not found");
        }
        else{
            
            System.out.format("Statistics (assessment class = %s)\n", assess.getAssessmentClass());
        
            assess.printDescriptiveStats();
        }
        
    }
}
