/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Milestone3;

import javafx.collections.ObservableList;

/**
 * Neighbourhood extends PropertyHandler, it is essentially the same as 
 *  property handler, except that it filters all but properties that
 *  match the neighbourhood input string
 * @author Korey Sniezek
 * @version 1.0
 * @since MS1
 */
public class Neighbourhood extends PropertyHandler {
    private String neighbourhoodName;
    /**
     * Neighbourhood constructor takes a PropertyHandler and a string, it 
     *  creates a new PropertyHandler containing only properties in that
     *  neighbourhood
     *      Version 2.0 updated allProperties to be an ObservabeList
     * @param parent
     * @param targetNeighbourhood 
     * @author Korey Sniezek
     * @version 2.0
     * @since MS1
     * 
     */
    public Neighbourhood (PropertyHandler parent, String targetNeighbourhood) {
        super();
        
        
       
        ObservableList <Property> allProperties = parent.getAllProperties();
        
        for(int i = 0; i < allProperties.size(); i++){
            if(allProperties.get(i).getNeighbourhood().equalsIgnoreCase(targetNeighbourhood)){
                
                properties.add(allProperties.get(i));
            }
        }
        if(!properties.isEmpty()){
            neighbourhoodName = properties.get(0).getNeighbourhood();
        }
       
    }
    
    /**
     * The purpose of this method is to get the name of the neighbourhood
     * @author Korey Sniezek
     * @version 1.0
     * @since MS1
     * @return String neighbourhood
     */
    public String getName(){
        return neighbourhoodName;
    }
}
