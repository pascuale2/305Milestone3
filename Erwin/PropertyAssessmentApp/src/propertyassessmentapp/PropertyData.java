/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propertyassessmentapp;

import static propertyassessmentapp.Stats.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is for the PropertyData object which constructs the needed information
 * to parse the user inputted CSV files.
 * 
 * @author Erwin Pascual
 */
public class PropertyData {
    private final int account, neighborhoodId;
    private final String suite, houseNum, streetName, garage;
    private final String address, neighborhood, ward;
    private final int assClassPercent1, assClassPercent2, assClassPercent3;
    private int[] assPercentages;
    private final String assClass1, assClass2, assClass3;
    private String[] assClasses;
    private final Double assVal, longitude, latitude;
    
    /**
     * Property - constructor class for each of the variables
     * 
     * @param data which is the List of Property objects
     */
    public PropertyData(String data){
        String[] metadata = data.split(",");
        this.account = Integer.parseInt(metadata[0]);
        this.suite = metadata[1];
        this.houseNum = metadata[2];
        this.streetName = metadata[3];
        this.address = String.format("%s %s %s", this.suite, this.houseNum, this.streetName);
        this.garage = metadata[4];
        this.neighborhoodId = (metadata[5].length() > 0) ? Integer.parseInt(metadata[5]): -1;
        this.neighborhood = metadata[6];
        this.ward = metadata[7];
        this.assVal = Double.parseDouble(metadata[8]);
        this.latitude = Double.parseDouble(metadata[9]);
        this.longitude = Double.parseDouble(metadata[10]);
        this.assClassPercent1 = (metadata[12].length() > 0) ? Integer.parseInt(metadata[12]): -1;
        this.assClassPercent2 = (metadata[13].length() > 0) ? Integer.parseInt(metadata[13]): -1;
        this.assClassPercent3 = (metadata[14].length() > 0) ? Integer.parseInt(metadata[14]): -1;
        this.assPercentages = new int[] {this.assClassPercent1, this.assClassPercent2, this.assClassPercent3};
        this.assClass1 = metadata[15];
        this.assClass2 = (metadata.length > 16)? metadata[16]: "";
        this.assClass3 = (metadata.length > 17) ? metadata[17]: "";
        this.assClasses = new String[] {this.assClass1, this.assClass2, this.assClass3};
    }

