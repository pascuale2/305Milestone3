/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Milestone2;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

/**
 * This class holds an ArrayList of property objects and implements
 *  methods to search through the list and provide statistics
 *          Version 2 updated so that properties is an ObservableList
 * @author Korey Sniezek (KMS)
 * @version 2.0
 * @since Lab 3
 * 
 */
public class PropertyHandler {
    protected ObservableList <Property> properties = FXCollections.observableArrayList();
    protected Scanner scan;
    /**
     * Default constructor takes no arguments, will prompt the user for
     * A CSV file name and will attempt to load the file as an ArrayList of
     * property objects.
     * 
     * Edit: Since version 2.0, the constructor process has been
     *  moved to a separate function to separate this method from
     *  child constructors
     * 
     * @author Korey Sniezek (KMS)
     * @version 2.0
     * @throws FileNotFoundException
     * @since Lab 3 28 JAN 2021
     * 
     */
    public PropertyHandler() {
        
    }
    
    /**
     * @purpose New constructor added as part of milestone 2 to handle
     *    default initialization without user prompt for the GUI
     *    this constructor is essentially the "loadPropertyFromFile" method
     *    without user input
     * 
     * @author Korey Sniezek
     * @since MS2
     * @version 2.0
     * @param fileName 
     */
    public PropertyHandler(String fileName) {
        String raw;
        
        try{
            scan = new Scanner(new File(fileName));
        }
        catch(Exception e){
            System.out.println("File not found.");
            return;                    
        }
        
        scan.useDelimiter(","); //May be extraneous, check later
        scan.nextLine();
        
        while (scan.hasNext()) {
            
            raw = scan.nextLine();
            
            // Try to create with line, announce bad data and display if fail
            try{
                properties.add(new Property(raw));
            }
            catch(Exception exc){
                System.out.println("Bad line of data:");
                System.out.println(raw);
            }
            
            
        }
        
        scan.close();
    }
    
    /**
     * loadPropertyFromFile -- this method prompts the user for a filename
     *  and loads all values into the property list
     *  This prevents child classes from attempting to load from file
     *  when compared to previous versions
     * @author Korey Sniezek
     * @version 1.0
     * @since MS1
     * @throws FileNotFoundException
     */
    public void loadPropertyFromFile() throws FileNotFoundException {
        String raw;
        
        
        System.out.print("Please Enter a filename:");
        String fileName = userStringInput();
        
        try{
            scan = new Scanner(new File(fileName));
        }
        catch(Exception e){
            System.out.println("File not found.");
            return;                    
        }
        
        scan.useDelimiter(","); //May be extraneous, check later
        scan.nextLine();
        
        while (scan.hasNext()) {
            
            raw = scan.nextLine();
            
            // Try to create with line, announce bad data and display if fail
            try{
                properties.add(new Property(raw));
            }
            catch(Exception exc){
                System.out.println("Bad line of data:");
                System.out.println(raw);
            }
            
            
        }
        
        scan.close();
    }
    /**
     * getNumberOfProperties -- This method returns the number of items in the list of properties
     * 
     *  #2021-01-28 KMS modified to accept an ArrayList of properties
     * 
     *  #2021-01-28 KMS moved into PropertyHandler from Main
     *  
     *  
     * @author Korey Sniezek (KMS)
     * @version 3.0
     * @return the integer number of entries for the data past to the function
     * @since Lab 3 28 January 2021
     * 
     *
     */
    public int getNumberOfProperties (){
        return properties.size();
    }

    /**
     * min -- This method returns the minimum property value of the contained
     *  property list
     *
     * #2021-01-28 KMS modified to accept an ArrayList of properties
     * #2021-01-28 KMS moved to Property handler class, param removed, works
     *  on internal ArrayList
     * 
     * @author Korey Sniezek (KMS)
     * @version 2.0
     * @since Lab1 26 January 2021
     * @return the integer number of entries for the data past to the function
     *
     */

    public double getMin(){

        double temp;
        temp = properties.get(0).getValue();

        for (int i = 1; i < properties.size(); i++) {
            temp = Math.min(temp, properties.get(i).getValue());
        }
        return temp;
    }

