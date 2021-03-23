package cmpt305.propertyData;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static cmpt305.statistics.Stats.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


/**
 * This class contains a line of input from a Property_Assessment_Data.csv file
 * and static functions for processing data searches
 *
 * @author Laroy Milton
 */
public class Property {
    private final int account, neighborhoodID;
    private final Address address;
    private final String neighbourhood, ward;
    private final boolean garage;
    private final Double value, longitude, latitude;
    private final int assesmentClass1Percent, assesmentClass2Percent, assesmentClass3Percent;
    private final String assesClass1, assesClass2, assesClass3;
    private String assesClass;
    private final List<String> assesClasses;
    private final String pointLoc;
    
    /**
     * Property constructor
     * 
     * @param data a string representation of the row in a csv file.
     */
    public Property(String data){
        String[] metadata = data.split(",");
        account = Integer.parseInt(metadata[0]);
        address = new Address(metadata[1], metadata[2], metadata[3]);
        garage = ("Y".equals(metadata[4].toUpperCase()));
        neighborhoodID = (!metadata[5].isBlank()) ? Integer.parseInt(metadata[5]) : -1;
        neighbourhood = metadata[6];
        ward = metadata[7];
        value = Double.parseDouble(metadata[8]);
        latitude = Double.parseDouble(metadata[9]);
        longitude = Double.parseDouble(metadata[10]);
        pointLoc = metadata[11];
        assesmentClass1Percent = Integer.parseInt(metadata[12]);
        assesmentClass2Percent = (!metadata[13].isBlank()) ? Integer.parseInt(metadata[13]) : 0;
        assesmentClass3Percent = (!metadata[14].isBlank()) ? Integer.parseInt(metadata[14]) : 0;
        assesClass1 = metadata[15];
        assesClass2 = (metadata.length > 16) ? metadata[16] : null;
        assesClass3 = (metadata.length > 17) ? metadata[17] : null;
        assesClasses = Arrays.asList(assesClass1, assesClass2, assesClass3);
        assesClass = assesClass1;
    }

    /**
     * toString -- Overrides the string output of the object
     * 
     * @return String
     */
    @Override
    public String toString() {
        NumberFormat fmt = NumberFormat.getCurrencyInstance();
        fmt.setMaximumFractionDigits(0);
        
        return String.format("Account number = %d", account) + 
                String.format("\nAddress = %s", address) + 
                String.format("\nAssessed value = $%s", fmt.format(value)) + 
                String.format("\nAssessment class = %s", assesClass1) + 
                String.format("\nNeighborhood = %s (%s)", neighbourhood, ward) +
                String.format("\nLocation = (%f, %f)", latitude, longitude);
    }
    
