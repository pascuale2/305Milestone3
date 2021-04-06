package MS2;

/**
 * This class contains the methods for the Address class. Data about
 * each property assessment contained within a csv file is passed to this 
 * class and a an Address object is created for each property assessment object.
 * These objects contain the suite number, house number, street
 * name and garage indicator (Y/N) for each property assessment, as well as 
 * functions to allow other classes to access the data.
 * 
 * @author Jason
 */
public class Address {
    private final String suite;
    private final String houseNum;
    private final String streetName;
    private final String garage;
    
    /**
     * Constructor for Address class
     * @param data - list containing data from csv file read to memory.
     */
    public Address(String[] data){
        suite = data[1];
        houseNum = data[2];
        streetName = data[3];
        garage = data[4];
    }
    /**
     * getSuite - returns the suite number associated with a 
     * PropertyAssessment object
     * 
     * @return - suite number associated with a PropertyAssessment object
     */
    public String getSuite(){
        return suite;
    }
    /**
     * getHouseNum - returns the house number associated with a 
     * PropertyAssessment object
     * 
     * @return - the house number associated with a PropertyAssessment object
     */
    public String getHouseNum() {
        return houseNum;
    }
    /**
     * getStreetName - returns the street name associated with a 
     * PropertyAssessment object
     * 
     * @return - the street name associated with a PropertyAssessment object
     */
    public String getStreetName() {
        return streetName;
    }
    /**
     * getGarage - returns Y or N depending on if a PropertyAssessment object
     * has a garage
     * 
     * @return - Y or N depending on if a PropertyAssessment object has a 
     * garage
     */
    public String getGarage() {
        return garage;
    }
    /**
     * toString - override the toString method to print the Address object
     * @return - string display of Address
     */
    @Override
    public String toString(){
        if (suite.equals("")){
            return String.format("%s %s", houseNum, streetName);
        }
        else {
            return String.format("%s %s %s", suite, houseNum, streetName);
        }
    }
}
