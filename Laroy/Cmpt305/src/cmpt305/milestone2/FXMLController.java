package cmpt305.milestone2;

import cmpt305.propertyData.Property;
import cmpt305.propertyData.Address;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import static cmpt305.propertyData.Property.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class in charge of handling the window
 *
 * @author Laroy Milton
 */
public class FXMLController implements Initializable {
    @FXML private TextField accNumTextBox;
    @FXML private TextField addressTextBox;
    @FXML private TextField neighbourTextBox;
    @FXML private TextArea displayBox;
    @FXML private ComboBox<String> assesOpt;
    @FXML private Button searchButton;
    @FXML private Button resetButton;
    @FXML private TableView<Property> table;
    @FXML private TableColumn<Property, Integer> account;
    @FXML private TableColumn<Property, Address> address;
    @FXML private TableColumn<Property, String> value;
    @FXML private TableColumn<Property, String> assesClass;
    @FXML private TableColumn<Property, String> neighbourhood;
    @FXML private TableColumn<Property, Double> latitude;
    @FXML private TableColumn<Property, Double> longitude;
    
    List<Property> data = getData("Property_Assessment_Data.csv");
    
    ObservableList<Property> obData = FXCollections.observableList(data); 

    /**
     * Initializes the controller class by connecting the data to the table
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        account.setCellValueFactory(new PropertyValueFactory<Property, Integer>("account"));
        address.setCellValueFactory(new PropertyValueFactory<Property, Address>("address"));
        
        //https://edencoding.com/tableview-customization-cellfactory/
        // format assesment value using a lambda function
        value.setCellValueFactory(cell ->{
            NumberFormat fmt = NumberFormat.getCurrencyInstance(); 
            fmt.setMaximumFractionDigits(0);
            String value = fmt.format(cell.getValue().getValue());
            return new SimpleStringProperty(value);});
        assesClass.setCellValueFactory(new PropertyValueFactory<Property, String>("assesClass"));
        neighbourhood.setCellValueFactory(new PropertyValueFactory<Property, String>("neighbourhood"));
        latitude.setCellValueFactory(new PropertyValueFactory<Property, Double>("latitude"));
        longitude.setCellValueFactory(new PropertyValueFactory<Property, Double>("longitude")); 
//        table.setItems(obData);
//        
//        if (obData.size() > 1) {
//            displayBox.setText(stringPropertyStats(obData));
//        } else {
//            displayBox.setText("");
//        }
        updateDisplay(obData);
    }
    
    /**
     * This updates the table and text field when given an ObserableList
     * 
     * @param list the ObservableList to onSearch on the 
     */
    private void updateDisplay(ObservableList<Property> list) {
        String lastComboResult = assesOpt.getValue();
        assesOpt.getItems().clear();
        
        table.setItems(list);
                
        if (list.size() > 1) {
            displayBox.setText(stringPropertyStats(list));
        } else {
            displayBox.setText("");
        }

        assesOpt.getItems().addAll(getAllAssesClasses(list));
        assesOpt.getSelectionModel().select(lastComboResult);
    }
    
    /**
     * When the search button is pressed, this updates the table based on
     * the given search results.
     * 
     * @param event when search button is pressed
     */
    @FXML
    void onSearch(ActionEvent event) {
        Set<Property> accNumSet = new HashSet<>();
        Set<Property> addrSet = new HashSet<>();
        Set<Property> neighbourSet = new HashSet<>();
        Set<Property> assesSet = new HashSet<>();
        Set<Property> intersectionSet = new HashSet<>();


        if (!accNumTextBox.getText().isEmpty()) {
        accNumSet.addAll(getPropertyByAccount(data, Integer.parseInt(accNumTextBox.getText())));
        intersectionSet.addAll(getPropertyByAccount(data, Integer.parseInt(accNumTextBox.getText())));
        }

        if (!addressTextBox.getText().isEmpty()) {
        addrSet.addAll(getPropertyByAddress(data, addressTextBox.getText())); 
        intersectionSet.addAll(getPropertyByAddress(data, addressTextBox.getText())); 
        }
        
        if (!neighbourTextBox.getText().isEmpty()) {
        neighbourSet.addAll(getNeighborhoodData(data, neighbourTextBox.getText()));
        intersectionSet.addAll(getNeighborhoodData(data, neighbourTextBox.getText()));
        }
        
        if (assesOpt.getValue() != null) {
            assesSet.addAll(getAssesClassData(data, assesOpt.getValue()));
            intersectionSet.addAll(getAssesClassData(data, assesOpt.getValue()));
        } 
        
        // Intersection of the results
        if (assesOpt.getValue() != null) {
            intersectionSet.retainAll(assesSet);
        }
        if (!neighbourTextBox.getText().isEmpty()) {
            intersectionSet.retainAll(neighbourSet);
        }
        if (!addressTextBox.getText().isEmpty()) {
            intersectionSet.retainAll(addrSet);
        }
        if (!accNumTextBox.getText().isEmpty()) {
            intersectionSet.retainAll(accNumSet);
        }
        
        List<Property> newData = new ArrayList<>(intersectionSet);
        // If there are no search parameters in the field, then don't update the list
        if (!(accNumTextBox.getText().isEmpty() && addressTextBox.getText().isEmpty() && neighbourTextBox.getText().isEmpty() && assesOpt.getValue() == null)) {
            ObservableList<Property> list = FXCollections.observableList(newData);
            updateDisplay(list);
        }
    }
    
    /**
     * This resets the table and input fields to default
     * 
     * @param event When the onReset button is pressed
     */
    @FXML
    void onReset(ActionEvent event) {
        accNumTextBox.clear();
        addressTextBox.clear();
        neighbourTextBox.clear();
        assesOpt.getItems().clear();

        updateDisplay(obData);
    }
}
