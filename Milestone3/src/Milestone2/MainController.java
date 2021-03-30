/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Milestone2;

import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * FXML Controller class
 *
 * @author Korey Sniezek
 * @purpose Acts as interface between Demo.fxml and the backend
 * @since MS2
 * @version 1.0
 */
public class MainController implements Initializable {
    private PropertyHandler handler;
    @FXML
    private TextField addressBox;
    @FXML
    private TextField neighbourhoodBox;
    @FXML
    private TextField accountBox;
    @FXML
    private ComboBox<String> assessmentMenu;
    @FXML
    private Button searchBtn;
    @FXML
    private Button resetBtn;
    @FXML
    private TextArea statText;
    @FXML
    private TableView<Property> table;
    @FXML
    private TableColumn<Property, Integer> accountCol;
    @FXML
    private TableColumn<Property, String> addressCol;
    @FXML
    private TableColumn<Property, Integer> assessedCol;
    @FXML
    private TableColumn<Property, String> assClassCol;
    @FXML
    private TableColumn<Property, String> nCol;
    @FXML
    private TableColumn<Property, Double> latCol;
    @FXML
    private TableColumn<Property, Double> longCol;
    @FXML
    private TableColumn<Property, String> assClassCol2;
    @FXML
    private TableColumn<Property, String> assClassCol3;
    @FXML
    private BarChart bChart;
    @FXML
    private PieChart piechart;
    @FXML
    private ChoiceBox wardSelect;
    @FXML
    private Button go;
    @FXML
    private TabPane tab;
    
    
    
    /**
     * --onEnter
     * @purpose Allows user to press return key instead of clicking the search
     *    button
     * @author Korey Sniezek
     * @since MS2
     * @version 1.0
     * @param ae 
     */
    @FXML
    public void onEnter(ActionEvent ae){
        searchBtnHandler();
    }
    /**
     * Initializes the controller class.
     * @author Korey Sniezek
     * @since MS2
     * @version 1.0
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        handler = Milestone2.handler;
        resetTable();
        setBChartData();
        
        
        initAssessmentClasses(handler);
        initWardSelect();
    }
    
    /**
     * --initAssessmentClasses
     * @purpose helper function to initialize for setting the comboBox items
     * @author Korey Sniezek
     * @since MS2
     * @version 1.0
     * @param properties (Property handler used by this class)
     */
    private void initAssessmentClasses(PropertyHandler properties){
        HashSet<String> assessment = new HashSet();
        for(int i = 0; i < properties.getAllProperties().size(); i++){
            Property property = properties.getAllProperties().get(i);
            for(int j = 0; j < property.getClasses().size(); j++){
                assessment.add(property.getClasses().get(j));
            }
        }
        ObservableList<String> list = FXCollections.observableArrayList();
        Iterator<String> iterator = assessment.iterator();
        while(iterator.hasNext()){
            list.add(iterator.next());
        }
        assessmentMenu.setItems(list);
        
    }
    private void initWardSelect(){
        ObservableList<String> list = FXCollections.observableArrayList();
        for (int i = 1; i < 13; i++){
            list.add("Ward " + i);
        }
        wardSelect.setItems(list);
    }
    /**
     * --resetTable
     * @purpose To set/reset the initial values of the table, as well as the
     *     descriptive statistics section, it will also clear text fields
     * @author Korey Sniezek
     * @since MS2
     * @version 1.0
     */
    private void resetTable() {
        accountCol.setCellValueFactory(new PropertyValueFactory<>("Account"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("FullAddress"));
        assessedCol.setCellValueFactory(new PropertyValueFactory<>("Value"));
        assClassCol.setCellValueFactory(new PropertyValueFactory<>("Class1"));
        assClassCol2.setCellValueFactory(new PropertyValueFactory<>("Class2"));
        assClassCol3.setCellValueFactory(new PropertyValueFactory<>("Class3"));
        nCol.setCellValueFactory(new PropertyValueFactory<>("Neighbourhood"));
        latCol.setCellValueFactory(new PropertyValueFactory<>("Lat"));
        longCol.setCellValueFactory(new PropertyValueFactory<>("Long"));
        
        statText.setText(handler.getStatString());
        // placeholder value
//        table.setPlaceholder(new Label("No properties to display"));
        table.setItems((ObservableList<Property>) handler.getAllProperties());
        
    }
    
    /**
     * --resetBtnHandler
     * @purpose handles the operation of the reset button, calls reset function
     *      is called via the Demo.fxml file 
     * @author Korey Sniezek
     * @since MS2
     * @version 1.0
     */
    @FXML
    public void resetBtnHandler() {
        resetTable();
        addressBox.setText("");
        neighbourhoodBox.setText("");
        accountBox.setText("");
        assessmentMenu.valueProperty().set(null);
    }
    /**
     * --searchBtnHandler
     * @purpose handles the operation of the search button
     *      is called via the Demo.fxml file 
     * 
     *      This function finds the values of each of the text boxes, then 
     *      performs a search, adding the returned properties to a set.
     *      It then transfers the sets to a new property handler if the set
     *      has a size greater than 1. This allows for us to print descriptive
     *      statistics.
     * @author Korey Sniezek
     * @since MS2
     * @version 1.0
     */
    @FXML
    public void searchBtnHandler() {
        
       
        PropertyHandler properties = new PropertyHandler();
        
        // clear descriptive stats box
        statText.setText("");
        
        ArrayList<Property> found;
        
        // the following is done to shorten the call to handler
        String acc = accountBox.getText();
        String add = addressBox.getText();
        
       
        String neigh = neighbourhoodBox.getText();
        String ass = assessmentMenu.getValue();
        found = handler.findPropertiesBySearchFields(acc, add, neigh, ass);
        
        // add found properties to new property handler
        for(int i = 0; i < found.size(); i++){
            properties.addProperty(found.get(i));
        }
        
        table.setItems(properties.getAllProperties());
        
        // set descriptive stats if set larger than 1
        if(properties.getAllProperties().size() > 1){
            statText.setText(properties.getStatString());
        }
    }
    
    public void goBtnHandler() {
        String ward = wardSelect.getValue().toString();
        setPieData(ward);
        tab.getSelectionModel().selectNext();
    }
    
    public void setBChartData(){
        PropertyHandler p;
        ArrayList<Property> found;
        BarChart.Series<String, Number> series = new XYChart.Series<>();
                
        for (int i = 1; i < 13; i++){
            String ward = "Ward " + i;
            found = handler.findPropertiesByWard(ward);
            p = new PropertyHandler();
            
            for(int j = 0; j < found.size(); j++){
                p.addProperty(found.get(j));
            }
            series.getData().add(new XYChart.Data(ward, p.getMean()));
            
        }
        bChart.getData().addAll(series);
    }
    
     public void setPieData(String ward){
        PropertyHandler p;
        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList();
        ArrayList<Property> wardList;
        ArrayList<Property> found = new ArrayList<>();
        HashSet<String> s = new HashSet<>();
        wardList = handler.findPropertiesByWard(ward);
        
            
        for(int j = 0; j < wardList.size(); j++){
            s.add(wardList.get(j).getNeighbourhood());
        }
        
        
        for (String str : s){
            p = new PropertyHandler();
            found = handler.findPropertiesByNeighbourhood(str);
            
            for(int j = 0; j < found.size(); j++){
                p.addProperty(found.get(j));
            }
            pieData.add(new PieChart.Data(str, p.getMean()));
        }
        piechart.setData(pieData);
    }
}
