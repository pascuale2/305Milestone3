package view;

import MS2.Getter;
import MS2.Address;
import MS2.PropertyAssessment;
import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;

/**
 * FXML Controller class
 *
 * @author Jason
 * Refs:
 *      - https://www.youtube.com/watch?v=S_JN7zO12H4&t=28s
 *      - https://www.youtube.com/watch?v=uh5R7D_vFto
 *      - https://stackoverflow.com/questions/42512513/adding-event-listener-to-mainscene-in-javafx-using-fxml
 */
public class Lab6UIController implements Initializable, EventHandler {
    @FXML 
    private TableView<PropertyAssessment> tableView;
    @FXML 
    private TableColumn<PropertyAssessment, Integer> accountColumn;
    @FXML
    private TableColumn<PropertyAssessment, Address> addressColumn;
    @FXML 
    private TableColumn<PropertyAssessment, String> valueColumn;
    @FXML 
    private TableColumn<PropertyAssessment, String> assessedClassColumn;
    @FXML 
    private TableColumn<PropertyAssessment, String> nbrhdColumn;
    @FXML 
    private TableColumn<PropertyAssessment, String> latitudeColumn;
    @FXML 
    private TableColumn<PropertyAssessment, String> longitudeColumn;
    @FXML 
    private TextField acctNum;
    @FXML 
    private TextField address;
    @FXML 
    private TextField nbrhd;
    @FXML 
    private ComboBox assessmentClass;
    @FXML 
    private TextArea stats;
    @FXML
    private Button search;
    @FXML
    private Button reset;
    private Set<String> assessmentClasses;
    private ObservableList<PropertyAssessment> observeLS;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        accountColumn.setCellValueFactory(new PropertyValueFactory<>("AcctNumber"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("Address"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("Val"));
        assessedClassColumn.setCellValueFactory(new PropertyValueFactory<>("AClass"));
        nbrhdColumn.setCellValueFactory(new PropertyValueFactory<>("Nbrhd"));
        latitudeColumn.setCellValueFactory(new PropertyValueFactory<>("Lat"));
        longitudeColumn.setCellValueFactory(new PropertyValueFactory<>("Long"));
        observeLS = Observable.getObservableLs();
        tableView.setItems(observeLS);
        
        assessmentClasses = Getter.getAssessClass();
        assessmentClass.getItems().addAll(assessmentClasses);
        assessmentClass.setEditable(true);
        stats.setEditable(false);
        stats.setText(Observable.getOLSStats(observeLS));
        
        search.setOnAction(this);
        reset.setOnAction(this);
        
        /* Event handler for Enter key pressed on textfields */
        EventHandler<KeyEvent> ke = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) { 
               if (ke.getCode() == KeyCode.ENTER) {
                   handleSearch(observeLS);
               }
            } 
        };
        acctNum.setOnKeyPressed(ke);
        address.setOnKeyPressed(ke);
        nbrhd.setOnKeyPressed(ke);
        assessmentClass.setOnKeyPressed(ke);    
    }
    /**
     * handle - overrides the handle function to add functionality to the 
     * search and reset buttons for the UI.
     * 
     * @param t - Event, typically a mouse click
     */
    @Override
    public void handle(Event t) {
        if (t.getSource() == search){
            handleSearch(tableView.getItems());
        }
        else if (t.getSource() == reset){
            handleReset();
        }
    }
    /**
     * handleSearch - helper function to display statistics and search results
     * on the UI when the user has hit the search button or pressed the Enter
     * key on a textField. Function adds all search parameters to a set, and
     * uses this set to filter results from the Observable list passed in.
     * 
     * @param pAssessment - and observable list of Property Assessment objects
     */
    @FXML
    public void handleSearch(ObservableList<PropertyAssessment> pAssessment){
        Set<String> searchValues = new HashSet<>();
        searchValues.add(acctNum.getCharacters().toString());
        searchValues.add(address.getCharacters().toString().toUpperCase());
        searchValues.add((nbrhd.getCharacters().toString()).toUpperCase());
        searchValues.add(assessmentClass.getEditor().getCharacters().toString());
        searchValues.remove("");
        pAssessment = Observable.editObservable(searchValues, pAssessment);
        tableView.setItems(pAssessment);
        stats.clear();
        if (pAssessment.size() > 1) {
            stats.setText(Observable.getOLSStats(pAssessment));
        }
    }
    /**
     * handleReset - helper function to handle when the user clicks the reset
     * button. Resets the tableView display and clears the textFields and 
     * textArea's.
     */
    @FXML
    public void handleReset(){
        acctNum.clear();
        address.clear();
        nbrhd.clear();
        assessmentClass.getItems().clear();
        assessmentClass.getEditor().clear();
        assessmentClass.getItems().addAll(assessmentClasses);
        stats.clear();
        stats.setText(Observable.getOLSStats(observeLS));;
        tableView.setItems(observeLS);
    }
}
