/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmpt305.milestone2;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The main program that launches a window and loads the table
 * from the file "Property_Assessment_Data.csv" which is coded 
 * inside of FXMLController
 * 
 * @author Laroy Milton
 */
public class Milestone2 extends Application {
    /**
     * required to launch the window
     * 
     * @param primaryStage
     * @throws Exception 
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Edmonton Property Assesment");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * main function that launches a table and form
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