    /**
     * getMax -- This method returns the maximum property value of the internal
     *  properties list
     *
     * #2021-01-28 KMS modified to accept an ArrayList of properties
     * #2021-01-28 KMS param removed, uses internal properties ArrayList
     * 
     * @author Korey Sniezek (KMS)
     * @version 2.0
     * @return the integer number of entries for the data past to the function
     * @since Lab2 26 January 2021
     * 
     */

    public double getMax() {

        double temp;
        temp = properties.get(0).getValue();

        for (int i = 1; i < properties.size(); i++) {
            temp = Math.max(temp, properties.get(i).getValue());
        }
        return temp;
    }

    /**
     * getRange -- This method returns the differenece between the maximum property
     *  value and the minimum property value in the internal properties list
     *
     * #2021-01-28 KMS modified to accept an ArrayList of properties
     * #2021-01-28 KMS Moved to Property Handler and modified
     * 
     * @author Korey Sniezek (KMS)
     * @version 2.0
     * @return the integer number of entries for the data past to the function
     * @since Lab2 26 January 2021
     * 
     */

    public double getRange(){
        return (getMax() - getMin());
    }

    /**
     * getMean -- This method returns the average value of properties in the
     *  internal property list
     * 
     * #2021-01-28 KMS modified to accept an ArrayList of properties
     * 
     * #2021-01-28 KMS moved to PropertyHandler, modified to work with internal
     *  properties list
     * 
     * @author Korey Sniezek (KMS)
     * @version 2.0
     * @return the integer number of entries for the data past to the function
     * @since Lab1 14 January 2021
     * 
     *
     */
    public double getMean(){
        double sum;
        sum = getSumList();
        return (sum / properties.size());
    }

    /**
     * getSumList -- This method returns the total sum of all properties in the
     *  internal property list
     *
     * #2021-01-28 KMS modified to accept an ArrayList of properties
     * #2021-01-28 KMS moved to PropertyHandler and modified to work with internal
     *  property list
     * 
     * @author Korey Sniezek
     * @version 2.0
     * @return the integer number of entries for the data past to the function
     * @since Lab2 26 January 2021
     * 
     *
     */

    public double getSumList() {
        double temp;
        temp = 0;
        for (int i = 0; i < properties.size(); i++) {
            temp = temp + properties.get(i).getValue();
        }
        return temp;
    }

    /**
     * getStdev -- This method returns the standard deviation of the values of the
     *  internal property list
     *
     * #2021-01-28 KMS modified to accept an ArrayList of properties 
     * #2021-01-28 KMS Moved to PropertyHandler class, modified to work with
     *  internal property list
     * 
     * @author Korey Sniezek (KMS)
     * @version 2.0
     * @return the integer number of entries for the data past to the function
     * @since Lab2 26 January 2021
     *
     * 
     *
     */

    public double getStDev () {
        double avg;
        double sum;
        double sumDiffMeanSquared;
        sum = getSumList();
        avg = sum / properties.size();
        sumDiffMeanSquared = 0;
        for (int i = 0; i < properties.size(); i++) {
            sumDiffMeanSquared = sumDiffMeanSquared + Math.pow((properties.get(i).getValue() - avg), 2);
        }
        return Math.sqrt(sumDiffMeanSquared / properties.size());
    }
    /**
     * getMedian -- This method returns the median value of the internal property
     *  list
     * 
     * #2021-01-28 KMS modified to accept an ArrayList of properties
     * #2021-01-28 KMS moved to PropertyHandler, modified to work with internal
     *  property list
     * #2021-03-22 KMS Special case found with size == 2, handler added
     * 
     * @author Korey Sniezek (KMS)
     * @version 3.0
     * @return the integer number of entries for the data past to the function
     * @since Lab1 14 January 2021
     * 
     *
     */
    
    public double getMedian() {
        int mid;
        double median;
        mid = Math.floorDiv(properties.size(), 2);
        if(properties.size() == 2){
            mid = 0;
        }
        Collections.sort(properties);
        
        if (properties.size() % 2 == 1) {
            // is odd
            
            return properties.get(mid).getValue();
        }
        else {
            
            median = (properties.get(mid).getValue() + properties.get(mid + 1).getValue())/2;
            return median;
        }
    }
    
