package MS2;

import java.awt.geom.Point2D;

/**
 * This class contains the methods for a Location object. Data about
 * each property assessment contained within a csv file is passed to this 
 * class and a Location object is created for each property assessment object.
 * These objects contain location coordinates for each property assessment 
 * object, as well as functions to allow other classes to access the data.
 * 
 * @author Jason
 * 
 */
public class Location {
    private final double latitude;
    private final double longitude;
    private final Point2D.Double location;
    
    /**
     * constructor method for location class
     * @param data - list containing data from csv file read to memory.
     */
    public Location(String[] data){
        latitude = Double.parseDouble(data[9]);
        longitude = Double.parseDouble(data[10]);
        location = new Point2D.Double(latitude, longitude);
    }
    /**
     * getLatitude - returns the latitude of the location 
     * 
     * @return - latitude of property assessment
     */
    public double getLatitude(){
        return latitude;
    }
    /**
     * getLongitude - returns the longitude of the location
     * 
     * @return - longitude of property assessment
     */
    public double getLongitude(){
        return latitude;
    }
    /**
     * toString - override the toString method to print out the location in
     * the form of a point with x being latitude and y being longitude.
     * 
     * @return - string display of location
     */
    @Override
    public String toString(){
        return String.format("(%s, %s)", location.x, location.y);
    }
}
