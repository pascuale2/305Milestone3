package view;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.*;

/**
 * /**
 * This program takes a csv filename from the user containing data on property 
 * assessments and stores the data in a list of PropertyAssessment objects, 
 * where each object contains the data for a single property assessment. The 
 * program can:
 * 
 * - display the statistics for the entire csv file
 * Ex. Descriptive statistics of all property assessments
 *     size = 407192
 *     min = $0
 *     max = $1,266,911,500
 *     range = $1,266,911,500
 *     mean = $456,647
 *     stdev = $3,574,901
 *     median = $313,000
 * 
 * - search for a user specified neighbourhood and display that neighbourhood's 
 * statistics
 * Ex. Neighbourhood: webber greens
 *     Statistics (neighbourhood = webber greens)
 *     size = 965
 *     min = $88,500
 *     max = $34,617,500
 *     range = $34,529,000
 *     mean = $468,721
 *     stdev = $1,230,573
 *     median = $397,000
 *
 * - search for a user specified assessment class, and display all the statistics
 * associated with that assessment class. 
 * Ex. Assessment Class: residential
 *     Statistics (assessment class = residential)
 *     size = 380384
 *     min = $0
 *     max = $101,062,000
 *     range = $101,062,000
 *     mean = $315,494
 *     stdev = $344,662
 *     median = $312,000
 * 
 * - search for a user specified account number, among the property assessments.
 * - search for a user specified address among the property assessments.
 * - As well as any combination of the 4 search values being: account number,
 * address, neighbourhood and assessment class.
 * 
 * Statistics are calculated based on the assessed value of each property 
 * assessment.
 * 
 * This is the UI for the program JavaFX class, which is used to set and 
 * display the user interface.
 * 
 * @author Jason
 * 
 * Ref's:
 * https://www.youtube.com/watch?v=VaAu-6geAnA
 * https://www.youtube.com/watch?v=vGTMZ-wDGpY
 * https://www.tutorialspoint.com/static-import-the-math-class-methods-in-java
 * https://www.tutorialspoint.com/static-import-the-math-class-methods-in-java
 * https://www.geeksforgeeks.org/math-pow-method-in-java-with-example/
 * https://www.geeksforgeeks.org/arraylist-get-method-java-examples/
 * https://docs.oracle.com/javase/8/docs/api/java/util/List.html
 * https://www.javatpoint.com/how-to-read-csv-file-in-java
 * https://www.journaldev.com/787/java-sort-list
 * https://docs.oracle.com/javase/6/docs/api/java/text/DecimalFormat.html
 * https://www.geeksforgeeks.org/iterate-map-java/
 * https://www.w3schools.com/java/java_user_input.asp
 * https://stackoverflow.com/questions/11001720/get-only-part-of-an-array-in-java
 * https://www.w3schools.com/java/java_hashmap.asp
 */
public class MS2FX extends Application{
    private AnchorPane anchorPane;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    /**
     * start - start method override, contains the methods to display the 
     * UI
     * 
     * @param primaryStage
     * @throws Exception 
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Edmonton Property Assessments");
        initRootLayout();
        
        Scene scene = new Scene(anchorPane);
        primaryStage.setScene(scene);
        primaryStage.show();    
    }
    /**
     * initRootLayout- Method to set the FMXL loader and set the root for
     * UI display in start method.
     */
    private void initRootLayout() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Lab6UIController.class.getResource("MS2UI.fxml"));
        try {
            anchorPane = (AnchorPane)loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
