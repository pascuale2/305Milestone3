/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propertyassessmentapp;

import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import static propertyassessmentapp.PropertyData.*;

/**
 * FXML Controller class
 *
 * @author Erwin Pascual
 */
public class FXMLController implements Initializable {
    private final String fileName = "Property_Assessment_Data.csv";
    
    // Text Field Variables
    @FXML private TextField accountNumField;
    @FXML private TextField addressField;
    @FXML private TextField neighbourhoodField;
    
    // ComboBox Variable
    @FXML private ComboBox assClassComboBox;
    
    // TextArea Vairable
    @FXML private TextArea statisticsTextArea;  
    
    // Table View Variables
    @FXML private TableView<PropertyData> propertyData;
    @FXML private TableColumn<PropertyData, Integer> account;
    @FXML private TableColumn<PropertyData, String> address;
    @FXML private TableColumn<PropertyData, String> assVal;
    @FXML private TableColumn<PropertyData, Integer> assClass1;
    @FXML private TableColumn<PropertyData, String> neighborhood;
    @FXML private TableColumn<PropertyData, Double> latitude;
    @FXML private TableColumn<PropertyData, Double> longitude;
    
    // Observable list for Table View
    private final ObservableList<PropertyData> list = FXCollections.observableList(getData(fileName));
    
    /**
     * displayResults - displays the results of the search query when the search
     * button is clicked
     * 
     * @param event 
     */
    @FXML void displayResults(ActionEvent event) {
        Set<PropertyData> accountResults = new HashSet<>();
        Set<PropertyData> addressResults = new HashSet<>();
        Set<PropertyData> neighborhoodResults = new HashSet<>();
        Set<PropertyData> comboBoxResults = new HashSet<>();
        
        Set<PropertyData> combinedResults = new HashSet<>();
        
        // If the Account Number Field isn't empty
        if (accountNumField.getText().length() != 0){
            accountResults.addAll(getSimilarAccount(accountNumField.getText(), list));
            combinedResults.addAll(accountResults);
        }
        // If the Address Field isn't empty
        if (addressField.getText().length() != 0){
            addressResults.addAll(getSimilarAddress(addressField.getText(), list));
            combinedResults.addAll(addressResults);
        }
        // If the Neighbourhood Field isn't empty
        if (neighbourhoodField.getText().length() != 0){
            neighborhoodResults.addAll(getSimilarNeighborhood(neighbourhoodField.getText(), list));
            combinedResults.addAll(neighborhoodResults);
        }
        // If the Assessment Class Combo Box isn't empty
        if (assClassComboBox.getValue() != null){
            comboBoxResults.addAll(getSimilarClasses(assClassComboBox.getValue().toString(), list));
            combinedResults.addAll(comboBoxResults);
        }
        
        /**
         * This block of if statements are to intersect common values between multiple
         * Search Results.
         */
        if (assClassComboBox.getValue() != null){
            // Intersects values from combo box results
            combinedResults.retainAll(comboBoxResults);
        }
        if (neighbourhoodField.getText().length() != 0){
            // Intersects values from neighbourhood field results
            combinedResults.retainAll(neighborhoodResults);
        }
        if (addressField.getText().length() != 0){
            // Intersects values from address field results
            combinedResults.retainAll(addressResults);
        }
        if (accountNumField.getText().length() != 0){
            // Intersects values from account number field results
            combinedResults.retainAll(accountResults);
        }
        
        // If the combined results of the 4 input fields aren't empty
        if (!combinedResults.isEmpty()){
            ObservableList<PropertyData> newList = FXCollections.observableList(new ArrayList<>(combinedResults));
            updateTable(newList);
        } 
        else {
            // If one of the search boxes aren't empty or the combobox isn't null
            if (accountNumField.getText().length() != 0 || 
                addressField.getText().length() != 0 ||
                neighbourhoodField.getText().length() != 0 ||
                assClassComboBox.getValue() != null)
            {
                ObservableList<PropertyData> emptyList = FXCollections.observableList(new ArrayList<>(combinedResults));
                updateTable(emptyList);
            }
        }
    }
    
    /**
     * resetTable - resets the values of the input text fields and resets the values
     * in the Table View
     * 
     * @param event 
     */
    @FXML void resetTable(ActionEvent event) {
        // Reset Text Fields
        accountNumField.setText("");
        addressField.setText("");
        neighbourhoodField.setText("");
        
        // Reset Text Area
        statisticsTextArea.setText("");
        
        // Reset Combobox
        assClassComboBox.setValue(null);
        
        // Resets Combobox
        assClassComboBox.getItems().clear();
        assClassComboBox.getItems().addAll(getDistinctClasses(list));
        
        // Reset TableView
        propertyData.setItems(list);
        
        // Calculates the statistics for the whole list
        statisticsTextArea.setText(getAssessmentStats(list));
    }
    
    /**
     * updateTable - updates the table with the user inputted fields and displays
     * statistics if there is more than one entry on the table
     * 
     * @param newList 
     */
    public void updateTable(ObservableList<PropertyData> newList){
        propertyData.setItems(newList);
        
        // Gets the last selection of the comboBox
        Object lastSearch = assClassComboBox.getValue();
        assClassComboBox.getItems().clear();
        // Auto-populates the Combobox
        assClassComboBox.getItems().addAll(getDistinctClasses(newList));
        assClassComboBox.getSelectionModel().select(lastSearch);
        
        if (newList.size() > 1){
            statisticsTextArea.setText(getAssessmentStats(newList));
        } else {
            statisticsTextArea.setText("Not enough entries to calculate statistics");
        }
    }
    
    /**
     * initialize - initializes the parameters such as the Table View and Combo Box
     * 
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Populates the TableView with data from the ObservableList
        account.setCellValueFactory(new PropertyValueFactory<>("account"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        // Formats the Assessed Value with currency notation
        assVal.setCellValueFactory(cellData ->{
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            formatter.setMaximumFractionDigits(0);
            String formattedValue = formatter.format(cellData.getValue().getAssVal());
            return new SimpleStringProperty(formattedValue);
        });
        assClass1.setCellValueFactory(new PropertyValueFactory<>("assClass1"));
        neighborhood.setCellValueFactory(new PropertyValueFactory<>("neighborhood"));
        latitude.setCellValueFactory(new PropertyValueFactory<>("latitude"));
        longitude.setCellValueFactory(new PropertyValueFactory<>("longitude"));
        
        // Populates the Table View with the Observable list items 
        propertyData.setItems(list);
        
        // Calculates the statistics for the whole list
        statisticsTextArea.setText(getAssessmentStats(list));
        
        // Populates the Combo Box with distinct items from the Observable list
        assClassComboBox.getItems().addAll(getDistinctClasses(list));
    }

    
}
