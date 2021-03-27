/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Milestone2;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * This class represents an individual property, and will have all of the 
 * associated values attached with methods to retrieve each field. Assumes a 
 * valid input format
 * 
 * @version 1.0
 * @author Korey
 * @since Lab 2
 */
public class Property implements Comparable<Property>{
    private int accountNumber;
    private String suite;
    private String houseName;
    private String street;
    private String garage;
    private int neighbourhoodID;
    private String neighbourhood;
    private String ward;
    private int value;
    private Double latitude;
    private Double longitude;
    private String point;
    private int assess1Percent;
    private int assess2Percent;
    private int assess3Percent;
    private String assessClass1;
    private String assessClass2;
    private String assessClass3;
    
    /**
     * This constructor takes a whole line of properly formatted data (csv with
     * proper fields) and seperates it, checks it, parses it, and puts it
     * into its proper fields
     * 
     * @author Korey Sniezek (KMS)
     * @param data
     * @version 1.0
     * @since lab2
     */
    public Property(String data) {
        
        try{
            String raw[] = data.split(",");
            accountNumber = Integer.parseInt(raw[0]);
            suite = raw[1];
            houseName = raw[2];
            street = raw[3];
            garage = raw[4];
        
            if(!"".equals(raw[5])){
                neighbourhoodID = Integer.parseInt(raw[5]);
            }
            neighbourhood = raw[6];
            ward = raw[7];
            if(!"".equals(raw[8])){
                value = Integer.parseInt(raw[8]);
            }
            if(!"".equals(raw[9])){
                latitude = Double.parseDouble(raw[9]);
            }
            if(!"".equals(raw[10])){
                longitude = Double.parseDouble(raw[10]);
            }
            point = raw[11];
            if(!"".equals(raw[12])){
                assess1Percent = Integer.parseInt(raw[12]);
            }
            if(!"".equals(raw[13])){
                assess2Percent = Integer.parseInt(raw[13]);
            }
            if(!"".equals(raw[14])){
                assess3Percent = Integer.parseInt(raw[14]);
            }
            if(raw.length > 15){
                    assessClass1 = raw[15];
                if(raw.length > 16){
                    assessClass2 = raw[16];
                if(raw.length > 17){
                    assessClass3 = raw[17];
                    }
                }
            }
        }
        catch(Exception parse){
            System.out.println("Incorrect data format");
        }
   }
    
    
    /**
     * getAccount -- returns the internal account number
     * 
     * @author Korey Sniezek (KMS)
     * @version 2.0
     * @return int accountNumber
     * @since Lab 3
     * 
     */  
    
    public int getAccount () {
        return accountNumber;
    }
    
    /**
     * getSuite -- returns the gets the associated suite string
     * 
     * @author Korey Sniezek (KMS)
     * @version 2.0
     * @return String suite
     * @since Lab 3
     * 
     */    
    public String getSuite(){
        return suite;
    }
    
    /**
     * getHouseName -- returns the a String representation of the house name
     * 
     * @author Korey Sniezek (KMS)
     * @version 2.0
     * @return String houseName
     * @since Lab 3
     * 
     */    
    
    public String getHouseName(){
        return houseName;
    }
    
    /**
     * getStreet -- returns a string representation of the street
     * 
     * @author Korey Sniezek (KMS)
     * @version 2.0
     * @return String street
     * @since Lab 3
     * 
     */    
    
    public String getStreet(){
        return street;
    }
    
    /**
     * getGarage -- returns a string, Y or N, if it has a garage or not
     * 
     * @author Korey Sniezek (KMS)
     * @version 2.0
     * @return String Garage
     * @since Lab 3
     * 
     */   
    public String getGarage (){
        return garage;
    }
    
    /**
     * getNID -- returns an integer representation of the neighbourhood ID
     * 
     * @author Korey Sniezek (KMS)
     * @version 2.0
     * @return int neighbourhoodID
     * @since Lab 3
     * 
     */   
    public int getNID (){
        return neighbourhoodID;
    }
    
    /**
     * getN -- returns a string representation of the neighbourhood
     * 
     * @author Korey Sniezek (KMS)
     * @version 2.0
     * @return String neighbourhood
     * @since Lab 3
     * 
     */   
    public String getNeighbourhood (){
        return neighbourhood;
    }
    /**
     * getClasses -- returns a string list of classes for this property
     * @author Korey Sniezek
     * @return ArrayList String classes
     * @version 1.0
     * @since MS1
     */
    public ArrayList<String> getClasses(){
        ArrayList <String> temp = new ArrayList <> ();
        
        temp.add(assessClass1);
        if(assessClass2 != null && !"".equals(assessClass2)){
            temp.add(assessClass2);
        }
        if(assessClass2!= null && !"".equals(assessClass3)){
            temp.add(assessClass2);
        }
        return temp;
    }
    /**
     * getWard -- returns a string representation of the ward
     * 
     * @author Korey Sniezek (KMS)
     * @version 2.0
     * @return String Ward
     * @since Lab 3
     * 
     */   
    public String getWard (){
        return ward;
    }
    
    /**
     * getValue -- returns a integer representation of the value
     * 
     * @author Korey Sniezek (KMS)
     * @version 2.0
     * @return int value
     * @since Lab 3
     * 
     */   
    public int getValue (){
        return value;
    }
    
