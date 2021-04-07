/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Milestone3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
    protected BufferedReader br;
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
     * @purpose New constructor added as part of milestone 3 to handle
     *    default initialization without user prompt for the GUI
     *    this constructor is essentially the "loadPropertyFromFile" method
     *    without user input but now it can take a file URL as well
     * 
     * @author Korey Sniezek, Erwin Pascual
     * @since MS3
     * @version 3.0
     * @param fileString
     * @param loadFromURL
     */
    public PropertyHandler(String fileString, Boolean loadFromUrl) throws IOException {
        String raw;
        try{
            if (loadFromUrl){
                InputStream input = new URL(fileString).openStream();
                Reader reader = new InputStreamReader(input, "UTF-8");
                br = new BufferedReader(reader);
            }
            else{
                br = new BufferedReader(new FileReader(fileString));
            }
        }
        catch(Exception e){
            System.out.println("File OR URL not found.");
            return;
        }
        br.readLine();
        while ((raw = br.readLine())!= null) {
            // Try to create with line, announce bad data and display if fail
            try{
                properties.add(new Property(raw));
            }
            catch(Exception exc){
                System.out.println("Bad line of data:");
                System.out.println(raw);
            }
        }
        br.close();
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
    /**
     * --findPropertiesByWard
     * @purpose This returns a list of properties that match the ward param. It
     * takes the ward, and searches through all the properties, adding each
     * Property that matches the argument to a new ArrayList to be returned.
     *    
     * 
     * @param ward
     * @return propertiesFound - list of Properties that match the param
     */
    public ArrayList<Property> findPropertiesByWard(String ward){
        ArrayList<Property> propertiesFound = new ArrayList();
        for(int i = 0; i < properties.size(); i++){
            if(properties.get(i).getFullAddress() == null ? ward == null : properties.get(i).getWard().equals(ward)){
                propertiesFound.add(properties.get(i));
            }
        }
        return propertiesFound;
    }
    /**
     * --findPropertiesByNeighbourhood
     * @purpose This returns a list of properties that match the nbrhd param. It
     * takes the ward, and searches through all the properties, adding each
     * Property that matches the argument to a new ArrayList to be returned.
     *    
     * 
     * @param nbrhd
     * @return propertiesFound - list of Properties that match the param
     */
    public ArrayList<Property> findPropertiesByNeighbourhood(String nbrhd){
        ArrayList<Property> propertiesFound = new ArrayList();
        for(int i = 0; i < properties.size(); i++){
            if(properties.get(i).getFullAddress() == null ? nbrhd == null : properties.get(i).getNeighbourhood().equals(nbrhd)){
                propertiesFound.add(properties.get(i));
            }
        }
        return propertiesFound;
    }
    /**
     * --findPropertiesByAssessmentClass
     * @purpose This returns a list of properties that match the aClass param. It
     * takes the ward, and searches through all the properties, adding each
     * Property that matches the argument to a new ArrayList to be returned.
     *    
     * 
     * @param aClass
     * @return propertiesFound - list of Properties that match the param
     */
    public ArrayList<Property> findPropertiesByAssessmentClass(String aClass){
        ArrayList<Property> propertiesFound = new ArrayList();
        for(int i = 0; i < properties.size(); i++){
            for (String c : properties.get(i).getClasses()){
                if(c == null ? aClass == null : c.equals(aClass)){
                    propertiesFound.add(properties.get(i));
                }
            }
        }
        return propertiesFound;
    }
    /**
     * --findPropertiesByGarage
     * @purpose This returns a list of properties that match the g param. It
     * takes the ward, and searches through all the properties, adding each
     * Property that matches the argument to a new ArrayList to be returned.
     *    
     * 
     * @param g - Garage indicator (Y/N)
     * @return propertiesFound - list of Properties that match the param
     */
    public ArrayList<Property> findPropertiesByGarage(String g){
        ArrayList<Property> propertiesFound = new ArrayList();
        for(int i = 0; i < properties.size(); i++){
            if(properties.get(i).getGarage() == null ? g == null : properties.get(i).getGarage().equals(g)){
                propertiesFound.add(properties.get(i));
            }
        }
        return propertiesFound;
    }
}
