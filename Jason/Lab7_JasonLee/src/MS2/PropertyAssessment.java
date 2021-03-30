package MS2;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * This class contains the methods for a property assessment object. Data about
 * each property assessment contained within a csv file is passed to this 
 * class and a PropertyAssessment object is created for each property assessment.
 * These objects contain the account number, suite number, house number, street
 * name, garage (Y/N), neighbourhood ID, neighbourhood name, ward, assessed value, 
 * location coordinates, assessment class percentages and assessment classes for 
 * each property assessment, as well as functions to allow other classes to 
 * access the data.
 * 
 * @author Jason
 * 
 * REFs:
 * https://www.geeksforgeeks.org/string-to-integer-in-java-parseint/
 */
public class PropertyAssessment {
    //data for each PropertyAssessment object
    private final Location l;
    private final Neighbourhood n;
    private final Address a;
    private final int acctNumber;
    private final double value;
    private final double[] classPercent;
    private final List<String> assessmentClass;
    
    /**
     * constructor for PropertyAssessment class
     * @param data - list of data associated with a single property assessment.
     */
    public PropertyAssessment(String[] data){
        acctNumber = Integer.parseInt(data[0]);
        l = new Location(data);
        n = new Neighbourhood(data);
        a = new Address(data);
        value = Double.parseDouble(data[8]);
        classPercent = new double[3];
        for (int i = 0; i < 3; i++) {
            if (data[12 + i].equals("")){
                classPercent[i] = 0;
            }
            else{
                classPercent[i] = Double.parseDouble(data[12 + i]);
            }
        }
        assessmentClass = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            if (!(data[15 + i].equals(""))){
                char c = data[15 + i].charAt(0);
                String s = c + data[15 + i].substring(1).toLowerCase();
                assessmentClass.add(s);
            }
        }
    }
    /**
     * getAcctNumber - returns the account number associated with a 
     * PropertyAssessment object
     * 
     * @return - acctNumber associated with a PropertyAssessment object
     */
    public int getAcctNumber(){
        return acctNumber;
    }
    /**
     * getAddress - returns the address object for the PropertyAssessment.
     * @return - Address object
     */
    public Address getAddress(){
        return a;
    }
    /**
     * getLocation - returns location object for the propertyAssessment object.
     * @return - location object
     */
    public Location getLocation(){
        return l;
    }
    /**
     * returns Neighbourhood object for PropertyAssessment object.
     * @return - neighbourhood object
     */
    public Neighbourhood getNeighbourhood(){
        return n;
    }
    /**
     * getValue - returns the assessment value associated with a 
     * PropertyAssessment object
     * 
     * @return - assessment value associated with a PropertyAssessment object
     */
    public double getValue(){
        return value;
    }
    /**
     * getClassPercent - returns an array of doubles containing the assessment
     * class percentage associated with each PropertyAssessment object
     * 
     * @return - array of doubles containing the assessment class percent 
     * associated with a PropertyAssessment object
     */
    public double[] getClassPercent(){
        return classPercent;
    }
    /**
     * getAssessmentClass - returns an array of doubles containing the assessment
     * class associated with each PropertyAssessment object
     * 
     * @return - array of doubles containing the assessment class associated 
     * with a PropertyAssessment object
     */
    public List<String> getAssessmentClass(){
        return assessmentClass;
    }
    /**
     * equals - override equals method to test if object o is of instance of
     * a PropertyAssessment object, and if so, checks if Object other account
     * number is equal to the PropertyAssessment object. Returns true if it is,
     * false otherwise.
     * @param o - object to be compared to PropertyAssessment object account 
     * number
     * @return - true if object o is a PropertyAssessment object and it's 
     * account number equals the PropertyAssessment object, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof PropertyAssessment) {
            PropertyAssessment other = (PropertyAssessment) o;
            return (acctNumber == other.getAcctNumber());
        }
        return false;
    }
    /**
     * hashCode - returns a hash code for the PropertyAssessment object's 
     * account number.
     * @return - hash code for Train objects ID
     */
    @Override
    public int hashCode() {
        return Objects.hash(acctNumber);
    }
    /**
     * getVal - returns a decimal formatted string of value attribute for 
     * display for UI
     * 
     * @return - decimal formatted string of the value attribute for display for
     * UI
     */
    public String getVal(){
        DecimalFormat df = new DecimalFormat("#,###");
        String str = String.format("$" + df.format(value));
        return str;
    }
    /**
     * getAClass - returns the first assessment class in the list of 
     * assessment classes for display on the UI
     * 
     * @return - string of first listed Assessment Class for UI display
     */
    public String getAClass(){
        Set<String> aClass = new HashSet<>(assessmentClass);
        String str = "";
        str = str.join(", ", aClass);
        return str;
    }
    /**
     * getNbrhd - returns the Property Assessment's neighbourhood name 
     * for UI display
     * 
     * @return - Property Assessment's neighbourhood name for UI display
     */
    public  String getNbrhd(){
        String str = n.getNbrhdName();
        return str;
    }
    /**
     * getLat - returns a string of the Property Assessment's latitude
     * for UI display
     * 
     * @return - string of the Property Assessment's latitude for UI display
     */
    public String getLat(){
        String str = Double.toString(l.getLatitude());
        return str;
    }
    /**
     * getLong - returns a string of the Property Assessment's longitude
     * for UI display
     * 
     * @return - string of the Property Assessment's longitude for UI display
     */
    public String getLong(){
        String str = Double.toString(l.getLongitude());
        return str;
    }
}