    /**
     * getLong -- returns a double represntation of the longitude
     * 
     * @author Korey Sniezek (KMS)
     * @version 2.0
     * @return double longitude
     * @since Lab 3
     * 
     */   
    public Double getLong (){
        return longitude;
    }
    
    /**
     * getLat -- returns a double representation of the latitude
     * 
     * @author Korey Sniezek (KMS)
     * @version 2.0
     * @return double latitude
     * @since Lab 3
     * 
     */   
    public Double getLat (){
        return latitude;
    }
    
    /**
     * getFullAddress -- returns a string representation of the full address
     * 
     *  #2021-03-22 KMS added conditional to remove extra space when parsing
     * 
     * @author Korey Sniezek (KMS)
     * @version 2.0
     * @return String full address
     * @since Lab 3
     * 
     */   
    public String getFullAddress (){
        String temp = "";
        if(!"".equals(suite)){
            temp = suite + " " + houseName + " " + street;
        }
        else{
            temp = houseName + " " + street;
        }
        
        return temp;
    }
    
    /**
     * getClass1 -- returns a string representation of the first assesment 
     *  class
     * 
     * @author Korey Sniezek (KMS)
     * @version 2.0
     * @return String assessClass1
     * @since Lab 3
     * 
     */   
    public String getClass1(){
        return assessClass1;
    }
    
    /**
     * getClass1 -- returns a string representation of the second assesment 
     *  class
     * 
     * @author Korey Sniezek (KMS)
     * @version 1.0
     * @return String assessClass1
     * @since MS2
     * 
     */   
    public String getClass2(){
        return assessClass2;
    }
    
    /**
     * getClass3 -- returns a string representation of the third assesment 
     *  class
     * 
     * @author Korey Sniezek (KMS)
     * @version 1.0
     * @return String assessClass3
     * @since MS2
     * 
     */   
    public String getClass3(){
        return assessClass3;
    }
    
    
    /**
     * getNeiPlusWard -- returns a string representation of the neighbourhood
     *  plus the ward
     * 
     * @author Korey Sniezek (KMS)
     * @version 2.0
     * @return String neighbourhood + ward
     * @since Lab 3
     * 
     */   
    public String getNeiPlusWard(){
        String temp = neighbourhood + " " + ward;
        return temp;
    }
    
    /**
     * getPoint -- returns a string representation of the coordinates
     * 
     * @author Korey Sniezek (KMS)
     * @version 2.0
     * @return String point 
     * @since Lab 3
     * 
     */   
    public String getPoint(){
        String temp = "(" + latitude + ", " + longitude + ")";
        return temp;
    }
    
    /**
     * compareTo -- overrides the compareTo operator to compare by value
     * 
     * @author Korey Sniezek (KMS)
     * @version 2.0
     * @param other
     * @return integer difference between self and other
     * @since Lab 3
     * 
     */   
    @Override
    public int compareTo(Property other){
        return value - other.getValue();
    }
    
   /**
     * equals -- returns true if account numbers are equal
     * 
     * @author Korey Sniezek (KMS)
     * @version 2.0
     * @return boolean, true if account numbers match
     * @param other
     * @since Lab 3
     * 
     */   
    @Override
    public boolean equals (Object other){
        Property temp;
        if(other instanceof Property){
            temp = (Property) other;
            if(((Property) other).getAccount() == accountNumber){
                return true;
            }
        }
        else{
            if(other instanceof Integer){
                if(accountNumber == (Integer) other){
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * hashCode -- netbeans generated hash function using accountNumber
     * 
     * @author Korey Sniezek (KMS)
     * @version 1.0
     * @return int hash
     * @since Lab 3
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.accountNumber;
        return hash;
    }
    
        /**
     * printAccountDetails prints a representation of the account
     *  with several details
     * 
     * #2021-01-28 KMS moved into PropertyHandler and modified
     * 
     * @author Korey Sniezek (KMS)
     * @version 2.1
     * @since Lab2 26 January 2021
     */
    public void printAccountDetails () {
        
        //use a number formatter to get properly formatted currency amounts
        NumberFormat numFormat = NumberFormat.getCurrencyInstance(Locale.CANADA);
        
        String temp = "Account number = " + accountNumber + "\n";
        temp = temp + "Address = " + this.getFullAddress() + "\n";
        
        temp = temp + "Assessed value = " + numFormat.format(value) + "\n";
        temp = temp + "Assessment class = " + assessClass1 + "\n";
        temp = temp + "Neighbourhood = " + neighbourhood + " ";
        temp = temp + "(" + ward + ")\n";
        temp = temp + "Location: (" + latitude + ", " + longitude + ")\n";
       
        
        System.out.println(temp);
        System.out.println();
    }
    
    /**
     * --hasAssessClass
     * @purpose returns true if the string class is one of the property's assessment
     *    classes
     * @author Korey Sniezek
     * @since MS2
     * @version 1.0
     * @param assess
     * @return true if it has class.
     */
    public boolean hasAssessClass(String assess){
        if(assess.equalsIgnoreCase(assessClass1) || assess.equalsIgnoreCase(assessClass2) || assess.equalsIgnoreCase(assessClass3)){
            return true;
        }
        return false;
    }
}
