/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Milestone3;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * --Milestone3
 * @purpose Implements all changes needed since MS1, including GUI
 * @author Korey Sniezekj
 * @since MS2
 * @version 1.0
 */
public class Milestone3 extends Application{
    public static PropertyHandler handler;
    
    /**
     * --start
     * @purpose starts the scene and loads initial specifications from XML
     * @author Korey Sniezek
     * @since MS2
     * @version 1.0
     * @param primaryStage
     * @throws IOException 
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Demo.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setTitle("Edmonton Property Assessments");
        primaryStage.setScene(scene);
        
        primaryStage.show();
    }

    /**
     * --main
     * @purpose main program
     * @author Korey Sniezek
     * @since MS2
     * @version 2.0
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Boolean readFromUrl = true;
        try {
            // comment out line 60 and uncomment 61 to load from database
            handler = new PropertyHandler("Property_Assessment_Data.csv", !readFromUrl);
            //handler = new PropertyHandler("https://data.edmonton.ca/api/views/q7d6-ambg/rows.csv?accessType=DOWNLOAD&api_foundry=true", readFromUrl);
        } catch (IOException ex) {
            Logger.getLogger(Milestone3.class.getName()).log(Level.SEVERE, null, ex);
        }
        launch(args);
    }
    
}