    /**
     * findAccount -- This method takes an account number and returns the 
     *  associated Property object from the internal Property list
     *  NOTE: Not a deepCopy
     * 
     * #2021-01-28 KMS Moved to Property Handler and modifed to work with the
     *  internal property list
     * 
     * @author Korey Sniezek
     * @version 2.0
     * @return null if not found, Property object if found
     * @since Lab2 26 January 2021
     * @param accountNumber
     * 
     *
     */
    public Property findAccount(int accountNumber){
        Property targetAccount = null;
        for(int i = 0; i < properties.size(); i++){
            if(accountNumber == properties.get(i).getAccount()){
                targetAccount = properties.get(i);
                break;
            }
        }
        return targetAccount;
    }
    
    /**
     * promptUserFindAccount -- prompts the user for an account number,
     *  gets the number, converts to int, and then calls findAccount
     * @author Korey Sniezek
     * @since MS1
     * @version 1.0
     * @return returns the found property
     */
    
    public Property promptUserFindAccount(){
        
        Property target = null;
        System.out.print("Find a property assessment by account number:");
        try{
            int accountNum = Integer.parseInt(userStringInput());
            target = findAccount(accountNum);
        }
        catch(Exception e){
            System.out.println("Cannot get property by account number");
            return null;
        }
        return target;
        
    }
    /**
     * getStatString -- return a string of all descriptive stats for all properties
     *  loaded, created instead of modifying printDescriptiveStats
     * 
     * @author Korey Sniezek
     * @version 1.0
     * @since MS2
     * 
     */
    public String getStatString(){
        
        //use a number formatter to get properly formatted currency amounts
        NumberFormat numFormat = NumberFormat.getCurrencyInstance(Locale.CANADA);
        
        String stats = "Statistics of Assessed Values:\n\n";
        stats = stats + "Number of properties: " + getNumberOfProperties() + "\n";
        stats = stats + "Min: " + numFormat.format(getMin()) + "\n";
        stats = stats + "Max: " + numFormat.format(getMax()) + "\n";
        stats = stats + "Range: " + numFormat.format(getRange()) + "\n";
        stats = stats + "Mean: " + numFormat.format(getMean())   + "\n";
        stats = stats + "Median " + numFormat.format(getMedian()) + "\n";
        stats = stats + "Standard deviation: " + numFormat.format(getStDev()) + "\n";
        
        
        return stats;
    }
    /**
     * printDescriptiveStats -- print all descriptive stats for all properties
     *  loaded
     * 
     * @author Korey Sniezek
     * @version 2.0
     * @since MS1
     * 
     */
    
    public void printDescriptiveStats() {
        
       
        //use a number formatter to get properly formatted currency amounts
        NumberFormat numFormat = NumberFormat.getCurrencyInstance(Locale.CANADA);
        
        //print n
        System.out.print("n = ");
        System.out.println( getNumberOfProperties());
        
        // print min
        System.out.print("min = ");
        System.out.println(numFormat.format(getMin()));
        
        //print max
        System.out.print("max = ");
        System.out.println(numFormat.format(getMax()));
        
        //print range
        System.out.print("Range = ");
        System.out.println(numFormat.format(getRange()));
        
        //print mean
        System.out.print("Mean = ");
        System.out.println(numFormat.format(getMean()));
        
        //print standerd deviation
        System.out.print("sd = ");
        System.out.println(numFormat.format(getStDev()));
        
        //print median
        System.out.print("median = ");
        System.out.println(numFormat.format(getMedian()));
        System.out.println();
    }
    
    /**
     * userStringInput -- Creates a scanner and gets user string input
     * @author Korey Sniezek
     * @version 1.0
     * @since MS1
     * @return user string
     */
    public String userStringInput () {
        Scanner console = null;
        String rString = "";
        try{
            console = new Scanner(System.in);
        }
        catch(Exception e){
            System.out.println("Stdin not found.");
            System.exit(1);
        }
        if(console.hasNextLine()){
            rString = console.nextLine();
        }
        return rString;
    }
    
    /**
     * getAllProperties -- returns the list of all property objects
     *      Version 2 edited to return ObservableList
     * @return ArrayList of properties
     * @author Korey Sniezek
     * @version 2.0
     * @since MS1
     */
    public ObservableList<Property> getAllProperties(){
        return properties;
    }
    
