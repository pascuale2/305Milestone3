/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Milestone2;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * --Milestone2
 * @purpose Implements all changes neede since MS1, including GUI
 * @author Korey Sniezekj
 * @since MS2
 * @version 1.0
 */
public class Milestone2 extends Application{
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
        primaryStage.setMaximized(true);
    }

    /**
     * --main
     * @purpose main program
     * @author Korey Sniezek
     * @since MS2
     * @version 1.0
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // initialize property handler
        handler = new PropertyHandler("Property_Assessment_Data.csv");
        
        launch(args);
    }
    
}
