/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arabitogrill.main;

import com.jfoenix.controls.JFXDrawer;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import arabitogrill.util.ArabitoGrillUtil;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import com.jfoenix.controls.JFXTabPane;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javafx.scene.control.cell.PropertyValueFactory;
import arabitogrill.main.toolbaruser.ToolbarController;
import arabitogrill.consts.Consts;
import arabitogrill.model.Workers;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.util.Callback;
import arabitogrill.util.ArabitoGrillUtil;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import arabitogrill.model.DaysOfWeek;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import arabitogrill.paymentforusers.*;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
/**
 *
 * @author eulis
 */
public class MainController  implements Initializable {

    private final String CALENDAR_ID = "calendarTab";
    
    @FXML
    private TableColumn mondayCol;
    @FXML
    private TableColumn tuesdayCol;
    @FXML
    private TableColumn wednesdayCol;
    @FXML
    private TableColumn thursdayCol;
    @FXML
    private TableColumn fridayCol;
    @FXML
    private TableColumn saturdayCol;
    @FXML
    private TableColumn sundayCol;
    @FXML
    private TableView tableView;
    @FXML
    private StackPane rootPane;
    @FXML
    private JFXTabPane footerTabPane;
    @FXML
    private JFXTabPane headerTabPane;    
    @FXML
    private JFXDrawer drawer;
    @FXML
    public JFXDrawer drawerUsers;
    @FXML
    private JFXTabPane mainTabPane;
    
    static public boolean firstCharge = false;
    static public VBox toolbar;
    static public FXMLLoader toolbarUsersFXML;
    static public FXMLLoader toolbarFXML;
    static public VBox toolbarUsers;
    arabitogrill.main.toolbar.ToolbarController controllerMenu;
    private ToolbarController controller;
    
    @Override
    @FXML
    public void initialize(URL url, ResourceBundle rb) {

          tableView.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler() {
             @Override
             public void handle(Event event) {
                 String day = event.getTarget().toString().split("text=")[1].split("Amount:")[0].trim().replace((""+'"'),"");
                 String amount = event.getTarget().toString().split("text=")[1].split("Amount:")[1].split("Tips:")[0].trim();
                 String tips =  event.getTarget().toString().split("text=")[1].split("Amount:")[1].split("Tips:")[1].trim();
                 System.out.println(day);
                 System.out.println(amount);
                 System.out.println(tips);
             }
         });
        /*tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            TableColumn value = (TableColumn)tableView.getColumns().get(tableView.getSelectionModel().getFocusedIndex());
            System.out.println(value.getText());
            ArabitoGrillUtil.loadWindow(getClass().getResource("/arabitogrill/paymentforusers/PaymentForWorkers.fxml"), "Payment for Workers",null,false,true);
        });*/
        
        for(int i = Consts.startYears; i <= Consts.endYears; i++)
            footerTabPane.getTabs().add(new Tab(""+i+""));
        
        mondayCol.prefWidthProperty().bind(tableView.widthProperty().divide(7));
        tuesdayCol.prefWidthProperty().bind(tableView.widthProperty().divide(7));
        wednesdayCol.prefWidthProperty().bind(tableView.widthProperty().divide(7));
        thursdayCol.prefWidthProperty().bind(tableView.widthProperty().divide(7));
        fridayCol.prefWidthProperty().bind(tableView.widthProperty().divide(7));
        saturdayCol.prefWidthProperty().bind(tableView.widthProperty().divide(7));
        sundayCol.prefWidthProperty().bind(tableView.widthProperty().divide(7));

        mondayCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DaysOfWeek, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DaysOfWeek, String> p) {
                return new ReadOnlyStringWrapper((p.getValue().getMondayCol()==null ? "":p.getValue().getMondayCol()+"\n\n\n   Amount:\n   Tips:\n\n\n"));
            }
         });
        tuesdayCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DaysOfWeek, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DaysOfWeek, String> p) {
                return new ReadOnlyStringWrapper((p.getValue().getTuesdayCol()==null ? "":p.getValue().getTuesdayCol()+"\n\n\n   Amount:\n   Tips:\n\n\n"));
            }
         });
        wednesdayCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DaysOfWeek, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DaysOfWeek, String> p) {
                return new ReadOnlyStringWrapper((p.getValue().getWednesdayCol()==null ? "":p.getValue().getWednesdayCol()+"\n\n\n   Amount:\n   Tips:\n\n\n"));
            }
         });
        thursdayCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DaysOfWeek, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DaysOfWeek, String> p) {
                return new ReadOnlyStringWrapper((p.getValue().getThursdayCol()==null ? "":p.getValue().getThursdayCol()+"\n\n\n   Amount:\n   Tips:\n\n\n"));
            }
         });
        fridayCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DaysOfWeek, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DaysOfWeek, String> p) {
                return new ReadOnlyStringWrapper((p.getValue().getFridayCol()==null ? "":p.getValue().getFridayCol()+"\n\n\n   Amount:\n   Tips:\n\n\n"));
            }
         });
        saturdayCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DaysOfWeek, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DaysOfWeek, String> p) {
                return new ReadOnlyStringWrapper((p.getValue().getSaturdayCol()==null ? "":p.getValue().getSaturdayCol()+"\n\n\n   Amount:\n   Tips:\n\n\n"));
            }
         });
        sundayCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DaysOfWeek, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DaysOfWeek, String> p) {
                return new ReadOnlyStringWrapper((p.getValue().getSundayCol()==null ? "":p.getValue().getSundayCol()+"\n\n\n   Amount:\n   Tips:\n\n\n"));
            }
         });
        mainTabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
            @Override
            public void changed(ObservableValue<? extends Tab> old, Tab oldTab, Tab newTab) {
                //Check for Tab and call you method here
                if(newTab.getId().equals(CALENDAR_ID))
                {
                    controller.refreshTableView();
                }
            }

        });
        
        if(firstCharge == false)
        {
            ObservableList<DaysOfWeek> daysOfWeekList = null;
            daysOfWeekList = FXCollections.observableArrayList();
            try{
                String month = ArabitoGrillUtil.monthNumber(headerTabPane.getTabs().get(headerTabPane.getSelectionModel().getSelectedIndex()).getText()).toString();
                String year = footerTabPane.getTabs().get(footerTabPane.getSelectionModel().getSelectedIndex()).getText();                
                Calendar cal1 = new GregorianCalendar(Integer.parseInt(year.trim()),Integer.parseInt(month.trim())-1,1);
                DaysOfWeek dow = new DaysOfWeek();
                for(int i = 1; i <= cal1.getMaximum(Calendar.DAY_OF_MONTH); i++){
                    switch(ArabitoGrillUtil.dayName(year+"-"+month+"-"+i, ("yyyy-M-d").toString()).toString().toLowerCase())
                    {
                        case "monday":
                            dow.setMondayCol(""+i+"");
                            break;
                        case "tuesday":
                            dow.setTuesdayCol(""+i+"");
                            break;
                        case "wednesday":
                            dow.setWednesdayCol(""+i+"");
                            break;                                                         
                        case "thursday":
                            dow.setThursdayCol(""+i+"");
                            break;
                        case "friday":
                            dow.setFridayCol(""+i+"");
                            break;
                        case "saturday":
                            dow.setSaturdayCol(""+i+"");
                            break;
                        case "sunday":
                            dow.setSundayCol(""+i+"");
                            daysOfWeekList.add(dow);
                            dow = new DaysOfWeek();
                            break;
                    }
                }  
                if(dow.getMondayCol() != null && dow.getSundayCol() == null){
                    daysOfWeekList.add(dow);
                }
                tableView.setItems(daysOfWeekList);
            }catch(Exception ex)
            {
                System.out.println(ex.getMessage());
            }
            firstCharge = true;
        }
        headerTabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
            @Override
            public void changed(ObservableValue<? extends Tab> old, Tab oldTab, Tab newTab) {
                //Check for Tab and call you method here
                ObservableList<DaysOfWeek> daysOfWeekList = null;
                daysOfWeekList = FXCollections.observableArrayList();
                try{
                    String month = ArabitoGrillUtil.monthNumber(newTab.getText().toString()).toString();
                    String year = footerTabPane.getTabs().get(footerTabPane.getSelectionModel().getSelectedIndex()).getText();                
                    Calendar cal1 = new GregorianCalendar(Integer.parseInt(year.trim()),Integer.parseInt(month.trim())-1,1);
                    DaysOfWeek dow = new DaysOfWeek();
                    for(int i = 1; i <= cal1.getMaximum(Calendar.DAY_OF_MONTH); i++){
                        switch(ArabitoGrillUtil.dayName(year+"-"+month+"-"+i, ("yyyy-M-d").toString()).toString().toLowerCase())
                        {
                            case "monday":
                                dow.setMondayCol(""+i+"");
                                break;
                            case "tuesday":
                                dow.setTuesdayCol(""+i+"");
                                break;
                            case "wednesday":
                                dow.setWednesdayCol(""+i+"");
                                break;                                                         
                            case "thursday":
                                dow.setThursdayCol(""+i+"");
                                break;
                            case "friday":
                                dow.setFridayCol(""+i+"");
                                break;
                            case "saturday":
                                dow.setSaturdayCol(""+i+"");
                                break;
                            case "sunday":
                                dow.setSundayCol(""+i+"");
                                daysOfWeekList.add(dow);
                                dow = new DaysOfWeek();
                                break;
                        }
                    }  
                    if(dow.getMondayCol() != null && dow.getSundayCol() == null){
                        daysOfWeekList.add(dow);
                    }
                    tableView.setItems(daysOfWeekList);
                }catch(Exception ex)
                {
                    System.out.println(ex.getMessage());
                }
            }

        });
        
        footerTabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
            @Override
            public void changed(ObservableValue<? extends Tab> old, Tab oldTab, Tab newTab) {
                //Check for Tab and call you method here
                ObservableList<DaysOfWeek> daysOfWeekList = FXCollections.observableArrayList();
                try{
                    String year = newTab.getText().toString();
                    String month = ArabitoGrillUtil.monthNumber(headerTabPane.getTabs().get(headerTabPane.getSelectionModel().getSelectedIndex()).getText()).toString();
                    Calendar cal1 = new GregorianCalendar(Integer.parseInt(year.trim()),Integer.parseInt(month.trim())-1,1);
                    DaysOfWeek dow = new DaysOfWeek();
                    for(int i = 1; i <= cal1.getMaximum(Calendar.DAY_OF_MONTH); i++){
                        System.out.println(year+"-"+month+"-"+i);
                        switch(ArabitoGrillUtil.dayName(year+"-"+month+"-"+i, ("yyyy-M-d").toString()).toString().toLowerCase())
                        {
                            case "monday":
                                dow.setMondayCol(""+i+"");
                                break;
                            case "tuesday":
                                dow.setTuesdayCol(""+i+"");
                                break;
                            case "wednesday":
                                dow.setWednesdayCol(""+i+"");
                                break;                                                         
                            case "thursday":
                                dow.setThursdayCol(""+i+"");
                                break;
                            case "friday":
                                dow.setFridayCol(""+i+"");
                                break;
                            case "saturday":
                                dow.setSaturdayCol(""+i+"");
                                break;
                            case "sunday":
                                dow.setSundayCol(""+i+"");
                                daysOfWeekList.add(dow);
                                dow = new DaysOfWeek();
                                break;
                        }
                    }
                    if(dow.getMondayCol() != null && dow.getSundayCol() == null){
                        daysOfWeekList.add(dow);
                    }
                    tableView.setItems(daysOfWeekList);
                }catch(Exception ex)
                {
                    System.out.println(ex.getMessage());
                }
            }

        });
        try {
            toolbarFXML  = new FXMLLoader(getClass().getResource("/arabitogrill/main/toolbar/toolbar.fxml"));
            toolbar  =  (VBox)toolbarFXML.load();
            toolbarUsersFXML  = new FXMLLoader(getClass().getResource("/arabitogrill/main/toolbaruser/toolbar.fxml"));
            toolbarUsers  =  (VBox)toolbarUsersFXML.load();
            controller = toolbarUsersFXML.getController();
            controllerMenu = toolbarFXML.getController();
            controllerMenu.setMainController(this);
            drawer.setSidePane(toolbar);
            drawer.open();
            drawerUsers.setSidePane(toolbarUsers);
            drawerUsers.open();
             
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
    }
    
    public ToolbarController getToolbarController(){
        return controller;
    }
    
    @FXML
    private void handleMenuFullScreen(ActionEvent event) {
        executeMenuFullScreen();
    }
    
    private void executeMenuFullScreen(){
        Stage stage = ((Stage) rootPane.getScene().getWindow());
        stage.setFullScreen(!stage.isFullScreen());
    }
    
    @FXML
    private void handleMenuClose(ActionEvent event) {
        ((Stage) rootPane.getScene().getWindow()).close();
    }
    
    @FXML
    private void handleMenuAddMember(ActionEvent event) {
        ArabitoGrillUtil.loadWindow(getClass().getResource("/arabitogrill/addmember/member_add.fxml"), "Add New Member", null, false, false);
    }
}