    /**
     * promptAndGetNeighbourhood -- prompt the user for a neighbourhood and 
     *  create and return a new Neighbourhood, which is a property handler
     *  containing only objects from that neighbourhood
     * @author Korey Sniezek
     * @version 1.0
     * @return Neighbourhood object
     * @since MS1
     * @throws FileNotFoundException
     */
    public Neighbourhood promptAndGetNeighbourhood() throws FileNotFoundException {
        System.out.print("Neighbourhhood: ");
        Neighbourhood temp = new Neighbourhood(this, userStringInput());
        
        if(temp.getAllProperties().isEmpty()){
            System.out.println("Property not found."); 
            return null;
        }
        return temp;
    }
    /**
     * promptAndGetAssesssment -- propmts the user for an assessment name 
     *  populates an Assessment property handler with properties that match
     *  the name
     * @author Korey Sniezek
     * @since Lab3
     * @version 1.0
     * @return Assessment
     * @throws FileNotFoundException
     */
    public Assessment promptAndGetAssessment() throws FileNotFoundException{
        System.out.print("Assessment class:");
        Assessment temp = new Assessment(this, userStringInput());
        
        if(temp.getAllProperties().isEmpty()){
            System.out.println("Property not found.");
            return null;
        }
        return temp;
    }
    
    /**
     * --addProperty
     * @purpose adds a property object to the list, used in searching
     * @author Korey Sniezek
     * @since MS2
     * @version 1.0
     * @param o (Property)
     */
    public void addProperty(Property o){
        properties.add(o);
    }
    
    /**
     * --findPropertiesByAddress
     * @purpose To iterate throught the properties and return a list of properties
     *    with matching full addresses
     * @author Korey Sniezek
     * @since MS2
     * @version 1.0
     * @param address (String)
     * @return ArrayList Property propertiesFound
     */
    public ArrayList<Property> findPropertiesByAddress(String address){
        ArrayList<Property> propertiesFound = new ArrayList();
        for(int i = 0; i < properties.size(); i++){
            if(properties.get(i).getFullAddress() == null ? address == null : properties.get(i).getFullAddress().equals(address)){
                propertiesFound.add(properties.get(i));
            }
        }
        return propertiesFound;
    }
    
    /**
     * --findPropertiesBySearchFields
     * @purpose This returns a list of properties that match the search parameters
     *    it is worth noting that  assessment class can remain unselected
     *    during the search, however its field cannot be empty, so it will only
     *    be involved in the check if it is non-empty
     *    
     *    there are acceptable empty values for address and neighbourhood
     *    however, as they are being treated as "not searched for" when left
     *    empty, properties with those values empty will only appear during
     *    searches with other valid search values.
     * 
     * @param account
     * @param address
     * @param neigh
     * @param assess
     * @return 
     */
    public ArrayList<Property> findPropertiesBySearchFields(String account, String address, String neigh, String assess){
        ArrayList<Property> propertiesToReturn = new ArrayList();
        
        // accounts are unique, early bailout. Also bailout if non-empty and junk data
        if(!"".equals(account)){
            try {
                propertiesToReturn.add(findAccount(Integer.parseInt(account)));
                return propertiesToReturn;
            }
            catch(Exception e){
                return propertiesToReturn;
            }
        }
        
        if(assess == null){
            assess = "";
        }
        
        for(int i = 0; i < properties.size(); i++){
            // if address is empty, or adress is equal to property address
            if("".equals(address) || address.equalsIgnoreCase(properties.get(i).getFullAddress())){
                if("".equals(neigh) || neigh.equalsIgnoreCase(properties.get(i).getNeighbourhood())){
                    if("".equals(assess) || properties.get(i).hasAssessClass(assess)){
                        propertiesToReturn.add(properties.get(i));
                    }
                }
            }
        }
        
        
        
        
        return propertiesToReturn;
    }
    
    public ArrayList<Property> findPropertiesByWard(String ward){
        ArrayList<Property> propertiesFound = new ArrayList();
        for(int i = 0; i < properties.size(); i++){
            if(properties.get(i).getFullAddress() == null ? ward == null : properties.get(i).getWard().equals(ward)){
                propertiesFound.add(properties.get(i));
            }
        }
        return propertiesFound;
    }
    public ArrayList<Property> findPropertiesByNeighbourhood(String nbrhd){
        ArrayList<Property> propertiesFound = new ArrayList();
        for(int i = 0; i < properties.size(); i++){
            if(properties.get(i).getFullAddress() == null ? nbrhd == null : properties.get(i).getNeighbourhood().equals(nbrhd)){
                propertiesFound.add(properties.get(i));
            }
        }
        return propertiesFound;
    }
}
