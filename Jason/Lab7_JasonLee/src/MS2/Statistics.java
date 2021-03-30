package MS2;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class counts the number of entries in the list (# of assessed houses),
 * the minimum and maximum assessed value, the range between the values, and the
 * mean, median and standard deviation of the data in the list.
 *
 * @author Jason
 */
public class Statistics {
    /**
     * n - returns the size of the list of data (the # of assessed values)
     * 
     * @param data List of doubles searched through for the minimum value
     * @return 
     */
    public static int n(List<Double> data) {
        int n = data.size();
        return n;
    }
    /**
     * min - returns the minimum value in the list of doubles, data
     * 
     * @param data List of doubles searched through for the minimum value
     * @return 
     */
    public static double min(List<Double> data) {
        double min = data.get(0);
        for (int i = 1; i < data.size(); i++){
            if (data.get(i) < min){
                min = data.get(i);
            }
        }
        return min;
    }
    /**
     * max - returns the maximum value in the list of doubles, data
     * 
     * @param data List of doubles searched through for the minimum value.
     * @return 
     */
    public static double max(List<Double> data) {
        double maxValue = data.get(0);
        for (int i = 1; i < data.size(); i++){
            if (data.get(i) > maxValue){
                maxValue = data.get(i);
            }
        }
        return maxValue;
    }
    /**
     * range - returns the difference between the minimum and maximum values in
     * the list, data.
     * 
     * @param data
     * @return 
     */
    public static double range(List<Double> data) {
       double maxValue = max(data);
       double minValue = min(data);
       double range = maxValue - minValue;
       return range;
    }
    /**
     * mean - returns the mean of data in the list of doubles, data.
     * 
     * @param data List of doubles searched through for the minimum value.
     * @return 
     */
    public static double mean(List<Double> data) {
        double sum = 0;
        for (int i = 0; i < data.size(); i++) {
            sum += data.get(i);
        }
        double meanValue = (double)sum / data.size();
        return meanValue;
    }
    /**
     * stdev - returns the standard deviation of the data in the list, data.
     * 
     * @param data List of doubles searched through for the minimum value.
     * @return 
     */
    public static double stdev(List<Double> data) {
        double avg = mean(data);
        double sum = 0;
        for (int i = 0; i < data.size(); i++) {
            sum += Math.pow(Math.abs(data.get(i) - avg), 2);
        }
        double sDev = Math.sqrt(sum/data.size());
        return sDev;
    }
    /**
     * median - returns the median of the data in the list, data, after it
     * has been sorted.
     * 
     * @param data List of doubles searched through for the minimum value.
     * @return 
     */
    public static double median(List<Double> data) {
        Collections.sort(data);
        int mid = data.size()/ 2;
        if (data.size() % 2 == 0){
            double med = (data.get(mid - 1) + data.get(mid)) / 2;
            return med;
        }
        else {
            double med = data.get(mid);
            return med;
        }
    }
    /**
     * printStatistics - prints a summary of the calculated statistics for a 
     * list of data containing property assessment values.
     * 
     * @param data - the list of assessed value's to be used in statistics 
     * calculations
     */
    public static void printStatistics(List<Double> data){
        DecimalFormat df = new DecimalFormat("#,###");
        
        System.out.println("size = " + Statistics.n(data));
        System.out.println("min = $" + df.format((int)Statistics.min(data)));
        System.out.println("max = $" + df.format((long)Statistics.max(data)));
        System.out.println("range = $" + df.format((long)Statistics.range(data)));
        System.out.println("mean = $" + df.format((long)Statistics.mean(data)));
        System.out.println("stdev = $" + df.format((long)Statistics.stdev(data)));
        System.out.println("median = $" + df.format((long)Statistics.median(data)));
    }
    /**
     * getStatistics - returns a list of strings to display all the statistics
     * for a list of doubles from a Property Assessment object
     * 
     * @param data - list of doubles from a Property Assessment object
     * @return - list of strings to display statistics
     */
    public static List<String> getStatistics(List<Double> data){
        List<String> stats = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("#,###");
        
        stats.add(String.valueOf(Statistics.n(data)));
        stats.add(df.format((long)Statistics.min(data)));
        stats.add(df.format((long)Statistics.max(data)));
        stats.add(df.format((long)Statistics.range(data)));
        stats.add(df.format((long)Statistics.mean(data)));
        stats.add(df.format((long)Statistics.stdev(data)));
        stats.add(df.format((long)Statistics.median(data)));
        
        return stats;
    }
}