    /**
     * getData -- returns a list of all the data entries of PropertyData objects 
     * 
     * @param fileName String of the file name to be parsed
     * @return List of PropertyData objects
     */
    public static List<Property> getData(String fileName) {
        List<Property> data = new ArrayList<>();
        String line;
        Property property;
        
        try {
            BufferedReader csvReader = Files.newBufferedReader(Paths.get(fileName));
            csvReader.readLine(); // ignore first line
            while ((line = csvReader.readLine()) != null){
                property = new Property(line);
                data.add(property);
            }
            csvReader.close();  
        } catch (IOException ex){
            Logger.getLogger(Property.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
    
    /**
     * getAssesClassData -- returns a list of PropertyData objects given an
     * assessment class name from the list of PropertyData objects.
     * 
     * @param data list of PropertyData objects
     * @param assesClassName the name of the assessment class to search
     * @return List of PropertyData objects containing only the assessment 
     * class name
     */
    public static List<Property> getAssesClassData(List<Property> data, String assesClassName) {
        List<Property> assesClassData = new ArrayList<>();
        
        for (Property property : data) {
            if (property.getAssesClasses().contains(assesClassName.toUpperCase())) {
                property.setAssesClass(assesClassName);
                assesClassData.add(property);
            }
        }
        return assesClassData;
    }
     
    /**
     * getPropertyByAccount -- returns the property data given the account number provided
     * 
     * @param data list of PropertyData objects to search
     * @param accNum the account number to search within the data list
     * @return PropertyDatat object or null if none found
     */
    public static List<Property> getPropertyByAccount(List<Property> data, int accNum){
        List<Property> accData = new ArrayList<>();
        for (Property property : data){
            if (property.getAccount() == accNum){
                accData.add(property);
            }
        }
        return accData;
    }
    
    /**
     * getPropertyByAddress - returns the list of properties by address
     * 
     * @param data the list of property data
     * @param addr the address to search by
     * @return the list of properties by address
     */
    public static List<Property> getPropertyByAddress(List<Property> data, String addr) {
        List<Property> accData = new ArrayList<>();
        
        for (Property property : data){
            if (property.getAddress().toString().contains(addr)){
                accData.add(property);
            }
        }
        return accData;
    }
    
    /**
     * getNeighborhoodData -- returns a list of PropertyData objects given a
     * neighbourhood name to search within a list of PropertyData objects 
     * 
     * @param data list of PropertyData objects
     * @param neighborhoodName String name of the neighbourhood
     * @return list of propertyData objects from a given neighbourhood 
     */
    public static List<Property> getNeighborhoodData(List<Property> data, String neighborhoodName){
        List<Property> neighborhood = new ArrayList<>();
        for (Property property : data) {
            if (property.getNeighbourhood().equals(neighborhoodName.toUpperCase())) {
                neighborhood.add(property);
            }
        }
        return neighborhood;
    }
    
    /**
     * printPropertyStats -- prints the statistics of a PropertyData list 
     * 
     * @param data the list of PropertyData objects to perform statistics on
     */
    public static void printPropertyStats(List<Property> data){
        
        if (data.size() < 1){ // empty list
            return;
        }
        
        List<Double> propAsses = new ArrayList();
        for (int i = 0; i < data.size(); i++){
            propAsses.add(data.get(i).getValue());
        }
        printStats(propAsses);
    }
    
    /**
     * stringPropertyStats -- prints the statistics of a PropertyData list 
     * 
     * @param data the list of PropertyData objects to perform statistics on
     * @return formatted string of the stats results
     */
    public static String stringPropertyStats(List<Property> data){
        
        if (data.size() < 1){ // empty list
            return " ";
        }
        
        List<Double> propAsses = new ArrayList();
        for (int i = 0; i < data.size(); i++){
            propAsses.add(data.get(i).getValue());
        }
        return stringStats(propAsses);
    }
    
    /**
     * getAssesClasses - returns the assesment classes
     * 
     * @return list of all the assesment classes related to this property
     */
    public List<String> getAssesClasses() {
        return assesClasses;
    }
    
    /**
     * getAllAssesClasses - returns all of the assesment classes given a 
     *  List of Property data
     * 
     * @param data List of Property data
     * @return the unique assesment classes Set of strings
     */
    public static  Set<String> getAllAssesClasses(List<Property> data) {
        Set<String> classes = new HashSet<>();
        for (Property d : data) {
            classes.addAll(d.getAssesClasses());
        }
        return classes;
    }
    
    /**
     * getAccNum - returns the account number of this property
     * 
     * @return the account number
     */
    public int getAccount() {
        return account;
    }
    
    /**
     * getValue - returns the property value
     * 
     * @return the value of the property
     */
    public Double getValue() {
        return value;
    }
    
    /**
     * getNeighborhood - returns the nehigborhhod this property belongs to
     * 
     * @return the neighbourhood of the property
     */
    public String getNeighbourhood(){
        return neighbourhood;
    }
    
    /**
     * getAssesClass - returns the first assesment class this property belongs to
     * 
     * @return the first assesment class of this property
     */
    public String getAssesClass() {
        return assesClass;
    }
    
    /**
     * setAssesClass - sets the assesmentClass to be displayed
     * 
     * @param newAssesClass the new assesment class to be displayed
     */
    public void setAssesClass(String newAssesClass) {
        this.assesClass = newAssesClass.toUpperCase();
    }
    
    /**
     * getAddress - getter for Address object
     * 
     * @return the Address object of this property data
     */
    public Address getAddress() {
        return address;
    }

    /**
     * getLongitude - getter for longitude
     * 
     * @return the longitude as a Double
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * getLatitude - getter for latitude
     * 
     * @return the latitude as a Double
     */
    public Double getLatitude() {
        return latitude;
    }

}
