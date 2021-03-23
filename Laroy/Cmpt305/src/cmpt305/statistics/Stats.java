package cmpt305.statistics;

import java.util.*;
import java.util.Collections;
import java.text.NumberFormat;

/**
 * This contains statistics methods
 *
 * @author Laroy Milton
 */
public class Stats {
   
    /**
     * printStats -- prints the statistics of a given list
     * 
     * @param data the list of doubles for statistical analysis
     */
    public static void printStats(List<Double> data){
        NumberFormat fmt = NumberFormat.getCurrencyInstance();
        fmt.setMaximumFractionDigits(0);
        
        System.out.println("n = " + n(data));
        System.out.println("min = " + fmt.format(min(data)));
        System.out.println("max = " + fmt.format(max(data)));
        System.out.println("range = " + fmt.format(range(data)));
        System.out.println("mean = " + fmt.format(mean(data)));
        System.out.println("sd = " + fmt.format(stdev(data)));
        System.out.println("median = " + fmt.format(median(data)));
    }
    
    /**
     * stringStats -- turns statistics of a given list into a string
     * 
     * @param data the list of doubles for statistical analysis
     * @return the formatted string of the statistics 
     */
    public static String stringStats(List<Double> data){
        NumberFormat fmt = NumberFormat.getCurrencyInstance();
        fmt.setMaximumFractionDigits(0);
        StringBuilder str = new StringBuilder();
        
        str.append("Statistics of assesed Values:\n");
        str.append("\nn = ").append(n(data));
        str.append("\nmin = ").append(fmt.format(min(data)));
        str.append("\nmax = ").append(fmt.format(max(data)));
        str.append("\nrange = ").append(fmt.format(range(data)));
        str.append("\nmean = ").append(fmt.format(mean(data)));
        str.append("\nsd = ").append(fmt.format(stdev(data)));
        str.append("\nmedian = ").append(fmt.format(median(data)));
        
        return str.toString();
    }

    /**
     * n -- returns the number of elements in a list of doubles
     * 
     * @param data a list of doubles to count
     * @return number of elements in the list as an integer
     */
    static int n (List<Double> data){
        return data.size();
    }
    
    /**
     * min -- returns the minimum value in a list of doubles
     * 
     * @param data a list of doubles
     * @return the minimum value in the list as a double
     */
    static double min(List<Double> data){
        List<Double> sortedData = new ArrayList<>(data);
        Collections.sort(sortedData);
        return sortedData.get(0);
    }
    
    /**
     * max -- returns the maximum value in a list of doubles
     * 
     * @param data a list of doubles
     * @return the minimum value in the list as a double
     */
    static double max(List<Double> data){
        List<Double> sortedData = new ArrayList<>(data);
        Collections.sort(sortedData);
        return sortedData.get(sortedData.size() - 1);
    }
    
    /**
     * range -- returns the statistical range of values in a list of doubles
     * 
     * @param data a list of doubles
     * @return the range in the list as a double
     */
    static double range(List<Double> data){
        return max(data) - min(data);
    }
    
    /**
     * mean -- returns the statistical mean given a list of doubles
     * 
     * @param data a list of doubles
     * @return the mean as a double
     */
    static double mean(List<Double> data){
        double sum = 0;
        for (Double data1 : data) {
            sum += data1;
        }
        return sum/n(data);
    }
    
    /**
     * stdev -- returns the standard deviation given a list of doubles
     * 
     * @param data a list of doubles
     * @return the standard deviation as a double
     */
    static double stdev(List<Double> data){
        double stdErr = 0;
        double m = mean(data);
        for (Double data1 : data) {
            stdErr += Math.pow(data1 - m, 2);
        }
        return Math.sqrt(stdErr / n(data));
    }
    
    /**
     * median -- the statistical median given a list of doubles
     * 
     * @param data a list of doubles
     * @return the median as a double
     */
    static double median(List<Double> data){
        List<Double> sortedData = new ArrayList<>(data);
        Collections.sort(sortedData);
        
        double med;
        double length = n(data);
        
        int mid = (int) Math.floor(length / 2);
        if (length % 2 == 0) {
            
            med = (sortedData.get(mid) + sortedData.get(mid - 1)) / 2;
        } else {
            med = sortedData.get(mid);
        }
        return med;
    }
}
