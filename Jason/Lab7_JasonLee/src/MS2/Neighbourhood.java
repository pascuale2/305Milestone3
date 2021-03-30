package MS2;

/**
 * This class contains the methods for a Neighbourhood object. Data about
 * each property assessment contained within a csv file is passed to this 
 * class and a Neighbourhood object is created for each property assessment 
 * object. These objects contain the Neighbourhood ID, Neighbourhood name and 
 * ward for each property assessment, as well as functions to allow other 
 * classes to access the data.
 * 
 * @author Jason
 * 
 */
public class Neighbourhood {
    private final String nbrhdID;
    private final String nbrhdName;
    private final String ward;
    
    /**
     * Constructor for Neighbourhood class
     * @param data - list containing data from csv file read to memory.
     */
    public Neighbourhood(String[] data){
        nbrhdID = data[5];
        nbrhdName = data[6];
        ward = data[7];
    }
    /**
     * getNbrhdID - returns the neighbourhood ID associated with a 
     * PropertyAssessment object
     * 
     * @return - the neighbourhood ID associated with a PropertyAssessment object
     */
    public String getNbrhdID() {
        return nbrhdID;
    }
    /**
     * getNeighbourhood - returns the neighbourhood name associated with a 
     * PropertyAssessment object
     * 
     * @return - the neighbourhood name associated with a PropertyAssessment object
     */
    public String getNbrhdName(){
        return nbrhdName;
    }
    /**
     * getWard - returns the ward associated with a 
     * PropertyAssessment object
     * 
     * @return - the ward associated with a PropertyAssessment object
     */
    public String getWard(){
        return ward;
    }
    /**
     * toString - override the toString method to print the Neighbourhood object
     * when called in print.
     * 
     * @return - string display of Neighbourhood.
     */
    @Override
    public String toString(){
        return String.format("%s (%s)", nbrhdName, ward);
    }
}
