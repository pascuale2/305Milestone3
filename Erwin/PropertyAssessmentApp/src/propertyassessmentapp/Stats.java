/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propertyassessmentapp;

import java.util.*;
import java.text.*;


/**
 *
 * @author Erwin Pascual
 */
public class Stats {
    public static String printStringStats (List<Double> listStats){
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        
        return 
            String.format("Statistics of Assessed Values: ") +
            String.format("\n\nNumber of properties: %d", n(listStats)) +
            String.format("\nMin: %s", formatter.format((int) min(listStats))) +
            String.format("\nMax: %s", formatter.format((int) max(listStats))) +
            String.format("\nRange: %s", formatter.format((int) range(listStats))) +
            String.format("\nMean: %s", formatter.format((int) mean(listStats))) + 
            String.format("\nMedian: %s", formatter.format((int) median(listStats))) +
            String.format("\nStandard deviation: %s", formatter.format((int) stdev(listStats)));

    }

    /**
     * printStats - prints the descriptive statistics of the specified List of
     * Double columns we give it
     * 
     * @param listStats a List of Doubles that we want to display statistics on
     */
    public static void printStats (List<Double> listStats){
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        
        System.out.println();
        System.out.printf("N = %d", n(listStats));
        System.out.printf("\nmin = %s", formatter.format((int) min(listStats)));
        System.out.printf("\nmax = %s", formatter.format((int) max(listStats)));
        System.out.printf("\nrange = %s", formatter.format((int) range(listStats)));
        System.out.printf("\nmean = %s", formatter.format((int) mean(listStats)));
        System.out.printf("\nstdev = %s", formatter.format((int) stdev(listStats)));
        System.out.printf("\nmedian = %s", formatter.format((int) median(listStats)));
        System.out.println();
    }
    
    /**
     * n - returns the size of the total entries in the data
     * 
     * @param data which is a List of Doubles that we want to get the size of
     * @return the size of the List
     */
    public static int n(List<Double> data) {
        return data.size();
    }
    
    /**
     * min - returns the minimum values of the elements in the data
     * 
     * @param data which is a List of Doubles that we want to get the min of
     * @return the min of all the elements in the List of Doubles 
     */
    public static double min(List<Double> data){
        List<Double> sortedData = new ArrayList<>(data);
        Collections.sort(sortedData);
        return sortedData.get(0);
    }
    
    /**
     * max - returns the maximum values of the elements in the data
     * 
     * @param data which is a List of Doubles that we want to get the max of
     * @return the max of all the elements in the List of Doubles 
     */
    public static double max(List<Double> data){
        List<Double> sortedData = new ArrayList<>(data);
        Collections.sort(sortedData);
        return sortedData.get(sortedData.size() - 1);
    }
    
    /**
     * max - returns the difference between the max value and minimum value
     * of the data
     * 
     * @param data which is a List of Doubles that we want to get the range of
     * @return the range of the difference between max and min in the List of 
     * Doubles 
     */
    public static double range(List<Double> data){
        return max(data) - min(data);
    }
    
    /**
     * mean - returns the average values of all the elements in the data
     * 
     * @param data which is a List of Doubles that we want to get the avg of
     * @return the average of all the elements in the List of Doubles 
     */
    public static double mean(List<Double> data){
        double sum = 0;
        for (Double elem : data){
            sum += elem;
        }
        return sum/n(data);
    }
    
    /**
     * stdev - returns the standard deviation of all the elements in the data
     * 
     * @param data which is a List of Doubles that we want to get the stdev of
     * @return the stdev of all the elements in the List of Doubles 
     */
    public static double stdev(List<Double> data){
        double stderr = 0;
        double mean = mean(data);
        for (Double elem : data){
            stderr += Math.pow(elem - mean,2);
        }
        return Math.sqrt(stderr/n(data));
    }
    
    /**
     * median - returns the midpoint of all the sorted elements in the data.
     * if number of elements in data are odd it takes the midpoint.
     * if number of elements in data are even it takes the two midpoint values
     * and takes the average of them
     * 
     * @param data which is a List of Doubles that we want to get the median of
     * @return the median of the elements in the List of Doubles 
     */
    public static double median(List<Double> data){
        List<Double> sortedData = new ArrayList<>(data);
        Collections.sort(sortedData);
        int midpoint = (int) Math.floor(n(sortedData)/2);
        // if size of elemts in data are even
        if (n(sortedData)%2 == 0){
            return (sortedData.get(midpoint - 1) + sortedData.get(midpoint))/2;
        }
        return sortedData.get(midpoint);
    }
}
