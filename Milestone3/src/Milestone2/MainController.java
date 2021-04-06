/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Milestone2;

import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Korey Sniezek
 * @purpose Acts as interface between Demo.fxml and the backend
 * @since MS2
 * @version 1.0
 */
public class MainController implements Initializable {
    //private MapHandler mapHandler;
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
    private TableColumn<Property, String> assessedCol;
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
    private ChoiceBox pChartSelect;
    @FXML
    private Button seeWard;
    @FXML
    private Button go;
    @FXML
    private TabPane tab;
    @FXML
    private Tab pieChartTab;
    @FXML
    private Label stats;
    private String ward;
    @FXML
    private CheckBox accountCheckbox;
    @FXML
    private CheckBox addressCheckbox;
    @FXML
    private CheckBox assessedValueCheckbox;
    @FXML
    private CheckBox assess1Checkbox;
    @FXML
    private CheckBox assess2Checkbox;
    @FXML
    private CheckBox assess3Checkbox;
    @FXML
    private CheckBox neighbourhoodCheckbox;
    @FXML
    private CheckBox latCheckbox;
    @FXML
    private CheckBox longCheckbox;

    @FXML
    private Tab location;
    @FXML
    private WebView locationMap;
    
    
    
    
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

//        initializeMap();
        

        
        initAssessmentClasses(handler);
        initWardSelect();
        pieChartTab.setDisable(true);
        
        
        stats.setVisible(false);
        initColumnVisibility();
        
    }
    /**
     * --initColumnVisibility
     * @purpose To set the tableview column visibility intially as well as
     *  to initially set the associated checkboxes
     * @author Korey Sniezek
     * @since MS3
     * @version 1.0
     * 
     * 
     */
    private void initColumnVisibility(){
        setCheckboxListeners();
        
        // listeners will change visibility when status is set
        accountCheckbox.setSelected(true);
        addressCheckbox.setSelected(true);
        assess1Checkbox.setSelected(true);
        assessedValueCheckbox.setSelected(true);
        neighbourhoodCheckbox.setSelected(true);
        
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
    /**
     * --initWardSelect
     * @purpose helper function to initialize for setting the comboBox wardSelect items
     * @author Jason Lee
     * @since MS3
     * @version 1.0
     */
    private void initWardSelect(){
        ObservableList<String> list = FXCollections.observableArrayList();
        for (int i = 1; i < 13; i++){
            list.add("Ward " + i);
        }
        wardSelect.setItems(list);
    }
    /**
     * --initPieChartSelect
     * @purpose helper function to initialize for setting the comboBox PieChartSelect items
     * @author Jason Lee
     * @since MS3
     * @version 1.0
     */
    private void initPieChartSelect(){
        ObservableList<String> list = FXCollections.observableArrayList("Choose option for graph..."
                ,"Assessment Class", "Neighbourhoods", "Garage");
        pChartSelect.setItems(list);
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
        assessedCol.setCellValueFactory(data -> {
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            formatter.setMaximumFractionDigits(0);
            String formattedValue = formatter.format(data.getValue().getValue());
            return new SimpleStringProperty(formattedValue);
        });
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
    /**
     * --wardSelectBtnHandler
     * @purpose handles the operation of the wardSelect search button in order
     * to display pie chart data about each ward.
     * 
     *      This function sets the ward variable by getting the value from the 
     *      choice box, and then goes to the next tab to display the pie chart
     *      data for the selected ward.
     * @author Jason Lee
     * @since MS3
     * @version 1.0
     */
    @FXML
    public void wardSelectBtnHandler() {
        ward = wardSelect.getValue().toString();
        pieChartTab.setDisable(false);
        nbrhdPieData();
        initPieChartSelect();
        tab.getSelectionModel().selectNext();
        
        //Mouse Handlers for mousing over pie slices to see stats
        for (final PieChart.Data data : piechart.getData()) {
            data.getNode().addEventFilter(MouseEvent.MOUSE_ENTERED_TARGET,
                new EventHandler<MouseEvent>() {
                    @Override 
                    public void handle(MouseEvent e) {
                       stats.setVisible(true);
                       stats.setText("Name: " 
                               + data.getName() + "\nNumber of Properties: " 
                               + ((int)data.getPieValue()));
                    }
                });
        }
        for (final PieChart.Data data : piechart.getData()) {
            data.getNode().addEventFilter(MouseEvent.MOUSE_EXITED_TARGET,
                new EventHandler<MouseEvent>() {
                    @Override 
                    public void handle(MouseEvent e) {
                       stats.setVisible(false);
                    }
                });
        }
    }
    /**
     * --setBChartData
     * @purpose function to set the bar chart data, with each bar representing
     * the average property assessment value for each ward.
     * @author Jason Lee
     * @since MS3
     * @version 1.0
     */
    public void setBChartData(){
        PropertyHandler p;
        ArrayList<Property> found;
        BarChart.Series<String, Number> series = new XYChart.Series<>();
                
        for (int i = 1; i < 13; i++){
            String w = "Ward " + i;
            found = handler.findPropertiesByWard(w);
            p = new PropertyHandler();
            
            for(int j = 0; j < found.size(); j++){
                p.addProperty(found.get(j));
            }
            series.getData().add(new XYChart.Data(w, p.getMean()));
        }
        bChart.getData().addAll(series);
    }
    /**
     * --pChartSelect
     * @purpose helper function to handle the selection of data for display in
     * the pie chart
     * @author Jason Lee
     * @since MS3
     * @version 1.0
     */
    @FXML
    public void pChartSelect(){
        String choice = pChartSelect.getValue().toString();
        switch(choice){
            case "Assessment Class" -> aClassPieData();
            case "Neighbourhoods" -> nbrhdPieData();
            case "Garage" -> garagePieData();
        }
        //Mouse Handlers for mousing over pie slices to see stats
        for (final PieChart.Data data : piechart.getData()) {
            data.getNode().addEventFilter(MouseEvent.MOUSE_ENTERED_TARGET,
                new EventHandler<MouseEvent>() {
                    @Override 
                    public void handle(MouseEvent e) {
                       stats.setVisible(true);
                       stats.setText("Name: " 
                               + data.getName() + "\nNumber of Properties: " 
                               + ((int)data.getPieValue()));
                    }
                });
        }
        for (final PieChart.Data data : piechart.getData()) {
            data.getNode().addEventFilter(MouseEvent.MOUSE_EXITED_TARGET,
                new EventHandler<MouseEvent>() {
                    @Override 
                    public void handle(MouseEvent e) {
                       stats.setVisible(false);
                    }
                });
        }
    } 
    /**
     * --nbrhdPieData
     * @purpose function used to set the pie chart display data to the an
     * observable list of PieChart.Data containing the name and number of 
     * properties as a percentage of the ward.
     * 
     *      function takes the current selected ward and builds an Set of 
     *      neighbourhoods, which is used to build an observable list of 
     *      PieChart data by creating a propertyhandler where each property
     *      is within the neighbourhood. This observablelist is used for the 
     *      data for the piechart.
     * @author Jason Lee
     * @since MS3
     * @version 1.0
     */
    public void nbrhdPieData(){
        PropertyHandler p;
        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList();
        ArrayList<Property> found = new ArrayList<>();
        HashSet<String> s = new HashSet<>();
        ArrayList<Property> wardList = handler.findPropertiesByWard(ward);


        for(int j = 0; j < wardList.size(); j++){
            s.add(wardList.get(j).getNeighbourhood());
        }

        for (String str : s){
            p = new PropertyHandler();
            found = handler.findPropertiesByNeighbourhood(str);

            for(int j = 0; j < found.size(); j++){
                p.addProperty(found.get(j));
            }
            pieData.add(new PieChart.Data(str, p.getNumberOfProperties()));
        }
        piechart.setTitle("Number of Properties Per Neighbourhood as a Percentage of " + ward);
        piechart.setData(pieData);
    }
    /**
     * --nbrhdPieData
     * @purpose function used to set the pie chart display data to the an
     * observable list of PieChart.Data containing the name and number of 
     * properties as a percentage of the ward.
     * 
     *      function takes the current selected ward and builds an Set of 
     *      AssessmentClasses, which is used to build an observable list of 
     *      PieChart data by creating a propertyhandler where each property
     *      is within the assessment class. This observablelist is used for the 
     *      data for the piechart.
     * @author Jason Lee
     * @since MS3
     * @version 1.0
     */
    public void aClassPieData(){
        PropertyHandler p;
        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList();
        ArrayList<Property> found = new ArrayList<>();
        HashSet<String> s = new HashSet<>();
        ArrayList<Property> wardList = handler.findPropertiesByWard(ward);


        for(int j = 0; j < wardList.size(); j++){
            for (String c : wardList.get(j).getClasses()){
                s.add(c);
            }
        }

        for (String str : s){
            p = new PropertyHandler();
            found = handler.findPropertiesByAssessmentClass(str);

            for(int j = 0; j < found.size(); j++){
                p.addProperty(found.get(j));
            }
            pieData.add(new PieChart.Data(str, p.getNumberOfProperties()));
        }
        piechart.setTitle("Number of Properties Per Assessment Class as a Percentage of " + ward);
        piechart.setData(pieData);
    }
    /**
     * --nbrhdPieData
     * @purpose function used to set the pie chart display data to the an
     * observable list of PieChart.Data containing the name and number of 
     * properties as a percentage of the ward.
     * 
     *      function takes the current selected ward and builds an Set of 
     *      Garage string(Y/N), which is used to build an observable list of 
     *      PieChart data by creating a propertyhandler where each property
     *      either has a garage, Y or doesn't, N. This observablelist is used 
     *      for the data for the piechart.
     * @author Jason Lee
     * @since MS3
     * @version 1.0
     */
    public void garagePieData(){
        PropertyHandler p;
        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList();
        ArrayList<Property> found = new ArrayList<>();
        ArrayList<Property> wardList = handler.findPropertiesByWard(ward);

        HashSet<String> s = new HashSet<>();
        for(int j = 0; j < wardList.size(); j++){
            s.add(wardList.get(j).getGarage());
        }

        for (String str : s){
            p = new PropertyHandler();
            found = handler.findPropertiesByGarage(str);

            for(int j = 0; j < found.size(); j++){
                p.addProperty(found.get(j));
            }
            pieData.add(new PieChart.Data(str, p.getNumberOfProperties()));
        }
        piechart.setTitle("Number of Properties with versus without a Garage as a Percentage of " + ward);
        piechart.setData(pieData);
    }
    /**
     * --setCheckboxListeners
     * @purpose To initialize the column visibility checkbox listeners
     * @author  Korey Sniezek
     * @since MS3
     * @version 1.0
     * Note: This was adapted from https://stackoverflow.com/questions/22882791/javafx-check-if-a-checkbox-is-ticked
     */
    private void setCheckboxListeners(){
        
        // Set account checkbox
        accountCheckbox.selectedProperty().addListener(new ChangeListener<Boolean>(){
             @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            // TODO Auto-generated method stub
            if(newValue){

                accountCol.setVisible(true);

            }else{

                accountCol.setVisible(false);
            }
        }
        
        
    });
        
        // Set address checkbox
        addressCheckbox.selectedProperty().addListener(new ChangeListener<Boolean>(){
             @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            // TODO Auto-generated method stub
            if(newValue){

                addressCol.setVisible(true);

            }else{

                addressCol.setVisible(false);
            }
        }
        
        
    });
        
        // set assessed value checkbox
        assessedValueCheckbox.selectedProperty().addListener(new ChangeListener<Boolean>(){
             @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            // TODO Auto-generated method stub
            if(newValue){

                assessedCol.setVisible(true);

            }else{

                assessedCol.setVisible(false);
            }
        }
        
        
    });
        
        // set assessment class 1
        assess1Checkbox.selectedProperty().addListener(new ChangeListener<Boolean>(){
             @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            // TODO Auto-generated method stub
            if(newValue){

                assClassCol.setVisible(true);

            }else{

                assClassCol.setVisible(false);
            }
        }
    });
        
        // set assessment class 2
        assess2Checkbox.selectedProperty().addListener(new ChangeListener<Boolean>(){
             @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            // TODO Auto-generated method stub
            if(newValue){

                assClassCol2.setVisible(true);

            }else{

                assClassCol2.setVisible(false);
            }
        }
    });
        
        // set assessment class 3
        assess3Checkbox.selectedProperty().addListener(new ChangeListener<Boolean>(){
             @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            // TODO Auto-generated method stub
            if(newValue){

                assClassCol3.setVisible(true);

            }else{

                assClassCol3.setVisible(false);
            }
        }
    });
        
        // set neighbourhood listener
        neighbourhoodCheckbox.selectedProperty().addListener(new ChangeListener<Boolean>(){
             @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            // TODO Auto-generated method stub
            if(newValue){

                nCol.setVisible(true);

            }else{

                nCol.setVisible(false);
            }
        }
    });
        // set latitude listener
        latCheckbox.selectedProperty().addListener(new ChangeListener<Boolean>(){
             @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            // TODO Auto-generated method stub
            if(newValue){

                latCol.setVisible(true);

            }else{

                latCol.setVisible(false);
            }
        }
    });
        // set longitude listener
        longCheckbox.selectedProperty().addListener(new ChangeListener<Boolean>(){
             @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            // TODO Auto-generated method stub
            if(newValue){

                longCol.setVisible(true);

            }else{

                longCol.setVisible(false);
            }
        }
    });
    }
    
    /**
     * --findOnMap
     * @purpose finds the longitude and latitude of first selected cell
     *  then finds that location on the map, shows map tab
     * @author Korey Sniezek   
     * @version 1.0
     * @since MS3
     * @param event 
     */
    @FXML
    private void findOnMap(ActionEvent event) {
        double latitude;
        double longitude;
        String markerString = "";
        String stageTitle = "Edmonton";
        String address = "Edmonton";
        
        Property property = table.getSelectionModel().getSelectedItem();
        if (property == null){
            latitude = 53.546186;
            longitude = -113.528368;
            
        }
        else {
            latitude = property.getLat();
            longitude = property.getLong();
            address = property.getFullAddress();
            stageTitle = "Account: " + property.getAccount();
        }
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        URL url = getClass().getResource("google.html");

        webEngine.loadContent("<!DOCTYPE html>\n" +
"<html>\n" +
"  <head>\n" +
"    <title>Simple Map</title>\n" +
"    <script src=\"https://polyfill.io/v3/polyfill.min.js?features=default\"></script>\n" +
"    <style type=\"text/css\">\n" +
"      /* Always set the map height explicitly to define the size of the div\n" +
"       * element that contains the map. */\n" +
"      #map {\n" +
"        height: 100%;\n" +
"      }\n" +
"\n" +
"      /* Optional: Makes the sample page fill the window. */\n" +
"      html,\n" +
"      body {\n" +
"        height: 100%;\n" +
"        margin: 0;\n" +
"        padding: 0;\n" +
"      }\n" +
"    </style>\n" +
"    <script>\n" +
"      let map;\n" +
"\n" +
"      function initMap() {\n" +
"          const myLatLng = { lat: " + latitude + ", lng: " + longitude + " };\n" +
"          const map = new google.maps.Map(document.getElementById(\"map\"), {\n" +
"              zoom: 10,\n" +
"              center: myLatLng,\n" +
"          });\n" +
"         new google.maps.Marker({\n" +
"             position: myLatLng,\n" +
"             map,\n" +
"             title: \"" + address + "\",\n" +
"         });\n" +
"        \n" +
"        \n" +
"      }\n" +
"    </script>\n" +
"  </head>\n" +
"  <body>\n" +
"    <div id=\"map\"></div>\n" +
"\n" +
"    <!-- Async script executes immediately and must be after any DOM elements used in callback. -->\n" +
"    <script\n" +
"      src=\"https://maps.googleapis.com/maps/api/js?key=AIzaSyCL5e1oiJiSx3yOqwia4NgeEqjiprE3_uE&callback=initMap&libraries=&v=weekly\"\n" +
"      async\n" +
"    ></script>\n" +
"  </body>\n" +
"</html>");
        Stage locationStage = new Stage();
        Scene scene = new Scene(webView,600,600);
        locationStage.setScene(scene);
        locationStage.setTitle(stageTitle);
  
        locationStage.show();
    }
}