    /**
     * getData - takes in the specified filename and parses the needed data line
     * by line and returns a List of Property Objects with the parsed data.
     * 
     * @param fileName the file that we want parsed
     * @return the parsed data into a List of Property Objects
     */
    public static List<PropertyData> getData(String fileName){
        String line;
        List<PropertyData> data = new ArrayList<>();
        PropertyData property;
        
        try {
            BufferedReader br = Files.newBufferedReader(Paths.get(fileName));
            br.readLine();
            while ((line = br.readLine()) != null){
                property = new PropertyData(line);
                data.add(property);
            }
        } catch (IOException ex){
            Logger.getLogger(Milestone2Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
    /**
     * printAllPropertyAssessment - prints all descriptive statistics of property
     * assessment values.
     * 
     * @param data the List of Property objects that we are going to parse 
     */
    public static void printAllPropertyAssessment(List<PropertyData> data){
        List<Double> propAssess = new ArrayList();
        for (int i = 0; i < data.size(); i++){
                propAssess.add(data.get(i).getAssVal());
            }
        printStats(propAssess); //prints the stats of Assessment 
    }
    
//    /**
//     * getPropertyAssessment - prints the information that pertains to the 
//     * Account Number the user specifies
//     * 
//     * @param console 
//     * @param data The List of Property objects that we are going to parse
//     */
//    public static void getPropertyAssessment(Scanner console, List<PropertyData> data){
//        System.out.println("\nFind a property assessment by account number: ");
//        String accNum = console.nextLine(); // Read User Input
//        for (int i = 0; i < data.size(); i++){
//            if (data.get(i).getAccount() == Integer.parseInt(accNum)){
//                System.out.println(data.get(i).toString());
//                System.out.println();
//                break;
//            }
//        }
//    }

    /**
     * getSimilarAccount - gets the user inputted accounts as a List of PropertyData
     * 
     * @param input user inputted accounts
     * @param data the List of Property objects that we are going to parse
     * @return List of PropertyData containing user inputted accounts
     */
    public static List<PropertyData> getSimilarAccount(String input, List<PropertyData> data){
        List<PropertyData> accounts = new ArrayList();
        
        for (int i = 0; i < data.size(); i++){
            if (String.valueOf(data.get(i).getAccount()).contains(input))
            {
                accounts.add(data.get(i));
            }
        }
        return accounts;
    }
    
    /**
     * getSimilarAddress - gets the user inputted address as a List of PropertyData
     * 
     * @param input user inputted address
     * @param data the List of Property objects that we are going to parse
     * @return List of PropertyData containing user inputted addresses
     */
    public static List<PropertyData> getSimilarAddress(String input, List<PropertyData> data){
        List<PropertyData> addresses = new ArrayList();
        input = input.toUpperCase();
        
        for (int i = 0; i < data.size(); i++){
            if (data.get(i).getAddress().contains(input))
            {
                addresses.add(data.get(i));
            }
        }
        return addresses;
    }
    
    /**
     * getSimilarNeighborhood - gets the user inputted neighborhood as a List of
     * PropertyData
     * 
     * @param input user inputted neighborhood
     * @param data the List of Property objects that we are going to parse
     * @return List of PropertyData containing user inputted Neighborhoods
     */
    public static List<PropertyData> getSimilarNeighborhood(String input, List<PropertyData> data){
        List<PropertyData> neighbor= new ArrayList();
        input = input.toUpperCase();
        
        for (int i = 0; i < data.size(); i++){
            if (data.get(i).getNeighborhood().equalsIgnoreCase(input))
            {
                neighbor.add(data.get(i));
            }
        }
        return neighbor;
    }
    
    /**
     * getDistinctClasses - gets the distinct Assessment Classes of the Property Data
     * 
     * @param data the List of Property objects that we are going to parse
     * @return Set of distinct classes
     */ 
    public static Set<String> getDistinctClasses(List<PropertyData> data){
        Set<String> distinctAssClass = new HashSet<>();

        data.forEach(row -> {
            distinctAssClass.add(row.getAssClass1());
        });
        return distinctAssClass;
    }
    
    /**
     * getSimilarClasses - gets the user inputted Assessment Classes of the Property Data
     * 
     * @param input user inputted assessment class
     * @param data the List of Property objects that we are going to parse
     * @return List of PropertyData containing user inputted Assessment Classes
     */ 
    public static List<PropertyData> getSimilarClasses(String input, List<PropertyData> data){
        List<PropertyData> assClass = new ArrayList();

        data.forEach(row -> {
            if (row.getAssClass1().equals(input)){
                assClass.add(row);
            }
        });
        return assClass;
    }
    
    
//    /**
//     * getNeighborhoodStats - prints the specified Neighborhood statistics
//     * 
//     * @param console Asks the user for the neighborhood as input to get stats
//     * @param data the List of Property objects that we are going to parse
//     */
//    public static void getNeighborhoodStats(Scanner console, List<PropertyData> data){
//        List<Double> neighborPropAssess = new ArrayList();
//        System.out.println("Neighbourhood: ");
//        String neighborhoodInp = console.nextLine(); // Read User Input
//        
//        System.out.printf("Statistics (neighborhood = %s)", neighborhoodInp);
//        for (int i = 0; i < data.size(); i++){
//            if (data.get(i).getNeighborhood().toUpperCase().equalsIgnoreCase(neighborhoodInp))
//            {
//                neighborPropAssess.add(data.get(i).getAssVal());
//            }
//        }
//        printStats(neighborPropAssess); //prints the stats of Assessment 
//    }
//    
    /**
     * getAssessmentStats - searches for user inputted Assessment Class and if
     * it matches, it adds the property Assessment values to a list which then
     * returns the descriptive statistics of it as a String.
     * 
     * @param data The List of Property objects that we are going to parse
     * @return String of assessed data
     */
    public static String getAssessmentStats(List<PropertyData> data){
        List<Double> assessClassData = new ArrayList();

        data.forEach(row -> {
            assessClassData.add(row.getAssVal());
        });
        return printStringStats(assessClassData);
    }
    
    /**
     * toString - overrides java's default toString() to print a formatted version
     * of the variables mentioned above.
     * 
     * @return formatted string of: Account Number, Address, Assessed Value,
     * Assessment Class, Neighborhood, Location (Latitude, Longitude)
     */
    @Override
    public String toString() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return String.format("Account number = %d", this.account) +
                String.format("\nAddress = %s", this.address) +
                String.format("\nAssessed value = %s", formatter.format(this.assVal)) + 
                String.format("\nAssessment class = %s", this.assClass1) +
                String.format("\nNeighbourhood = %s (%s)", this.neighborhood, this.ward) +
                String.format("\nLocation = (%f, %f)", this.longitude, this.latitude);            
    }
    
    /**
     * getAccount - getter function for Account Number
     * @return Account Number of type int
     */
    public int getAccount() { return this.account; }

    /**
     * getAddress - getter function for Address
     * @return Address of type String
     */
    public String getAddress() { return this.address; }
    /**
     * getAcc - getter function for Assessment Value
     * @return Assessment Value of type Double
     */
    public Double getAssVal(){ return this.assVal; }
    /**
     * getAssClass1 - getter function for the primary Assessment Class
     * @return 
     */
    public String getAssClass1() { return this.assClass1; }
    /**
     * getAcc - getter function for Neighborhood
     * @return Neighborhood of type String
     */
    public String getNeighborhood(){ return this.neighborhood; }
    /**
     * getLattitude - getter function for Latitude
     * @return 
     */
    public Double getLatitude() { return this.latitude; }
    /**
     * getAcc - getter function for Longitude
     * @return 
     */
    public Double getLongitude() { return this.longitude; }
    /**
     * getAssessClassess - getter function for Assessment Classes 1, 2 and 3
     * @return Assessment Classes of type String[]
     */
    public String[] getAssessClasses(){ return this.assClasses; }
}
