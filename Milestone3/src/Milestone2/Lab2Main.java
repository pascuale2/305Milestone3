
package Milestone2;

import java.io.FileNotFoundException;

/**
 * This program is the submission for Lab 1. The purpose of the program is to
 * load in a CSV file included in the Lab1 Packages and use various
 * methods to output relevant statistics about the contents of that
 * file
 * 
 * EDIT: the constructor has been modified to fit into the consolidated main
 *  class
 * 
 * @author Korey Sniezek
 * @version 2.0
 * @since Lab2, 26 January 2021
 */

public class Lab2Main {

    /**
     * main -- This is called to run the application.
     *
     * EDIT: The constructor of this class has been modified so that it can be
     * called from the consolidated main file.
     * 
     * @author Korey Sniezek
     * @version 2.0
     * @throws FileNotFoundException
     *
     */
    
    
    public Lab2Main() throws FileNotFoundException {
        
        //property handler prompts for file input
        
        PropertyHandler pHandler = new PropertyHandler ();
        pHandler.loadPropertyFromFile();
        
        // error check and return, added for MS1 to work with the menu better
        if(pHandler.getAllProperties().isEmpty()){
            return;
        }
        //print stats after load
        System.out.println("Descriptive statistics of all property assesments:");
        pHandler.printDescriptiveStats();
        
        // prompt user and print account details
        Property target = pHandler.promptUserFindAccount();
        if(target != null){
            target.printAccountDetails();
        }
        else{
            System.out.println("Not found");
        }
        
        // prompt user for neighbourhood and print stats
        Neighbourhood targetNeighbourhood;
        targetNeighbourhood = pHandler.promptAndGetNeighbourhood();
        
        if(targetNeighbourhood != null){
            System.out.format("Statistics(neighbourhood = %s)\n", targetNeighbourhood.getName());
            targetNeighbourhood.printDescriptiveStats();
        }
        else{
            System.out.println("Not found");
        }

    }
}
