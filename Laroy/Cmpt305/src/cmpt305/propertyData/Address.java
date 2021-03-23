package cmpt305.propertyData;


/**
 * This stores an address given a suite, houseNum, streetName
 *
 * @author Laroy Milton
 */
public class Address {
    String suite, houseNum, streetName;
    
    /**
     * constructor for address
     * 
     * @param suite string of the suite number
     * @param houseNum string of the house number
     * @param streetName string of the street name
     */
    public Address(String suite, String houseNum, String streetName) {
        this.suite  = suite;
        this.houseNum = houseNum;
        this.streetName = streetName;
    }
    
    /**
     * string representation of the Address object
     * 
     * @return string of the address in the form of suite, house  number, street name
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(suite).append(" ");
        str.append(houseNum).append(" ");
        str.append(streetName);
        return str.toString();        
    }
    
}
